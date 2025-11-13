package interfaz.menuHuesped;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Objects;

public class buscarHuesped extends JInternalFrame{
    public buscarHuesped() {
            setTitle("Gestión de Huéspedes");
            setResizable(false);
            setClosable(true);
            setIconifiable(true);
            setSize(950, 550);
            setLayout(new BorderLayout());
            setBackground(new Color(0xfef7e9));
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            initComponents();
        }

        private void initComponents() {

            // === Panel izquierdo ===
            JPanel panelIzquierdo = new JPanel(new BorderLayout());
            panelIzquierdo.setBackground(Color.WHITE);
            panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            // ===============================
            // Panel de DATOS DEL HUÉSPED
            // ===============================
            JPanel panelDatos = new JPanel(new GridBagLayout());
            panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del húesped"));
            panelDatos.setBackground(Color.WHITE);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            Dimension campoTam = new Dimension(150, 28);  // Tamaño uniforme
            Dimension botonTam = new Dimension(120, 30);

            JTextField txtApellido = new JTextField();
            txtApellido.setMaximumSize(campoTam);
            JTextField txtNombres = new JTextField();
            txtNombres.setMaximumSize(campoTam);
            JComboBox<String> cmbTipoDocumento = new JComboBox<>(new String[]{"DNI", "LC", "LE", "Pasaporte", "Otro"});
            cmbTipoDocumento.setMaximumSize(campoTam);
            JTextField txtNroDocumento = new JTextField();
            txtNroDocumento.setMaximumSize(campoTam);

            JButton btnBuscar = new JButton("Buscar Huésped");
            btnBuscar.setMaximumSize(botonTam);
            JButton btnModificar = new JButton("Modificar");
            btnModificar.setMaximumSize(botonTam);
            JButton btnEliminar = new JButton("Eliminar");
            btnEliminar.setMaximumSize(botonTam);

            // === Fila 1: Apellido ===
            gbc.gridx = 0; gbc.gridy = 0;
            panelDatos.add(new JLabel("Apellido:"), gbc);
            gbc.gridx = 1;
            panelDatos.add(txtApellido, gbc);
            panelDatos.add(Box.createVerticalStrut(45));

            // === Fila 2: Nombres ===
            gbc.gridx = 0; gbc.gridy++;
            panelDatos.add(new JLabel("Nombres:"), gbc);
            gbc.gridx = 1;
            panelDatos.add(txtNombres, gbc);
            panelDatos.add(Box.createVerticalStrut(45));

            // === Fila 3: Tipo Documento ===
            gbc.gridx = 0; gbc.gridy++;
            panelDatos.add(new JLabel("Tipo de documento:"), gbc);
            gbc.gridx = 1;
            panelDatos.add(cmbTipoDocumento, gbc);
            panelDatos.add(Box.createVerticalStrut(45));

            // === Fila 4: Nro Documento ===
            gbc.gridx = 0; gbc.gridy++;
            panelDatos.add(new JLabel("Nro de documento:"), gbc);
            gbc.gridx = 1;
            panelDatos.add(txtNroDocumento, gbc);
            panelDatos.add(Box.createVerticalStrut(45));

             // === Fila 5: Botones
            gbc.gridx = 0; gbc.gridy++;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.CENTER;

            JPanel panelBotones = new JPanel();
            panelBotones.setBackground(Color.WHITE);
            panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

            // --- Botón Buscar (ocupa todo el ancho) ---
            btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnBuscar.setMaximumSize(new Dimension(230, 30));
            btnBuscar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
                }});
            panelBotones.add(Box.createVerticalStrut(20));
            panelBotones.add(btnBuscar);
            panelBotones.add(Box.createVerticalStrut(20));

            // --- Panel interno para los dos botones inferiores ---
            JPanel panelInferiorBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            panelInferiorBotones.setBackground(Color.WHITE);

            btnModificar.setPreferredSize(new Dimension(110, 30));
            btnModificar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
                }});
            btnEliminar.setPreferredSize(new Dimension(110, 30));
            btnEliminar.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
                }});

            panelInferiorBotones.add(btnModificar);
            panelInferiorBotones.add(btnEliminar);

            panelBotones.add(panelInferiorBotones);

            panelDatos.add(panelBotones, gbc);

            // ===============================
            // Panel inferior: nuevo huésped + imagen
            // ===============================
            JPanel panelInferior = new JPanel();
            panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
            panelInferior.setBackground(Color.WHITE);

            JButton btnNuevo = new JButton("Nuevo Huésped");
            btnNuevo.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnNuevo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35)); // ancho total
            btnNuevo.setPreferredSize(new Dimension(110, 35));
            btnNuevo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    altaHuesped Ventana = new altaHuesped();
                    JDesktopPane desktopPane = getDesktopPane();
                    desktopPane.add(Ventana);
                    Ventana.setVisible(true);
                    try {
                        setIcon(true);
                    } catch (PropertyVetoException ex) {
                        throw new RuntimeException(ex);
                    }
                }});

            ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/banner.png")));
            Image scaledImage = originalIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setBounds(15, 30, 200, 120);

            panelInferior.add(btnNuevo);
            panelInferior.add(Box.createVerticalStrut(15));
            panelInferior.add(imageLabel);

            // Combinar ambos en la izquierda
            panelIzquierdo.add(panelDatos, BorderLayout.NORTH);
            panelIzquierdo.add(panelInferior, BorderLayout.SOUTH);

            // ===============================
            // Panel derecho: tabla de huéspedes
            // ===============================
            String[] columnas = {"Apellido", "Nombre", "Tipo de Documento", "Número de Documento"};
            DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
            JTable tablaHuespedes = new JTable(modeloTabla);
            JScrollPane scrollTabla = new JScrollPane(tablaHuespedes);

            // Agregar a la vista principal
            add(panelIzquierdo, BorderLayout.WEST);
            add(scrollTabla, BorderLayout.CENTER);
        }
    }
