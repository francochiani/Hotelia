package gestor;

import DAO.reservaDAO;
import entidad.reservaHabitacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class gestorReserva {

    private long DNI;

    public void registrarReservas(List<Object[]> datos, long DNI) {
        reservaDAO reservaDAO = new reservaDAO();
        for (Object[] fila : datos) {
            long idHabitacion = (long) fila[0];
            LocalDate desde = (LocalDate) fila[1];
            LocalDate hasta = (LocalDate) fila[2];
            Long huespedDni = this.DNI;
            reservaDAO.guardar(idHabitacion, DNI, desde, hasta);
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
