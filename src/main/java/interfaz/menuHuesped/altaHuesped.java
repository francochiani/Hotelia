package interfaz.menuHuesped;
import gestor.gestorHuesped;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class altaHuesped extends JInternalFrame {
    private final int TOTAL_COLUMNS = 12; // unidad base para repartir anchuras

    public altaHuesped() {
        setTitle("Alta de Huésped");
        setResizable(false);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 580);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(root);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.BOTH;

        JLabel title = new JLabel("Alta de Huésped");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 0));
        root.add(title, BorderLayout.NORTH);

        JPanel datos = new JPanel(new GridBagLayout());
        JScrollPane scroll = new JScrollPane(datos);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 12, 5, 12));
        root.add(scroll, BorderLayout.CENTER);

        int row = 0;

        // FILA 1 -> 3 campos (deben repartirse el ancho en 3 partes iguales)
        List<Field> fila1 = new ArrayList<>();
        JTextField txtApellido = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtNacimiento = new JTextField();
        fila1.add(new Field("Apellido *", txtApellido));
        fila1.add(new Field("Nombres *",txtNombre));
        fila1.add(new Field("Fecha de Nacimiento * (dd/mm/aaaa)", txtNacimiento));
        addRow(datos, gbc, row++, fila1);

        // FILA 2 -> 3 campos
        List<Field> fila2 = new ArrayList<>();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"DNI", "LC", "LE","Pasaporte","Otro"});
        JTextField txtDNI = new JTextField();
        JTextField txtNacionalidad = new JTextField();
        fila2.add(new Field("Tipo de Documento *", comboTipo));
        fila2.add(new Field("Número *", txtDNI));
        fila2.add(new Field("Nacionalidad *", txtNacionalidad));
        addRow(datos, gbc, row++, fila2);

        // FILA 3 -> 5 campos
        List<Field> fila3 = new ArrayList<>();
        JTextField txtCalle = new JTextField();
        JTextField txtNumero = new JTextField();
        JTextField txtPostal = new JTextField();
        fila3.add(new Field("Calle *", txtCalle));
        fila3.add(new Field("Número *", txtNumero));
        fila3.add(new Field("Dpto.", new JTextField()));
        fila3.add(new Field("Piso", new JTextField()));
        fila3.add(new Field("Código Postal *", txtPostal));
        addRow(datos, gbc, row++, fila3);

        // FILA 4 -> 3 campos (país/provincia/localidad)
        List<Field> fila4 = new ArrayList<>();
        JComboBox<String> comboPais = new JComboBox<>(new String[]{"Argentina", "Uruguay", "Chile"});
        JComboBox<String> comboProvincia = new JComboBox<>(new String[]{"Santa Fe", "Buenos Aires", "Córdoba"});
        JComboBox<String> comboLocalidad = new JComboBox<>(new String[]{"Santa Fe", "Rosario", "Rafaela"});
        fila4.add(new Field("País *", comboPais));
        fila4.add(new Field("Provincia *", comboProvincia));
        fila4.add(new Field("Localidad *", comboLocalidad));
        addRow(datos, gbc, row++, fila4);

        // FILA 5 -> 2 campos (teléfono / email)
        List<Field> fila5 = new ArrayList<>();
        JTextField txtTelefono = new JTextField();
        //     txtTelefono.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        fila5.add(new Field("Teléfono *", txtTelefono));
        fila5.add(new Field("Email", new JTextField()));
        addRow(datos, gbc, row++, fila5);

        // FILA 6 -> 3 campos (ocupación / iva / cuit)
        List<Field> fila6 = new ArrayList<>();
        JTextField txtOcupacion = new JTextField();
        JComboBox<String> comboIVA = new JComboBox<>(new String[]{"Consumidor Final", "Monotributista", "Responsable Inscripto"});
        fila6.add(new Field("Ocupación *", txtOcupacion));
        fila6.add(new Field("Posición frente al IVA *", comboIVA));
        fila6.add(new Field("C.U.I.T.", new JTextField()));
        addRow(datos, gbc, row++, fila6);

        // Nota centrada que ocupa todo el ancho
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = TOTAL_COLUMNS;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel nota = new JLabel("Los campos marcados con * son obligatorios", SwingConstants.LEFT);
        nota.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        datos.add(nota, gbc);

        // Botones al final (en panel separado)
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 6));
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setPreferredSize(new Dimension(110, 30));

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(110, 30));

        botones.add(btnSiguiente);
        botones.add(btnCancelar);

        btnSiguiente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean valido = true;
                valido &= validarCampo(txtApellido, "string");
                valido &= validarCampo(txtNombre, "string");
                valido &= validarCampo(txtNacimiento, "date");
                valido &= validarCampo(txtDNI, "int");
                valido &= validarCampo(txtNacionalidad, "string");
                valido &= validarCampo(txtCalle, "string");
                valido &= validarCampo(txtNumero, "int");
                valido &= validarCampo(txtPostal, "int");
                valido &= validarCampo(txtTelefono, "int");
                valido &= validarCampo(txtOcupacion, "string");
                if (!valido) {
                    JOptionPane.showMessageDialog(null, "Hay campos incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    //sarasa para cargar los datos en la base
                    int opcion = JOptionPane.showOptionDialog(
                            null,                               // componente padre
                            "El huésped " + txtNombre.getText() + " " + txtApellido.getText() + " ha sido\n" +
                                    "cargado satisfactoriamente en el sistema.\n" +
                                    " ¿Desea cargar otro?",    // mensaje
                            "Carga exitosa",                 // título
                            JOptionPane.YES_NO_OPTION,          // tipo de opción
                            JOptionPane.QUESTION_MESSAGE,       // tipo de ícono
                            null,                               // ícono personalizado (null = por defecto)
                            new Object[]{"SI", "NO"},           // etiquetas personalizadas de botones
                            "SI"                                // botón por defecto
                    );
                    if (opcion == 0) {
                        altaHuesped Ventana = new altaHuesped();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(Ventana);
                        Ventana.setVisible(true);
                        dispose();
                    }
                    else{
                        dispose();
                    }
                }
            }});
        btnCancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int opcion = JOptionPane.showOptionDialog(
                        null,                               // componente padre
                        "¿Desea cancelar el alta del huésped?",    // mensaje
                        "Cancelar alta",                 // título
                        JOptionPane.YES_NO_OPTION,          // tipo de opción
                        JOptionPane.QUESTION_MESSAGE,       // tipo de ícono
                        null,                               // ícono personalizado (null = por defecto)
                        new Object[]{"SI", "NO"},           // etiquetas personalizadas de botones
                        "SI"                                // botón por defecto
                );
                if (opcion == 0) {
                    dispose();
                }
            }});
        root.add(botones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private boolean validarCampo(JTextField campo, String tipoDato) {
        if (campo.getText().trim().isEmpty()) {
            campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return false;
        } else if ((!(campo.getText().trim().matches("\\d+")) && (tipoDato.equals("int")))) {
            campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return false;
        } else if ((!(campo.getText().trim().matches("\\d{2}/\\d{2}/\\d{4}")) && (tipoDato.equals("date")))) {
            campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return false;
        } else {
            campo.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            return true;
        }
    }

    /**
     * Añade una fila al panel usando GridBagLayout repartiendo TOTAL_COLUMNS entre la cantidad de campos.
     * Cada campo recibe gridwidth = span (número entero) y weightx = span para que se escale proporcionalmente.
     */
    private void addRow(JPanel panel, GridBagConstraints gbcBase, int rowIndex, List<Field> campos) {
        int n = campos.size();
        int base = TOTAL_COLUMNS / n;
        int rem = TOTAL_COLUMNS % n;

        // calcular spans: repartir residuo a las primeras columnas
        int[] spans = new int[n];
        for (int i = 0; i < n; i++) {
            spans[i] = base + (i < rem ? 1 : 0);
        }

        int gridx = 0;
        for (int i = 0; i < n; i++) {
            Field f = campos.get(i);

            GridBagConstraints gbc = (GridBagConstraints) gbcBase.clone();
            gbc.gridx = gridx;
            gbc.gridy = rowIndex;
            gbc.gridwidth = spans[i];
            gbc.weightx = spans[i]; // peso proporcional a las columnas ocupadas
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Panel contenedor vertical (label arriba, campo abajo)
            JPanel cell = new JPanel(new BorderLayout(4, 4));
            JLabel lbl = new JLabel(f.label);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            cell.add(lbl, BorderLayout.NORTH);

            // poner el campo en centro con padding para no forzar alto
            JPanel holder = new JPanel(new BorderLayout());
            holder.add(f.component, BorderLayout.CENTER);
            cell.add(holder, BorderLayout.CENTER);

            // opción: fijar tamaño preferido razonable para los campos (ajustable)
            f.component.setPreferredSize(new Dimension(160 * spans[i] / (TOTAL_COLUMNS / 3), 26));

            panel.add(cell, gbc);

            gridx += spans[i]; // avanzar columna
        }
    }

    private static class Field {
        String label;
        JComponent component;
        Field(String label, JComponent component) {
            this.label = label;
            this.component = component;
        }
    }
}