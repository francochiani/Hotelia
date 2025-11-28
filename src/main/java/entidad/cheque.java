package entidad;

public class cheque {
    private long numeroCheque;
    private long idPago;
    private String bancoEmisor;
    private java.sql.Date fechaCobro;

    public cheque(long numeroCheque, long idPago, String bancoEmisor, java.sql.Date fechaCobro) {
        this.numeroCheque = numeroCheque;
        this.idPago = idPago;
        this.bancoEmisor = bancoEmisor;
        this.fechaCobro = fechaCobro;
    }

    public long getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(long numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }

    public String getBancoEmisor() {
        return bancoEmisor;
    }

    public void setBancoEmisor(String bancoEmisor) {
        this.bancoEmisor = bancoEmisor;
    }

    public java.sql.Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(java.sql.Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }
}