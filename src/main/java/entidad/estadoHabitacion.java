package entidad;

import java.time.LocalDate;

public class estadoHabitacion {
    private String numero;
    private LocalDate fecha;
    private String estado;

    public estadoHabitacion(String numero, LocalDate fecha, String estado) {
        this.numero = numero;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getNumero() { return numero; }
    public LocalDate getFecha() { return fecha; }
    public String getEstado() { return estado; }
}
