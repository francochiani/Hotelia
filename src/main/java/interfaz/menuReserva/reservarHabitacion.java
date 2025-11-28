package interfaz.menuReserva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gestor.gestorHabitacion;
import gestor.gestorReserva;

public class reservarHabitacion extends JInternalFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JFormattedTextField txtDesdeFecha, txtHastaFecha;
    private JComboBox<String> cmbTipoHabitacion;

    // // ===== VARIABLES PARA SELECCIÓN CU04 =====
    private int filaInicio = -1;
    private int filaFin = -1;
    private int columnaSeleccionada = -1;

    // ===== VARIABLES PARA TABLA DINÁMICA =====
    private List<String> columnasDinamicas; // [fecha, hab1, hab2, ...]
    private java.util.List<Object[]> datosTabla; //[[hab, fecha, estado],...]

    // ===== SELECCIONES MULTIHABITACIÓN CU05 =====
    private List<Object[]> habitacionesSeleccionadas = new ArrayList<>();

    public reservarHabitacion() {
        setTitle("Reserva de habitación");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setSize(1100, 650);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // ===== PANEL PRINCIPAL =====
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(panel, BorderLayout.CENTER);

        // ===== PANEL SUPERIOR (Filtros) =====
        JPanel panelFiltros = new JPanel(new GridBagLayout());
        panelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar habitación a reservar"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Fecha desde
        gbc.gridx = 0; gbc.gridy = 0;
        panelFiltros.add(new JLabel("Desde fecha"), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        txtDesdeFecha = new JFormattedTextField("dd/mm/aaaa");
        txtDesdeFecha.setColumns(12);
        panelFiltros.add(txtDesdeFecha, gbc);

        // Fecha hasta
        gbc.gridx = 1; gbc.gridy = 0;
        panelFiltros.add(new JLabel("Hasta fecha"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        txtHastaFecha = new JFormattedTextField("dd/mm/aaaa");
        txtHastaFecha.setColumns(12);
        panelFiltros.add(txtHastaFecha, gbc);

        // Tipo habitación
        gbc.gridx = 2; gbc.gridy = 0;
        panelFiltros.add(new JLabel("Tipo de habitación"), gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        cmbTipoHabitacion = new JComboBox<>(new String[]{
                "Individual Estándar ", "Doble Estándar ",
                "Doble Superior ", "Superior Family Plan ", "Suite Doble "
        });
        cmbTipoHabitacion.setPreferredSize(new Dimension(150, 25));
        panelFiltros.add(cmbTipoHabitacion, gbc);

        // Botón consultar disponibilidad
        gbc.gridx = 3; gbc.gridy = 1;
        JButton btnConsultar = new JButton("Consultar Disponibilidad");
        btnConsultar.addActionListener(e -> consultarDisponibilidad());
        panelFiltros.add(btnConsultar, gbc);

        panel.add(panelFiltros, BorderLayout.BEFORE_FIRST_LINE);

        // ===== TABLA =====
        modelo = new DefaultTableModel();
        modelo.addColumn("Fecha");
        for (int i = 1; i <= 12; i++) modelo.addColumn("Hab " + i);

        tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.setSelectionBackground(new Color(0,0,0,0));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setDefaultRenderer(Object.class, new EstadoRenderer());

        // CLICK para selección CU04
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                seleccionarRango();
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        panel.add(scroll, BorderLayout.CENTER);

        // ===== LEYENDA + BOTÓN =====
        JPanel panelInferior = new JPanel(new BorderLayout());

        JPanel leyenda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leyenda.add(crearLeyenda(Color.RED, "ocupado"));
        leyenda.add(crearLeyenda(Color.ORANGE, "reservado"));
        leyenda.add(crearLeyenda(Color.WHITE, "libre"));
        leyenda.add(crearLeyenda(Color.GRAY, "fuera de servicio"));
        panelInferior.add(leyenda, BorderLayout.WEST);

        JButton btnReservar = new JButton("Reservar Habitación");
        btnReservar.setPreferredSize(new Dimension(150, 35));
        btnReservar.addActionListener(e -> reservar());

        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBtn.add(btnReservar);

        panelInferior.add(panelBtn, BorderLayout.EAST);
        panel.add(panelInferior, BorderLayout.SOUTH);
    }

    // ===== CREAR LEYENDA =====
    private JPanel crearLeyenda(Color color, String texto) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel cuadrado = new JLabel("  ");
        cuadrado.setOpaque(true);
        cuadrado.setBackground(color);
        cuadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        p.add(cuadrado);
        p.add(new JLabel(texto));
        return p;
    }

    // ===== RENDERER DE ESTADOS =====
    class EstadoRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // La columna 0 (Fechas) NO se colorea
            if (column == 0) {
                c.setBackground(Color.WHITE);
                return c;
            }

            // Si la celda no tiene valor no la colorea
            if (value == null) {
                c.setBackground(Color.WHITE);
                return c;
            }

            String estado = value.toString().toLowerCase();

            switch (estado) {
                case "ocupado":
                    c.setBackground(Color.RED);
                    break;
                case "reservado":
                    c.setBackground(Color.ORANGE);
                    break;
                case "libre":
                    c.setBackground(Color.WHITE);
                    break;
                case "fuera":
                case "fuera de servicio":
                    c.setBackground(Color.GRAY);
                    break;
                case "inicio":
                    c.setBackground(Color.CYAN);
                    break;
                case "por reservar":
                    c.setBackground(Color.CYAN);
                    break;
                default:
                    c.setBackground(Color.WHITE);
            }

            return c;
        }
    }

    // ===== FUNCIONALIDAD CU05 =====
    private void consultarDisponibilidad() {
        // Limpio la tabla
        modelo.setRowCount(0);

        try {
            LocalDate desde = LocalDate.parse(txtDesdeFecha.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate hasta = LocalDate.parse(txtHastaFecha.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (hasta.isBefore(desde)) {
                JOptionPane.showMessageDialog(this, "La fecha hasta debe ser posterior a la fecha desde");
                return;
            }
            String tipo = cmbTipoHabitacion.getSelectedItem().toString();

            gestorHabitacion gestorHabitacion = new gestorHabitacion();

            columnasDinamicas = gestorHabitacion.obtenerColumnas(tipo);
            datosTabla = gestorHabitacion.obtenerDisponibilidad(desde, hasta, tipo);

            cargarColumnas(columnasDinamicas);
            for (Object[] fila : datosTabla) {
                modelo.addRow(fila);
            }
            habitacionesSeleccionadas.clear();
            limpiarSeleccion();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ingrese fechas válidas (dd/MM/yyyy)");
        }
    }

    // Actualiza la tabla de forma dinámica -> IE son 10 columnas, DE son 18 columnas ...
    private void cargarColumnas(List<String> columnas) {
        modelo.setColumnCount(0);
        for (String col : columnas) {
            modelo.addColumn(col);
        }
    }

    // ===== FUNCIONALIDAD CU04 =====
    // ===== GUARDAR RESERVA =====
    private void reservar() {
        if (habitacionesSeleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar por lo menos una habitación");
            return;
        }

        // Crear la ventana listadoAReservar
        listadoAReservar ventana = new listadoAReservar(
                habitacionesSeleccionadas,
                cmbTipoHabitacion.getSelectedItem().toString()
        );

        // Listener correctamente agregado (NO estático)
        ventana.setReservaFinalListener(dni -> {

            gestorReserva gestor = new gestorReserva();
            gestorHabitacion gestorHabitacion = new gestorHabitacion();

            // Registrar reservas cuando vuelve el DNI de finalizarReserva
            gestor.registrarReservas(habitacionesSeleccionadas, dni);
            gestorHabitacion.actualizarEstadoHabitacion(habitacionesSeleccionadas,"Reservado");

            JOptionPane.showMessageDialog(
                    this,
                    "Reservas registradas correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Cerrar reservarHabitacion
            this.dispose();
        });

        JDesktopPane desktop = this.getDesktopPane();
        desktop.add(ventana);
        ventana.setVisible(true);
        ventana.toFront();
    }

    // ===== GESTIONAR EL RANGO DE FECHAS SELECCIONADO =====
    private void seleccionarRango() {
        int fila = tabla.getSelectedRow();
        int col = tabla.getSelectedColumn();
        gestorHabitacion gestorHabitacion = new gestorHabitacion();
        if (fila == -1 || col <= 0) return;
        String estado = String.valueOf(tabla.getValueAt(fila, col));
        if (!estado.equals("libre") && !estado.equals("inicio")) {
            JOptionPane.showMessageDialog(this, "Seleccione solo fechas LIBRES");
            return;
        }
        //  1° clic → inicio
        if (filaInicio == -1) {
            limpiarInicioPrevio(); // solo borra "inicio" previo, NO borra reservas hechas
            filaInicio = fila;
            columnaSeleccionada = col;
            modelo.setValueAt("inicio", fila, col);
            return;
        }
        //  2° clic → fin
        if (filaFin == -1) {
            if (col != columnaSeleccionada) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar la misma habitación");
                return;
            }
            filaFin = fila;
            int desdeF = Math.min(filaInicio, filaFin);
            int hastaF = Math.max(filaInicio, filaFin);
            for (int f = desdeF; f <= hastaF; f++) { // validar rango
                String est = String.valueOf(tabla.getValueAt(f, col));
                if (!est.equals("libre") && !est.equals("inicio")) {
                    JOptionPane.showMessageDialog(this, "El rango contiene días NO disponibles");
                    resetSeleccion();  // resetea selección pero NO borra reservas previas
                    return;
                }
            }
            for (int f = desdeF; f <= hastaF; f++) { // marcar visualmente
                modelo.setValueAt("por reservar", f, col);
            }
            String numeroHabitacion = columnasDinamicas.get(col); // datos para el gestor
            long idReal = gestorHabitacion.obtenerIdPorNumero(numeroHabitacion);
            if (idReal != -1) {
                DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaDesde = LocalDate.parse(tabla.getValueAt(desdeF, 0).toString(), f);
                LocalDate fechaHasta = LocalDate.parse(tabla.getValueAt(hastaF, 0).toString(), f);
                habitacionesSeleccionadas.add(
                        new Object[]{ idReal, fechaDesde, fechaHasta }
                );
            }
            resetSeleccion();
            return;
        }
        resetSeleccion();
    }

    // ===== REINICAR LOS CONTADORES PARA UNA NUEVA SELECCIÓN DE FEHCAS =====
    private void resetSeleccion() {
        filaInicio = -1;
        filaFin = -1;
        columnaSeleccionada = -1;
    }

    private void limpiarInicioPrevio() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 1; j < modelo.getColumnCount(); j++) {
                if ("inicio".equals(modelo.getValueAt(i, j))) {
                    modelo.setValueAt("libre", i, j);
                }
            }
        }
    }

    private void limpiarSeleccion() {
        filaInicio = -1;
        filaFin = -1;
        columnaSeleccionada = -1;
        habitacionesSeleccionadas.clear();
        limpiarInicioPrevio();
        for (int i = 0; i < modelo.getRowCount(); i++)
            for (int j = 1; j < modelo.getColumnCount(); j++)
                if ("por reservar".equals(modelo.getValueAt(i, j)))
                    modelo.setValueAt("libre", i, j);
    }
}

