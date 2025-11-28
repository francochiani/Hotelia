package interfaz.menuReserva;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import gestor.gestorReserva;

public class buscarReserva extends JInternalFrame {

    private JTextField txtDni;

    public buscarReserva() {
        setTitle("Buscar Reserva");
        setSize(300, 170);
        setClosable(true);
        setIconifiable(true);
        setResizable(false);

        setLayout(new BorderLayout());

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Buscar Reserva", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        add(lblTitulo, BorderLayout.NORTH);

        // --- PANEL CENTRAL COMPACTO ---
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lblTexto = new JLabel("Ingrese DNI del huésped que realizó la reserva");
        lblTexto.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTexto2 = new JLabel("la reserva");
        lblTexto2.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTexto2.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtDni = new JTextField();
        txtDni.setMaximumSize(new Dimension(150, 24));
        txtDni.setHorizontalAlignment(JTextField.CENTER);
        txtDni.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        panelCentral.add(lblTexto);
        panelCentral.add(lblTexto2);
        panelCentral.add(Box.createVerticalStrut(5)); // espacio mínimo
        panelCentral.add(txtDni);

        add(panelCentral, BorderLayout.CENTER);

        // --- BOTONES MUY JUNTOS ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(90, 25));

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(90, 25));

        panelBotones.add(btnBuscar);
        panelBotones.add(btnCancelar);

        panelBotones.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        add(panelBotones, BorderLayout.SOUTH);

        // --- EVENTOS ---
        btnBuscar.addActionListener(e -> validarDni());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void validarDni() {
        String dni = txtDni.getText().trim();
        txtDni.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        if (dni.isEmpty()) {
            mostrarError("El DNI no puede estar vacío.");
            return;
        }
        if (!dni.matches("\\d+")) {
            mostrarError("El DNI solo debe contener números.");
            return;
        }
        if (dni.length() < 7 || dni.length() > 9) {
            mostrarError("El DNI no es válido.");
            return;
        }

        gestorReserva gestorReserva = new gestorReserva();
        List<Object[]> listadoReservas = gestorReserva.buscarReserva(Long.parseLong(dni));

        if(listadoReservas==null){
            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "El húesped no posee reservas realizadas ¿Desea reservar una habitación?",
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
        }
        else{
            listadoReservas Ventana = new listadoReservas(listadoReservas,dni);
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.add(Ventana);
            Ventana.setVisible(true);
            this.dispose();
    }}

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        txtDni.setBorder(new LineBorder(Color.RED, 2));
        txtDni.requestFocus();
    }
}
