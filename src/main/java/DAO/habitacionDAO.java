package DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import  entidad.habitacion;
import  entidad.estadoHabitacion;
import  DAO.conexionDB.conexion;

public class habitacionDAO {

    public List<habitacion> buscarPorTipo(String tipoHabitacion) {
        // busca todas las habitaciones de un tipo específico
        conexion conexion = new conexion();
        List<habitacion> lista = new ArrayList<>();
        String sql = "SELECT id_habitacion, numero, tipo_habitacion FROM habitacion WHERE tipo_habitacion = ?";
        try (Connection conn = conexion.establecerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipoHabitacion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                habitacion h = new habitacion();
                h.setIdHabitacion(rs.getLong("id_habitacion"));
                h.setNumero(rs.getString("numero"));
                h.setTipoHabitacion(rs.getString("tipo_habitacion"));
                lista.add(h);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<estadoHabitacion> obtenerEstadosRango(String tipo, LocalDate desde, LocalDate hasta) {
        // obtiene el estado de las habitacioes para un rango de fechas.
        List<estadoHabitacion> lista = new ArrayList<>();
        conexion conexion = new conexion();
        String sql =
                "SELECT h.numero, gs.fecha AS fecha, eh.estado " +
                        "FROM habitacion h " +
                        "LEFT JOIN estadohabitacion eh ON eh.id_habitacion = h.id_habitacion " +
                        "LEFT JOIN LATERAL generate_series( " +
                        "        eh.fecha_inicio, " +
                        "        COALESCE(eh.fecha_fin, eh.fecha_inicio), " +
                        "        interval '1 day' " +
                        "    ) AS gs(fecha) ON gs.fecha BETWEEN ? AND ? " +
                        "WHERE h.tipo_habitacion = ? " +
                        "ORDER BY h.numero, gs.fecha";

        try (Connection conn = conexion.establecerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(desde));
            ps.setDate(2, Date.valueOf(hasta));
            ps.setString(3, tipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String numero = rs.getString("numero");
                Date fechaSQL = rs.getDate("fecha"); // viene de generate_series
                String estado = rs.getString("estado");
                if (fechaSQL != null) {
                    lista.add(new estadoHabitacion(
                            numero,
                            fechaSQL.toLocalDate(),
                            estado == null ? "libre" : estado.toLowerCase()
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static long obtenerIdPorNumero(String numeroHabitacion) {
        // como en la UI se trabaja con numero de habitacion ( EJ: IE01 )
        // es necesario obtener el ID real de la habitación para las consultas SQL.
        String sql = "SELECT id_habitacion FROM habitacion WHERE numero = ?";
        try (Connection conn = new conexion().establecerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numeroHabitacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("id_habitacion");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private long obtenerUltimoId(Connection conn) throws SQLException {
        // Obtiene el último id usado
        String sql = "SELECT COALESCE(MAX(id), 0) FROM estadohabitacion";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        }
        return 0;
    }

    public void modificarEstadoHabitacion(long idHabitacion, String estado, LocalDate inicio, LocalDate fin) {
        // modifica el estado de la habitación según el estado actual
        String sqlBuscar =
                "SELECT id FROM estadohabitacion " +
                        "WHERE id_habitacion = ? AND fecha_inicio = ? AND fecha_fin = ?";
        String sqlInsert =
                "INSERT INTO estadohabitacion (id, id_habitacion, estado, fecha_inicio, fecha_fin) " +
                        "VALUES (?, ?, ?, ?, ?)";
        String sqlUpdate =
                "UPDATE estadohabitacion SET estado = ? " +
                        "WHERE id = ?";
        try (Connection conn = new conexion().establecerConexion()) {
            long idExistente = -1;

              // ===== BUSCAR SI YA EXISTE UN REGISTRO PARA ESAS FECHAS =====
            try (PreparedStatement psBuscar = conn.prepareStatement(sqlBuscar)) {

                psBuscar.setLong(1, idHabitacion);
                psBuscar.setDate(2, java.sql.Date.valueOf(inicio));
                psBuscar.setDate(3, java.sql.Date.valueOf(fin));
                try (ResultSet rs = psBuscar.executeQuery()) {
                    if (rs.next()) {
                        idExistente = rs.getLong("id");
                    }
                }
            }

            // SI EXISTE → UPDATE
            // la habitación esta como 'reservada' y se debe actualizar para pasar a 'ocupada'
            if (idExistente != -1) {
                try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
                    psUpdate.setString(1, estado);
                    psUpdate.setLong(2, idExistente);
                    psUpdate.executeUpdate();
                }
                return;
            }

            // SI NO EXISTE → INSERT
            // agregar una habitación como 'reservada'
            long nuevoId = obtenerUltimoId(conn) + 1;
            try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                psInsert.setLong(1, nuevoId);
                psInsert.setLong(2, idHabitacion);
                psInsert.setString(3, estado);
                psInsert.setDate(4, java.sql.Date.valueOf(inicio));
                psInsert.setDate(5, java.sql.Date.valueOf(fin));
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
