package interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class loginInterfaz extends JFrame {  
    public loginInterfaz() {
        // Configuración del marco de la ventana
        setTitle("Inicio de sesión");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(null); // Usar layout absoluto
        setResizable(false); // Deshabilitar la opción de maximizar
        setUndecorated(true); // Quitar la barra de título y los botones de ventana

        //Imagen superior
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        Image scaledImage = originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(65, 30, 280, 250);
        add(imageLabel);

        // Crear un panel para el área de inicio de sesión
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(55, 280, 300, 180);
        panel.setLayout(null); 
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 

        // Fondo negro detrás del título
        JPanel titleBackground = new JPanel();
        titleBackground.setBackground(Color.BLACK);
        titleBackground.setBounds(0, 10, 300, 30);
        titleBackground.setLayout(null);

        // Etiqueta de título
        JLabel titleLabel = new JLabel("Acceso al sistema");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(50, 0, 200, 30);
        titleBackground.add(titleLabel);
        panel.add(titleBackground);

        // Etiqueta de usuario
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(30, 50, 80, 25);
        panel.add(userLabel);

        // Campo de texto para el usuario
        JTextField userText = new JTextField();
        userText.setBounds(120, 50, 150, 25);
        panel.add(userText);

        // Etiqueta de contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setBounds(30, 90, 80, 25);
        panel.add(passwordLabel);

        // Campo de texto para la contraseña
        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(120, 90, 150, 25);
        panel.add(passwordText);

        // Botón de ingreso alineado a la derecha
        JButton loginButton = new JButton("Ingresar");
        loginButton.setBounds(90, 130, 100, 25);
        loginButton.addActionListener(e -> {
            String user = userText.getText();
            String password = new String(passwordText.getPassword());
            if (user.equals("conserje") && password.equals("conserje")) {
                menuPrincipal menu = new menuPrincipal();
                menu.setVisible(true);
                dispose();
            }else {
                JOptionPane.showMessageDialog(null, "El usuario o la contraseña no son válidos", "Error", JOptionPane.ERROR_MESSAGE);
                userText.setText("");
                passwordText.setText("");
            }
        });
        panel.add(loginButton);

        // Botón de salida a la derecha de "Ingresar"
        JButton exitButton = new JButton("Salir");
        exitButton.setBounds(200, 130, 70, 25);
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        panel.add(exitButton);

        add(panel);
    }
}

