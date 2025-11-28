package entidad;

public class tarjeta {
    private long numeroTarjeta;
    private long idPago;
    private String tipoTarjeta;
    private java.sql.Date fechaVencimiento;
    private int cvv;
    private String nombreTitular;

    public tarjeta(long numeroTarjeta, long idPago, String tipoTarjeta, java.sql.Date fechaVencimiento, int cvv, String nombreTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.idPago = idPago;
        this.tipoTarjeta = tipoTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.nombreTitular = nombreTitular;
    }

    public long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public java.sql.Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
}
