package entidad;

public class consumo {
    private long codigo;
    private long idEstadia;
    private String tipo;
    private int cantidad;
    private double precioUnitario;

    public consumo(long codigo, long idEstadia, String tipo, int cantidad, double precioUnitario) {
        this.codigo = codigo;
        this.idEstadia = idEstadia;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(long idEstadia) {
        this.idEstadia = idEstadia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}

