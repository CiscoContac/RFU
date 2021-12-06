package pe.gob.sunat.base.android.model;

import java.io.Serializable;

public class LiquidacionCobranza implements Serializable {

    private static final long serialVersionUID = 215804655582882272L;

    private String numLc;

    private String tipoLc;

    private String estadoLc;

    private String fechaCancelacion;

    private String fechaLiquidacion;

    private double monto;

    private String sustento;

    private boolean visible;

    public LiquidacionCobranza(String numLc, String tipoLc, String estadoLc,
                               String fechaCancelacion, String fechaLiquidacion,
                               double monto, String sustento) {
        this.numLc = numLc;
        this.tipoLc = tipoLc;
        this.estadoLc = estadoLc;
        this.fechaCancelacion = fechaCancelacion;
        this.fechaLiquidacion = fechaLiquidacion;
        this.monto = monto;
        this.sustento = sustento;
        this.visible = false;
    }

    public String getNumLc() {
        return numLc;
    }

    public void setNumLc(String numLc) {
        this.numLc = numLc;
    }

    public String getTipoLc() {
        return tipoLc;
    }

    public void setTipoLc(String tipoLc) {
        this.tipoLc = tipoLc;
    }

    public String getEstadoLc() {
        return estadoLc;
    }

    public void setEstadoLc(String estadoLc) {
        this.estadoLc = estadoLc;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(String fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getFechaLiquidacion() {
        return fechaLiquidacion;
    }

    public void setFechaLiquidacion(String fechaLiquidacion) {
        this.fechaLiquidacion = fechaLiquidacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getSustento() {
        return sustento;
    }

    public void setSustento(String sustento) {
        this.sustento = sustento;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
