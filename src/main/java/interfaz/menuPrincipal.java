package interfaz;

import javax.management.remote.JMXConnectionNotification;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class menuPrincipal extends JFrame{
    public menuPrincipal() {
        // Crear un JDesktopPane
        JDesktopPane desktopPane = new JDesktopPane();
        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
                escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
                escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 277, Short.MAX_VALUE)
        );
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Menu Principal");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuHuesped = new JMenu("Huesped");
        menuBar.add(menuHuesped);
        JMenuItem buscarHuesped = new JMenuItem("# Buscar Huesped");//CU02 -> CU09, CU10, CU11
        buscarHuesped.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        menuHuesped.add(buscarHuesped);

        JMenu menuReserva = new JMenu("Reserva");
        JMenuItem reservarHabitacion = new JMenuItem("# Reservar Habitación"); //CU04 -> CU05
        JMenuItem cancelarReserva = new JMenuItem("Cancelar Reserva"); // CU06
        JMenuItem ocuparHabitacion = new JMenuItem("# Ocupar Habitacion");// CU15 -> CU05
        reservarHabitacion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        cancelarReserva.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        ocuparHabitacion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        menuReserva.add(reservarHabitacion);
        menuReserva.add(cancelarReserva);
        menuReserva.add(ocuparHabitacion);
        menuBar.add(menuReserva);

        JMenu menuFacturacion = new JMenu("Facturacion");
        JMenuItem facturar = new JMenuItem("Facturar"); // CU07
        JMenuItem buscarResponsablePago = new JMenuItem("Buscar Responsable de pago"); // CU03 -> CU12, CU13, CU14
        JMenuItem ingresarPago = new JMenuItem("Ingresar Pago");// CU16
        JMenuItem ingresarNotadeCredito = new JMenuItem("Ingresar Nota de Crédito"); // CU19
        JMenuItem gestionarListados = new JMenuItem("Gestionar listados"); // CU08 -> CU17, CU18
        facturar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        buscarResponsablePago.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        ingresarPago.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        ingresarNotadeCredito.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        gestionarListados.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "En desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }});
        menuFacturacion.add(facturar);
        menuFacturacion.add(buscarResponsablePago);
        menuFacturacion.add(ingresarPago);
        menuFacturacion.add(ingresarNotadeCredito);
        menuFacturacion.add(gestionarListados);
        menuBar.add(menuFacturacion);

        setJMenuBar(menuBar);


    }
}
