package DAO;
import DAO.conexionDB.conexion;

import java.math.BigDecimal;
import java.sql.*;

public class estadiaDAO {
    public Long nuevaEstadia(long idReserva, long dni) {

        String sqlInsertEstadia =
                "INSERT INTO estadia (huesped_dni, valor) VALUES (?, ?)";

        String sqlActualizarReservaHab =
                "UPDATE reservaHabitacion SET id_estadia = ? WHERE id_reserva = ?";

        Connection conn = null;

        try {
            conn = new conexion().establecerConexion();
            conn.setAutoCommit(false); // transacción

            Long idEstadiaGenerado = null;

            try (PreparedStatement ps = conn.prepareStatement(sqlInsertEstadia, Statement.RETURN_GENERATED_KEYS)) {

                ps.setLong(1, dni);
                ps.setBigDecimal(2, BigDecimal.ZERO); // valor inicial

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idEstadiaGenerado = rs.getLong(1);
                    }
                }
            }

            if (idEstadiaGenerado == null)
                throw new SQLException("No se generó el ID de la estadía.");


            try (PreparedStatement ps = conn.prepareStatement(sqlActualizarReservaHab)) {
                ps.setLong(1, idEstadiaGenerado);
                ps.setLong(2, idReserva);
                ps.executeUpdate();
            }

            conn.commit();
            return idEstadiaGenerado;

        } catch (Exception e) {

            try {
                if (conn != null) conn.rollback();
            } catch (Exception ignored) {}

            e.printStackTrace();
            return null;

        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (Exception ignored) {}
        }
    }
}
