package entidad;

public class pago {
    private long idPago;
    private long idFactura;
    private java.sql.Date fechaPago;
    private double totalPagado;

    public pago(long idPago, long idFactura, java.sql.Date fechaPago, double totalPagado) {
        this.idPago = idPago;
        this.idFactura = idFactura;
        this.fechaPago = fechaPago;
        this.totalPagado = totalPagado;
    }

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }

    public java.sql.Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(java.sql.Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }
}