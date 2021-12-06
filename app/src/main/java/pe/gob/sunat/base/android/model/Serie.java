package pe.gob.sunat.base.android.model;

import java.io.Serializable;

public class Serie implements Serializable {

    private static final long serialVersionUID = 1591882263007519655L;

    private String numero;
    private String descripcion;
    private String subpartidaNacional;

    public Serie(String numero, String descripcion, String subpartidaNacional) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.subpartidaNacional = subpartidaNacional;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSubpartidaNacional() {
        return subpartidaNacional;
    }

    public void setSubpartidaNacional(String subpartidaNacional) {
        this.subpartidaNacional = subpartidaNacional;
    }
}
