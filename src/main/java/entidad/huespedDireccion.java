package entidad;

public class huespedDireccion {
    private String calle;
    private int numero;
    private int depto;
    private int piso;
    private String localidad;
    private String provincia;
    private String pais;
    private int codigoPostal;

    public huespedDireccion(String calle, int numero, int depto, int piso, String localidad, String provincia, String pais, int codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.depto = depto;
        this.piso = piso;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getDepto() { return depto; }
    public void setDepto(int depto) { this.depto = depto; }

    public int getPiso() { return piso; }
    public void setPiso(int piso) { this.piso = piso; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public int getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(int codigoPostal) { this.codigoPostal = codigoPostal; }
}
