package entidad;

import java.util.Date;

public class huesped {
    // Atributos
    private String apellido;
    private String nombres;
    private String tipoDocumento;
    private int nroDocumento;
    private Date fechaNac;
    private String nacionalidad;
    private huespedContacto contacto;
    private huespedIVA posicionFiscal;
    private huespedDireccion direccion;

    // Constructor completo
    public huesped(String apellido, String nombres, String tipoDocumento, int nroDocumento,
                   Date fechaNac, String nacionalidad,
                   long telefono, String email,
                   String ocupacion, String posicionIVA, int cuit, boolean responsableDePago,
                   String calle, int numero, String localidad, String provincia,
                   String pais, int codigoPostal) {
        this.apellido = apellido;
        this.nombres = nombres;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
        this.nacionalidad = nacionalidad;
        this.contacto = new huespedContacto(telefono, email);
        this.posicionFiscal = new huespedIVA(ocupacion, posicionIVA,cuit,responsableDePago);
        this.direccion = new huespedDireccion(calle,numero,localidad,provincia,pais,codigoPostal);
    }

    public huesped(String apellido, String nombres, String tipoDocumento, int nroDocumento,
                   Date fechaNac, String nacionalidad) {
        this.apellido = apellido;
        this.nombres = nombres;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
        this.nacionalidad = nacionalidad;
    }

    // Getters y Setters
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public huespedContacto getContacto() {
        return contacto;
    }

    public void setContacto(huespedContacto contacto) {
        this.contacto = contacto;
    }

    public huespedIVA getPosicionFiscal() {
        return posicionFiscal;
    }

    public void setPosicionFiscal(huespedIVA posicionFiscal) {
        this.posicionFiscal = posicionFiscal;
    }

    public huespedDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(huespedDireccion direccion) {
        this.direccion = direccion;
    }
}