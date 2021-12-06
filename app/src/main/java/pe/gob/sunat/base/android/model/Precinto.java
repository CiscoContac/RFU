package pe.gob.sunat.base.android.model;

import java.io.Serializable;

public class Precinto implements Serializable {

    private static final long serialVersionUID = 3542967671053679344L;

    private String numPrecinto;

    public Precinto(String numPrecinto) {
        this.numPrecinto = numPrecinto;
    }

    public String getNumPrecinto() {
        return numPrecinto;
    }

    public void setNumPrecinto(String numPrecinto) {
        this.numPrecinto = numPrecinto;
    }
}
