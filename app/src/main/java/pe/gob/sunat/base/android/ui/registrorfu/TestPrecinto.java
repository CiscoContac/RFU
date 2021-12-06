package pe.gob.sunat.base.android.ui.registrorfu;

public class TestPrecinto {
    private  String numContenedor, ultPrecintoActual,ultprecintoModif;

    private boolean visible;

    public TestPrecinto(String numContenedor, String ultPrecintoActual, String ultprecintoModif) {
        this.numContenedor = numContenedor;
        this.ultPrecintoActual = ultPrecintoActual;
        this.ultprecintoModif = ultprecintoModif;
    }

    public String getNumContenedor() {
        return numContenedor;
    }

    public void setNumContenedor(String numContenedor) {
        this.numContenedor = numContenedor;
    }

    public String getUltPrecintoActual() {
        return ultPrecintoActual;
    }

    public void setUltPrecintoActual(String ultPrecintoActual) {
        this.ultPrecintoActual = ultPrecintoActual;
    }

    public String getUltprecintoModif() {
        return ultprecintoModif;
    }

    public void setUltprecintoModif(String ultprecintoModif) {
        this.ultprecintoModif = ultprecintoModif;
    }


    public boolean isVisible() { return visible; }

    public void setVisible(boolean visible) { this.visible = visible; }
}
