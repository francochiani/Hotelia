package entidad;
import java.time.LocalDate;

public class reserva {
    private long id;
    private long idHabitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public reserva(long idHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idHabitacion = idHabitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public reserva() {
    }

    public long getId() {
        return id;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}