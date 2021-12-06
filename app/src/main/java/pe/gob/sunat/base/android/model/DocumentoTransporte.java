package pe.gob.sunat.base.android.model;

import java.io.Serializable;

public class DocumentoTransporte implements Serializable {

    private static final long serialVersionUID = -7483099631563957824L;

    private String numDocTransp;

    public DocumentoTransporte(String numDocTransp) {
        this.numDocTransp = numDocTransp;
    }

    public String getNumDocTransp() {
        return numDocTransp;
    }

    public void setNumDocTransp(String numDocTransp) {
        this.numDocTransp = numDocTransp;
    }
}
