package pe.gob.sunat.base.android.model;

import java.io.Serializable;

public class FacturaComercial implements Serializable {

    private static final long serialVersionUID = 368357114111550294L;

    private String numFactuComerc;

    public FacturaComercial(String numFactuComerc) {
        this.numFactuComerc = numFactuComerc;
    }

    public String getNumFactuComerc() {
        return numFactuComerc;
    }

    public void setNumFactuComerc(String numFactuComerc) {
        this.numFactuComerc = numFactuComerc;
    }
}
