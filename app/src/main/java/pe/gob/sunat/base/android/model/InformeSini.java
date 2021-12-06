package pe.gob.sunat.base.android.model;

import java.io.Serializable;

public class InformeSini implements Serializable {

    private static final long serialVersionUID = 5259343544369694767L;

    private String resultado;
    private String hipotesis;

    public InformeSini(String resultado, String hipotesis) {
        this.resultado = resultado;
        this.hipotesis = hipotesis;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getHipotesis() {
        return hipotesis;
    }

    public void setHipotesis(String hipotesis) {
        this.hipotesis = hipotesis;
    }


}
