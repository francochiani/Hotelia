package DAO;

import entidad.reserva;
import DAO.conexionDB.conexion;

import java.sql.*;
import java.time.LocalDate;

public class reservaDAO {
    public void guardar(long habitacion, long dni, LocalDate fecha) {

        String sql = "INSERT INTO reserva (huesped_dni, fecha_reserva, cancelada) VALUES (?, ?, false)";

        try (Connection conn = new conexion().establecerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, dni);
            ps.setDate(2, Date.valueOf(fecha));

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
