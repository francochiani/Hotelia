package interfaz.menuReserva;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class finalizarReserva extends JInternalFrame {

    private reservaFinalListener listener;
    private JTextField txtDni;
    private JTextField txtApellido;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JComboBox<String> comboPrefijo;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    // Bordes para restaurar
    private final Border bordeNormal = UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border");
    private final Border bordeError = BorderFactory.createLineBorder(Color.RED, 2);

    public finalizarReserva() {
        setClosable(false);
        setIconifiable(false);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Finalizar Reserva");
        setSize(320, 430);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("Reserva a nombre de:");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel lblDni = new JLabel("DNI*");
        txtDni = new JTextField();
        txtDni.setPreferredSize(new Dimension(200, 35));

        JLabel lblApellido = new JLabel("Apellido *");
        txtApellido = new JTextField();
        txtApellido.setPreferredSize(new Dimension(200, 35));

        JLabel lblNombre = new JLabel("Nombre *");
        txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 35));

        JLabel lblTelefono = new JLabel("Teléfono *");
        comboPrefijo = new JComboBox<>(new String[]{"+54", "+598", "+595"});
        comboPrefijo.setPreferredSize(new Dimension(70, 35));

        txtTelefono = new JTextField();
        txtTelefono.setPreferredSize(new Dimension(200, 35));

        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");
        btnConfirmar.addActionListener(e -> confirmar());
        btnCancelar.addActionListener(e -> cancelar());

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)

                        .addComponent(lblTitulo)

                        .addComponent(lblDni)
                        .addComponent(txtDni)

                        .addComponent(lblApellido)
                        .addComponent(txtApellido)

                        .addComponent(lblNombre)
                        .addComponent(txtNombre)

                        .addComponent(lblTelefono)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(comboPrefijo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono)
                        )

                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()

                        .addComponent(lblTitulo)

                        .addGap(15)
                        .addComponent(lblDni)
                        .addComponent(txtDni, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)

                        .addComponent(lblApellido)
                        .addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)

                        .addComponent(lblNombre)
                        .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)

                        .addComponent(lblTelefono)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(comboPrefijo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                        )

                        .addGap(25)
                        .addGroup(
                                layout.createParallelGroup()
                                        .addComponent(btnConfirmar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        )
        );

        add(panel, BorderLayout.CENTER);
    }

    private void confirmar() {
        resetearBordes();

        String dniTxt = txtDni.getText().trim();
        String apellido = txtApellido.getText().trim();
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Validaciones básicas
        if (dniTxt.isEmpty() || apellido.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {

            if (dniTxt.isEmpty()) txtDni.setBorder(bordeError);
            if (apellido.isEmpty()) txtApellido.setBorder(bordeError);
            if (nombre.isEmpty()) txtNombre.setBorder(bordeError);
            if (telefono.isEmpty()) txtTelefono.setBorder(bordeError);

            JOptionPane.showMessageDialog(this,
                    "Debe completar todos los campos obligatorios marcados con *.",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }
        long dni;
        try {
            dni = Long.parseLong(txtDni.getText().trim());
        } catch (NumberFormatException e) {
            txtDni.setBorder(bordeError);
            JOptionPane.showMessageDialog(this,
                    "El DNI debe ser un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Long.parseLong(telefono);
        } catch (NumberFormatException e) {
            txtTelefono.setBorder(bordeError);
            JOptionPane.showMessageDialog(this,
                    "El teléfono debe contener solo números.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (listener != null) {
            listener.onReservaFinalizada(dni);
        }
    }

    private void cancelar() {
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
            dispose();
        }
    }
    public void setReservaFinalListener(reservaFinalListener l) {
        this.listener = l;
    }

    private void resetearBordes() {
        txtDni.setBorder(bordeNormal);
        txtApellido.setBorder(bordeNormal);
        txtNombre.setBorder(bordeNormal);
        txtTelefono.setBorder(bordeNormal);
    }
}

