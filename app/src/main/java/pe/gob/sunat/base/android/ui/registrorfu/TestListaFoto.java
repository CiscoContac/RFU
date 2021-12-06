package pe.gob.sunat.base.android.ui.registrorfu;

import android.net.Uri;

public class TestListaFoto {

    private String numeroFoto;
    private int  imgBorrar, imgDescripcion;
    private Uri imgUriFoto;
    //Detalle Foto
    private String detalleFoto;
    private boolean visible;

    public TestListaFoto(String numeroFoto, int imgBorrar, int imgDescripcion, Uri imgUriFoto, String detalleFoto) {
        this.numeroFoto = numeroFoto;
        this.imgBorrar = imgBorrar;
        this.imgDescripcion = imgDescripcion;
        this.imgUriFoto = imgUriFoto;
        this.detalleFoto = detalleFoto;
        this.visible = visible;
    }

    public String getNumeroFoto() {
        return numeroFoto;
    }

    public void setNumeroFoto(String numeroFoto) {
        this.numeroFoto = numeroFoto;
    }

    public int getImgBorrar() {
        return imgBorrar;
    }

    public void setImgBorrar(int imgBorrar) {
        this.imgBorrar = imgBorrar;
    }

    public int getImgDescripcion() {
        return imgDescripcion;
    }

    public void setImgDescripcion(int imgDescripcion) {
        this.imgDescripcion = imgDescripcion;
    }

    public Uri getImgUriFoto() {
        return imgUriFoto;
    }

    public void setImgUriFoto(Uri imgUriFoto) {
        this.imgUriFoto = imgUriFoto;
    }

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
