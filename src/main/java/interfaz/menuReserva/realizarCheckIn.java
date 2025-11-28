package interfaz.menuReserva;

import javax.swing.*;
import java.awt.*;

public class realizarCheckIn extends JInternalFrame {

        private JLabel lblImagen;
        private JButton btnConReserva;
        private JButton btnSinReserva;

        public realizarCheckIn() {
            setTitle("Realizar Check In");
            setClosable(true);
            setIconifiable(true);
            setResizable(false);
            setSize(530, 435);

            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);

            // Título
            JLabel lblTitulo = new JLabel("REALIZAR CHECK IN");
            lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

            // Imagen dentro de un panel centrado
            JPanel panelImagen = new JPanel(new BorderLayout());
            panelImagen.setBackground(Color.WHITE);
            panelImagen.setPreferredSize(new Dimension(500, 220));

            lblImagen = new JLabel("", SwingConstants.CENTER);
            lblImagen.setOpaque(true);
            lblImagen.setBackground(new Color(230, 230, 230)); // gris solo si no carga la imagen
            panelImagen.add(lblImagen, BorderLayout.CENTER);

            cargarImagen("src/main/resources/banner.png", 450, 170);

            // Botones
            btnConReserva = new JButton("Huésped con reserva previa");
            btnSinReserva = new JButton("Huésped sin reserva previa");
            btnConReserva.setPreferredSize(new Dimension(220, 40));
            btnSinReserva.setPreferredSize(new Dimension(220, 40));

            btnConReserva.addActionListener(e -> {
                buscarReserva Ventana = new buscarReserva();
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(Ventana);
                Ventana.setVisible(true);

            });

            btnSinReserva.addActionListener(e -> {

                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "¿Desea reservar una habitación?",
                        "Confirmar reserva",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"SI", "NO"},
                        "SI"
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    reservarHabitacion Ventana = new reservarHabitacion();
                    JDesktopPane desktopPane = getDesktopPane();
                    desktopPane.add(Ventana);
                    Ventana.setVisible(true);
                    this.dispose();
                }

            });

            GroupLayout layout = new GroupLayout(panel);
            panel.setLayout(layout);
            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(panelImagen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitulo)
                            .addGroup(
                                    layout.createSequentialGroup()
                                            .addComponent(btnConReserva, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                                            .addGap(40)
                                            .addComponent(btnSinReserva, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                            )
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGap(15)
                            .addComponent(lblTitulo)
                            .addGap(15)
                            .addComponent(panelImagen, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                            .addGap(15)
                            .addGroup(
                                    layout.createParallelGroup()
                                            .addComponent(btnConReserva, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSinReserva, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            )
            );

            add(panel, BorderLayout.CENTER);
        }

        private void cargarImagen(String path, int ancho, int alto) {
            ImageIcon original = new ImageIcon(path);
            if (original.getIconWidth() <= 0) {
                System.out.println("No se encontró la imagen en: " + path);
                return;
            }
            Image imgEscalada = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(imgEscalada));
            lblImagen.setBackground(Color.WHITE); // quita el gris si carga la imagen
        }
    }