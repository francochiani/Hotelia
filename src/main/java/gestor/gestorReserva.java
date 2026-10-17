package gestor;

import DAO.reservaDAO;
import entidad.reservaHabitacion;
import java.sql.Connection;
import DAO.conexionDB.conexion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class gestorReserva {

    private long DNI;

    public void registrarReservas(List<Object[]> datos, long DNI) {
        reservaDAO reservaDAO = new reservaDAO();
        try (Connection conn = new conexion().establecerConexion()) {
            for (Object[] fila : datos) {
                long idHabitacion = (long) fila[0];
                LocalDate desde = (LocalDate) fila[1];
                LocalDate hasta = (LocalDate) fila[2];
                Long huespedDni = this.DNI;
                // Bolt Optimization: Pass single DB Connection to eliminate N+1 overhead
                reservaDAO.guardar(conn, idHabitacion, DNI, desde, hasta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> buscarReserva(long DNI) {
        List<reservaHabitacion> reservas;
        reservas = reservaDAO.buscarPorHuesped(DNI);

        // Lista de salida convertida a Object[]
        List<Object[]> resultado = new ArrayList<>();

        for (reservaHabitacion r : reservas) {

            Object[] fila = {
                    r.getIdHabitacion(),
                    r.getFechaIngreso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+" 12:00 hs.",
                    r.getFechaEgreso().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ " 10:00 hs.",
                    r.getIdReserva()
            };

            resultado.add(fila);
        }
        return resultado;
    }

    public void cancelarReserva(long IDReserva){}

    public void confirmarReserva(long IDReserva){}
}
