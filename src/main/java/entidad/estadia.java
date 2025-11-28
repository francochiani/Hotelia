package entidad;

import java.math.BigDecimal;

public class estadia {

    private Long idEstadia;
    private Long huespedDni;
    private BigDecimal valor;

    // -----------------------------------
    // Constructor vacío
    // -----------------------------------
    public estadia() { }

    // -----------------------------------
    // Constructor con todos los campos
    // -----------------------------------
    public estadia(Long idEstadia, Long huespedDni, BigDecimal valor) {
        this.idEstadia = idEstadia;
        this.huespedDni = huespedDni;
        this.valor = valor;
    }

    // -----------------------------------
    // Getters y Setters
    // -----------------------------------

    public Long getIdEstadia() {
        return idEstadia;
    }

    public void setIdEstadia(Long idEstadia) {
        this.idEstadia = idEstadia;
    }

    public Long getHuespedDni() {
        return huespedDni;
    }

    public void setHuespedDni(Long huespedDni) {
        this.huespedDni = huespedDni;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
