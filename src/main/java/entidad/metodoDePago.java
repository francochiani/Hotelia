package entidad;

public class metodoDePago {
    private long idPago;
    private String tipo;

    public metodoDePago(long idPago, String tipo) {
        this.idPago = idPago;
        this.tipo = tipo;
    }

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

