package entidad;

public class huespedIVA {
    private String ocupacion;
    private String posicionIVA;
    private long cuit;
    private boolean responsableDePago;

    public huespedIVA(String ocupacion, String posicionIVA, long cuit, boolean responsableDePago) {
        this.ocupacion = ocupacion;
        this.posicionIVA = posicionIVA;
        this.cuit = cuit;
        this.responsableDePago = responsableDePago;
    }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getPosicionIVA() { return posicionIVA; }
    public void setPosicionIVA(String posicionIVA) { this.posicionIVA = posicionIVA; }

    public long getCuit() { return cuit; }
    public void setCuit(long cuit) { this.cuit = cuit; }

    public boolean isResponsableDePago() { return responsableDePago; }
    public void setResponsableDePago(boolean responsableDePago) { this.responsableDePago = responsableDePago; }
}
