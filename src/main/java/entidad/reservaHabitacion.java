package entidad;

import java.time.LocalDate;

public class reservaHabitacion {

    private long id;
    private long idReserva;
    private long idHabitacion;
    private Long idEstadia;       // puede ser null
    private LocalDate fechaIngreso;
    private LocalDate fechaEgreso;

    // Constructor vacío
    public reservaHabitacion() {
    }

    // Constructor con todos los campos
    public reservaHabitacion(long id, long idReserva, long idHabitacion, Long idEstadia,
                             LocalDate fechaIngreso, LocalDate fechaEgreso) {
        this.id = id;
        this.idReserva = idReserva;
        this.idHabitacion = idHabitacion;
        this.idEstadia = idEstadia;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Long getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(Long idEstadia) {
        this.idEstadia = idEstadia;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }
}
