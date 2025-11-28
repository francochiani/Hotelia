package gestor;
import entidad.huesped;
import DAO.huespedDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class gestorHuesped {
    public boolean darDeAltaHuesped(String apellido, String nombres, String tipoDocumento, int nroDocumento,
                                     Date fechaNac, String nacionalidad,
                                     long telefono, String email,
                                     String ocupacion, String posicionIVA, long cuit, boolean responsableDePago,
                                     String calle, int numero, int depto, int piso, String localidad, String provincia,
                                     String pais, int codigoPostal){

        huesped huesped = new huesped(apellido, nombres, tipoDocumento, nroDocumento,
                fechaNac, nacionalidad, telefono, email, ocupacion, posicionIVA, cuit,
                responsableDePago, calle, numero, depto, piso, localidad, provincia, pais, codigoPostal);
        huespedDAO huespedDAO = new huespedDAO();

        huesped huespedExistente = huespedDAO.buscarporDNI(huesped.getNroDocumento());

        if (huespedExistente == null) {
            return huespedDAO.guardar(huesped);
        }
        else{
            JOptionPane.showMessageDialog(null, "Ya existe un húesped con el DNI ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void darDeBajaHuesped(){}

    private void modificarHuesped(){}

    public static List<Object[]> buscarHuesped(String apellido, String nombres, String DNI, String tipoDocumento){
        huespedDAO huespedDAO = new huespedDAO();
        List<huesped> lista = huespedDAO.buscarHuesped(apellido, nombres, DNI, tipoDocumento);
        List<Object[]> resultado = new ArrayList<>();

        // Convertir la lista de huespedes en lista de "objetos", ya que la UI NO conoce lo que es un 'Huesped'
        for (huesped h : lista) {

            Object[] fila = new Object[]{
                    h.getApellido(),
                    h.getNombres(),
                    h.getNroDocumento(),
                    h.getTipoDocumento()
            };

            resultado.add(fila);
        }

        if (resultado.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se encontraron Húespedes que cumplan los parámetros ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            return resultado;
        }
        else { return resultado; }

}};
