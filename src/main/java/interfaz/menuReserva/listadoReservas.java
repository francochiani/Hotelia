package interfaz.menuReserva;

import gestor.gestorEstadia;
import gestor.gestorHabitacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class listadoReservas extends JInternalFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    public listadoReservas(List<Object[]> listado, String DNI) {

        setTitle("Listado de Reservas");
        setSize(900, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setLayout(new BorderLayout());

        // ===== TÍTULO SUPERIOR =====
        JLabel lblTitulo = new JLabel("Listado de Reservas para DNI: "+DNI, SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 0));

        add(lblTitulo, BorderLayout.NORTH);

        // ===== TABLA =====
        String[] columnas = {"Tipo de Habitación", "Ingreso", "Egreso"};

        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla solo lectura
            }
        };

        tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 30));
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        add(scroll, BorderLayout.CENTER);
        cargarDatos(listado);

        // ===== PANEL DE BOTONES INFERIOR =====
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        JButton btnIniciar = new JButton("Iniciar Estadía");
        btnIniciar.setPreferredSize(new Dimension(130, 30));
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(130, 30));
        btnCancelar.addActionListener(e -> dispose());
        panelBotones.add(btnIniciar);
        panelBotones.add(btnCancelar);
        btnIniciar.addActionListener(e -> iniciarEstadia(listado,DNI));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        add(panelBotones, BorderLayout.SOUTH);
    }

    // ===== CARGAR LA INFORMACIÓN =====
    private void cargarDatos(List<Object[]> listado) {
        modelo.setRowCount(0);
        if (listado == null) return;
        for (Object[] fila : listado) {
            modelo.addRow(fila);
        }
    }

    private void iniciarEstadia(List<Object[]> listado, String DNI) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una reserva.");
            return;
        }

        Object[] datos = listado.get(filaSeleccionada);
        List<Object[]> reservaSeleccionada = new java.util.ArrayList<>();
        reservaSeleccionada.add(datos);
        Long idReserva = Long.parseLong(datos[3].toString());

        gestorEstadia gestorEstadia = new gestorEstadia();
        gestorHabitacion gestorHabitacion = new gestorHabitacion();

        gestorEstadia.iniciarEstadia(idReserva,DNI);
        gestorHabitacion.ocuparHabitacion(reservaSeleccionada);

        dispose();
}}
