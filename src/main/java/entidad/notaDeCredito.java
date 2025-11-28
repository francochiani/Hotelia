package entidad;

public class notaDeCredito {
    private long idNotaDeCredito;
    private long idFactura;
    private java.sql.Date fechaGeneracion;
    private double total;

    public notaDeCredito(long idNotaDeCredito, long idFactura, java.sql.Date fechaGeneracion, double total) {
        this.idNotaDeCredito = idNotaDeCredito;
        this.idFactura = idFactura;
        this.fechaGeneracion = fechaGeneracion;
        this.total = total;
    }

    public long getIdNotaDeCredito() {
        return idNotaDeCredito;
    }

    public void setIdNotaDeCredito(long idNotaDeCredito) {
        this.idNotaDeCredito = idNotaDeCredito;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }

    public java.sql.Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(java.sql.Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
