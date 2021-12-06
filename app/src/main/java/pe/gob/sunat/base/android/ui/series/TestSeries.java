package pe.gob.sunat.base.android.ui.series;

public class TestSeries {

    private String numero,descripcion,subpartidaNacional;
    //Detalle
    private String detalleSerie;
    private boolean visible;

    public TestSeries(String numero, String descripcion, String subpartidaNacional, String detalleSerie) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.subpartidaNacional = subpartidaNacional;
        this.detalleSerie = detalleSerie;
        this.visible = false;
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

    public String getDetalleSerie() { return detalleSerie; }

    public void setDetalleSerie(String detalleSerie) { this.detalleSerie = detalleSerie; }

    public boolean isVisible() { return visible; }

    public void setVisible(boolean visible) { this.visible = visible; }
}
