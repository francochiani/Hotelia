package entidad;

public class huespedIVA {
    private String ocupacion;
    private String posicionIVA;
    private int cuit;
    private boolean responsableDePago;

    public huespedIVA(String ocupacion, String posicionIVA, int cuit, boolean responsableDePago) {
        this.ocupacion = ocupacion;
        this.posicionIVA = posicionIVA;
        this.cuit = cuit;
        this.responsableDePago = responsableDePago;
    }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getPosicionIVA() { return posicionIVA; }
    public void setPosicionIVA(String posicionIVA) { this.posicionIVA = posicionIVA; }

    public int getCuit() { return cuit; }
    public void setCuit(int cuit) { this.cuit = cuit; }

    public boolean isResponsableDePago() { return responsableDePago; }
    public void setResponsableDePago(boolean responsableDePago) { this.responsableDePago = responsableDePago; }
}
