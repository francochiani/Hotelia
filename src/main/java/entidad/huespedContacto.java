package entidad;

public class huespedContacto {
    private long telefono;
    private String email;

    public huespedContacto(long telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    public long getTelefono() { return telefono; }
    public void setTelefono(long telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
