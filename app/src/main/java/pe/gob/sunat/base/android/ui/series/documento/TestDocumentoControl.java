package pe.gob.sunat.base.android.ui.series.documento;

public class TestDocumentoControl {

    private String numDoc, numSerie, entidadNombre;

    private boolean visible;

    public TestDocumentoControl(String numDoc, String numSerie, String entidadNombre) {
        this.numDoc = numDoc;
        this.numSerie = numSerie;
        this.entidadNombre = entidadNombre;
        this.visible = visible;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getEntidadNombre() {
        return entidadNombre;
    }

    public void setEntidadNombre(String entidadNombre) {
        this.entidadNombre = entidadNombre;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
