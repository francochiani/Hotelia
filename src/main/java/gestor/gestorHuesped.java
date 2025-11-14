package gestor;
import entidad.huesped;
import DAO.huespedDAO;

import javax.swing.*;
import java.util.Date;

public class gestorHuesped {
    public boolean darDeAltaHuesped(String apellido, String nombres, String tipoDocumento, int nroDocumento,
                                     Date fechaNac, String nacionalidad,
                                     long telefono, String email,
                                     String ocupacion, String posicionIVA, int cuit, boolean responsableDePago,
                                     String calle, int numero, String localidad, String provincia,
                                     String pais, int codigoPostal){

        huesped huesped = new huesped(apellido, nombres, tipoDocumento, nroDocumento,
                fechaNac, nacionalidad, telefono, email, ocupacion, posicionIVA, cuit,
                responsableDePago, calle, numero, localidad, provincia, pais, codigoPostal);
        huespedDAO huespedDAO = new huespedDAO();

        huesped huespedExistente = huespedDAO.buscarporDNI(huesped.getNroDocumento());

        if (huespedExistente == null) {
            return huespedDAO.guardar(huesped);
        }
        else{
            JOptionPane.showMessageDialog(null, "Ya existe un húesped con el DNI ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    };

    private void darDeBajaHuesped(){};

    private void modificarHuesped(){}

    private void buscarHuesped(){}
}
