package entidad;

public class factura {
    private long idFactura;
    private long idEstadia;
    private String tipo;
    private double totalFacturado;
    private java.sql.Date fechaGeneracion;
    private long cuit;

    public factura(long idFactura, long idEstadia, String tipo, double totalFacturado, java.sql.Date fechaGeneracion, long cuit) {
        this.idFactura = idFactura;
        this.idEstadia = idEstadia;
        this.tipo = tipo;
        this.totalFacturado = totalFacturado;
        this.fechaGeneracion = fechaGeneracion;
        this.cuit = cuit;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }

    public long getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(long idEstadia) {
        this.idEstadia = idEstadia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTotalFacturado() {
        return totalFacturado;
    }

    public void setTotalFacturado(double totalFacturado) {
        this.totalFacturado = totalFacturado;
    }

    public java.sql.Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(java.sql.Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
}
