package DAO;
import DAO.conexionDB.conexion;
import entidad.huesped;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class huespedDAO {
    public boolean guardar(huesped Huesped){
        conexion objetoconexion = new conexion();
        String consultaHuesped = "insert into huesped values (?, ?, ?, ?, ?, ?);";
        String consultaContacto = "insert into contacto values (?, ?, ?)";
        String consultaDireccion = "insert into direccion values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            CallableStatement cs = objetoconexion.establecerConexion().prepareCall(consultaHuesped);
            cs.setInt(1, Huesped.getNroDocumento());
            cs.setString(2,Huesped.getTipoDocumento());
            cs.setString(3,Huesped.getApellido());
            cs.setString(4,Huesped.getNombres());
            cs.setDate(5, (Date) Huesped.getFechaNac());
            cs.setString(6, Huesped.getNacionalidad());

            CallableStatement cs2 = objetoconexion.establecerConexion().prepareCall(consultaContacto);
            cs2.setInt(1, Huesped.getNroDocumento());
            cs2.setLong(2,Huesped.getContacto().getTelefono());
            cs2.setString(3,Huesped.getContacto().getEmail());

            CallableStatement cs3 = objetoconexion.establecerConexion().prepareCall(consultaDireccion);
            cs3.setInt(1, Huesped.getNroDocumento());
            cs3.setString(2,Huesped.getDireccion().getCalle());
            cs3.setInt(3,Huesped.getDireccion().getNumero());
            cs3.setInt(4,0);
            cs3.setInt(5, 0);
            cs3.setInt(6, Huesped.getDireccion().getCodigoPostal());
            cs3.setString(7,Huesped.getDireccion().getLocalidad());
            cs3.setString(8, Huesped.getDireccion().getProvincia());
            cs3.setString(9, Huesped.getDireccion().getPais());

            cs.execute();
            cs2.execute();
            cs3.execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void actualizar(){}

    private void eliminar(){}

    public huesped buscarporDNI(int DNI) {
        conexion objetoconexion = new conexion();
        huesped resultado = null;
        try {
            Statement st = objetoconexion.establecerConexion().createStatement();
            String consultaHuesped = "select * from huesped where dni =" + DNI;
            ResultSet rs = st.executeQuery(consultaHuesped);
            if (!rs.next()) {
                return null;
            }
            else {
                 resultado = new huesped(
                        rs.getString("apellido"),        // String apellido
                        rs.getString("nombres"),          // String nombres
                        rs.getString("tipo_documento"),   // String tipoDocumento
                        rs.getInt("dni"),                // int nroDocumento
                        rs.getDate("fecha_nacimiento"),          // Date fechaNac
                        rs.getString("nacionalidad")     // String nacionalidad
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
    private ArrayList<huesped> buscarTodos(){
        return null;
    }
}
