package interfaz.menuReserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class listadoAReservar extends JInternalFrame {
    private reservaFinalListener listener;
    private JTable tabla;
    private DefaultTableModel modelo;
    public listadoAReservar(List<Object[]> datos, String tipoHabitacion) {

        super("Listado a Reservar", false, false, false, false);
        setSize(900, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("LISTADO A RESERVAR", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new Object[]{"Tipo de Habitación", "Ingreso", "Egreso"},
                0
        );

        tabla = new JTable(modelo);
        tabla.setRowHeight(28);

        cargarTabla(datos, tipoHabitacion);

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnRechazar = new JButton("Rechazar");

        btnAceptar.addActionListener(e -> {

            finalizarReserva fr = new finalizarReserva();

            // Recibe el DNI desde la pantalla final
            fr.setReservaFinalListener(dni -> {

                // Reenvía el DNI directamente a reservarHabitacion
                if (listener != null) {
                    listener.onReservaFinalizada(dni);
                }

                // Cierra ambas ventanas
                fr.dispose();
                this.dispose();
            });

            getDesktopPane().add(fr);
            fr.setVisible(true);
            fr.toFront();
        });



        btnRechazar.addActionListener(e -> {

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "¿Desea cancelar la reserva?",
                    "Confirmar cancelación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"SI", "NO"},
                    "SI"
            );

            if (opcion == JOptionPane.YES_OPTION) {
                if (getDesktopPane() != null) {
                    for (JInternalFrame frame : getDesktopPane().getAllFrames()) {
                        if (frame instanceof reservarHabitacion) {
                            frame.dispose();
                            dispose();
                        }
                    }
                }
            }

        });

        botones.add(btnAceptar);
        botones.add(btnRechazar);

        add(botones, BorderLayout.SOUTH);
    }

    private void cargarTabla(List<Object[]> datos, String tipoHabitacion) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Object[] fila : datos) {

            String numeroHabitacion = tipoHabitacion + " "+fila[0].toString();
            LocalDate fechaDesde = (LocalDate) fila[1];
            LocalDate fechaHasta = (LocalDate) fila[2];

            String ingreso = fechaDesde.format(formato) + ", 12:00hs.";
            String egreso  = fechaHasta.format(formato) + ", 10:00hs.";

            modelo.addRow(new Object[]{
                    numeroHabitacion,
                    ingreso,
                    egreso
            });
        }
    }

    public void setReservaFinalListener(reservaFinalListener l) {
        this.listener = l;
    }
}