package entidad;

public class personaJuridica {
    private long cuit;
    private String telefono;
    private String direccion;

    public personaJuridica(long cuit, String telefono, String direccion) {
        this.cuit = cuit;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
