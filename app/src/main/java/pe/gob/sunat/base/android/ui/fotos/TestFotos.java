package pe.gob.sunat.base.android.ui.fotos;

import android.net.Uri;

public class TestFotos {

    private String numeroSerie, numeroItem, numeroFoto;
    private int imgBorrar, imgDescripcion;
    private Uri imgUriFoto;
    //Detalle Foto
    private String detalleFoto;
    private boolean visible;

    public TestFotos(String numeroSerie, String numeroItem, String numeroFoto, Uri imgUriFoto, int imgBorrar, int imgDescripcion, String detalleFoto) {
        this.numeroSerie = numeroSerie;
        this.numeroItem = numeroItem;
        this.numeroFoto = numeroFoto;
        this.imgUriFoto = imgUriFoto;
        this.imgBorrar = imgBorrar;
        this.imgDescripcion = imgDescripcion;
        this.detalleFoto = detalleFoto;
        this.visible = false;
    }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) {this.numeroSerie = numeroSerie; }

    public String getNumeroItem() { return numeroItem; }
    public void setNumeroItem(String numeroSerie) {this.numeroItem = numeroItem; }

    public String getNumeroFoto() { return numeroFoto; }
    public void setNumeroFoto(String numeroFoto) {this.numeroFoto = numeroFoto; }

    public Uri getImgUriFoto() { return imgUriFoto; }
    public void setImgUriFoto(Uri imgUriFoto) { this.imgUriFoto = imgUriFoto; }

    public int getImgBorrar() { return imgBorrar; }
    public void setImgBorrar(int imgBorrar) {this.imgBorrar = imgBorrar; }

    public int getImgDescripcion() { return imgDescripcion; }
    public void setImgDescripcion(int imgDescripcion) {this.imgDescripcion = imgDescripcion; }

    public String getDetalleFoto() {
        return detalleFoto;
    }
    public void setDetalleFoto(String detalleFoto) {
        this.detalleFoto = detalleFoto;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
