package entidad;

public class responsableDePago {
    private long cuit;
    private String razonSocial;
    private String tipo;

    public responsableDePago(long cuit, String razonSocial, String tipo) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.tipo =tipo;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
