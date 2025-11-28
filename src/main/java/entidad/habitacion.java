package entidad;

public class habitacion {
    private Long idHabitacion;
    private String numero;
    private String tipoHabitacion;
    private int costo;

    public habitacion() {
    }

    public habitacion(Long idHabitacion, String numero, String tipoHabitacion) {
        this.idHabitacion = idHabitacion;
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        switch (tipoHabitacion){
            case "Individual Estandar": {this.costo = 50800;} break;
            case "Doble Estandar": {this.costo = 70230;} break;
            case "Doble Superior": {this.costo = 90560;} break;
            case "Superior Family Plan": {this.costo = 110500;} break;
            case "Suite Doble": {this.costo = 128600;} break;
        }
    }

    public Long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getCosto() {
        return costo;
    }

}
