package entidad;

public class efectivo {
    private long idPago;
    private String moneda;
    private double cotizacion;

    public efectivo(long idPago, String moneda, double cotizacion) {
        this.idPago = idPago;
        this.moneda = moneda;
        this.cotizacion = cotizacion;
    }

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(double cotizacion) {
        this.cotizacion = cotizacion;
    }
}