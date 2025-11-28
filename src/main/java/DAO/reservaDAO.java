package DAO;


import DAO.conexionDB.conexion;
import entidad.reservaHabitacion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class reservaDAO {
    public void guardar(long habitacion, long dni, LocalDate desde, LocalDate hasta) {
        // crea una nueva reserva y reservaHabitacion en la base de datos
        String sqlReserva = "INSERT INTO reserva (huesped_dni, fecha_reserva, cancelada) VALUES (?, ?, false)";
        String sqlReservaHab = "INSERT INTO reservaHabitacion (id_reserva, id_habitacion, id_estadia, fecha_ingreso, fecha_egreso) VALUES (?, ?, NULL, ?, ?)";

        try (Connection conn = new conexion().establecerConexion();
             PreparedStatement psReserva = conn.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS)) {
            // ===== INSERT EN TABLA reserva =====
            psReserva.setLong(1, dni);
            psReserva.setDate(2, Date.valueOf(desde));
            psReserva.executeUpdate();
            long idReservaGenerado = -1;

            try (ResultSet rs = psReserva.getGeneratedKeys()) {
                if (rs.next()) {
                    idReservaGenerado = rs.getLong(1);
                }
            }

            if (idReservaGenerado == -1) {
                System.err.println("No se pudo obtener el ID de la reserva generada.");
                return;
            }

            // ===== INSERT EN TABLA reservaHabitacion =====
            try (PreparedStatement psResHab = conn.prepareStatement(sqlReservaHab)) {
                psResHab.setLong(1, idReservaGenerado);        // id_reserva
                psResHab.setLong(2, habitacion);               // id_habitacion
                psResHab.setDate(3, Date.valueOf(desde));      // fecha_ingreso
                psResHab.setDate(4, Date.valueOf(hasta));      // fecha_egreso
                psResHab.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<reservaHabitacion> buscarPorHuesped(Long DNI) {
        // buscar todas las reservas que posee un huesped
        List<reservaHabitacion> lista = new ArrayList<>();
        String sql =
                "SELECT rh.id, rh.id_reserva, rh.id_habitacion, rh.id_estadia, rh.fecha_ingreso, rh.fecha_egreso " +
                        "FROM reservaHabitacion rh " +
                        "INNER JOIN reserva r ON r.id_reserva = rh.id_reserva " +
                        "WHERE r.huesped_dni = ? and rh.id_estadia IS NULL";

        try (Connection conn = new conexion().establecerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, DNI);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reservaHabitacion rh = new reservaHabitacion();
                    rh.setId(rs.getLong("id"));
                    rh.setIdReserva(rs.getLong("id_reserva"));
                    rh.setIdHabitacion(rs.getLong("id_habitacion"));
                    long estadia = rs.getLong("id_estadia");
                    rh.setIdEstadia(rs.wasNull() ? null : estadia); // id_estadia puede ser NULL
                    rh.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
                    rh.setFechaEgreso(rs.getDate("fecha_egreso").toLocalDate());
                    lista.add(rh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(reservaHabitacion r){}

    public void eliminar(Long IDReserva){}

    public void buscarPorID(Long IDReserva){}
}
