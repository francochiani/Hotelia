package DAO;
import DAO.conexionDB.conexion;
import entidad.huesped;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class huespedDAO {

    public boolean guardar(huesped Huesped){
        // guardar un nuevo huesped en la base de datos
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
            cs3.setInt(4,Huesped.getDireccion().getDepto());
            cs3.setInt(5, Huesped.getDireccion().getPiso());
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

    private void actualizar(huesped h){}

    private void eliminar(long DNI){}

    public huesped buscarporDNI(int DNI) {
        // buscar un huesped por su DNI
        conexion objetoconexion = new conexion();
        huesped resultado;
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

    public List<huesped> buscarHuesped(String apellido, String nombres, String DNI, String tipoDocumento){
        // buscar un huesped
        conexion objetoconexion = new conexion();
        List<huesped> lista = new ArrayList<>();

        String sql = "SELECT * FROM huesped WHERE 1 = 1";
        List<Object> params = new ArrayList<>();

        // Filtro DNI
        if (DNI!=null && !DNI.trim().isEmpty()) {
            sql += " AND DNI = ?";
            params.add(DNI);
        }

        // Filtro Nombre (LIKE para coincidencias parciales)
        if (nombres != null && !nombres.trim().isEmpty()) {
            sql += " AND nombres LIKE ?";
            params.add("%" + nombres + "%");
        }

        // Filtro Apellido
        if (apellido != null && !apellido.trim().isEmpty()) {
            sql += " AND apellido LIKE ?";
            params.add("%" + apellido + "%");
        }

        // Filtro Tipo Documento
        if (tipoDocumento != null && !tipoDocumento.trim().isEmpty()) {
            sql += " AND tipo_Documento = ?";
            params.add(tipoDocumento);
        }
        // si todos los if dan FALSE retorna todos los huespedes por la condición WHERE 1 = 1

        try {
            Connection conn = objetoconexion.establecerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                huesped h = new huesped();
                h.setNroDocumento(rs.getInt("DNI"));
                h.setApellido(rs.getString("apellido"));
                h.setNombres(rs.getString("nombres"));
                h.setTipoDocumento(rs.getString("tipo_Documento"));
                lista.add(h);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
