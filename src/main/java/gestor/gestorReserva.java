package gestor;
import DAO.habitacionDAO;
import DAO.reservaDAO;
import entidad.reserva;

import java.time.LocalDate;
import java.util.List;

public class gestorReserva {

    private long DNI;

    public void registrarReservas(List<Object[]> datos, long DNI) {

        reservaDAO reservaDAO = new reservaDAO();

        for (Object[] fila : datos) {

            long idHabitacion = (long) fila[0];    // <-- AHORA ES LONG
            LocalDate desde = (LocalDate) fila[1];
            LocalDate hasta = (LocalDate) fila[2];
            Long huespedDni = this.DNI;

            // guardar reserva
            reservaDAO.guardar(idHabitacion, DNI, desde);
        }
    }

    public static class ReservaSeleccionada {
        public String numeroHabitacion;
        public LocalDate desde;
        public LocalDate hasta;

        public ReservaSeleccionada(String numeroHabitacion, LocalDate desde, LocalDate hasta) {
            this.numeroHabitacion = numeroHabitacion;
            this.desde = desde;
            this.hasta = hasta;
        }
    }
}
