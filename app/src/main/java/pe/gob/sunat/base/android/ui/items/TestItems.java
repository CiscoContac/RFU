package pe.gob.sunat.base.android.ui.items;

public class TestItems {

    private String numeroItem, numeroItemComponente;
    private int imgItem, imgitemDetalle;
    //Detalle
    private String detalleItem;
    private boolean visible;

    public TestItems(String numeroItem, String numeroItemComponente, int imgItem, int imgitemDetalle, String detalleItem) {
        this.numeroItem = numeroItem;
        this.numeroItemComponente = numeroItemComponente;
        this.imgItem = imgItem;
        this.imgitemDetalle = imgitemDetalle;
        this.detalleItem = detalleItem;
        this.visible = false;
    }

    public String getNumeroItem() { return numeroItem; }

    public void setNumeroItem(String numeroItem) {
        this.numeroItem = numeroItem;
    }

    public String getNumeroItemComponente() {
        return numeroItemComponente;
    }

    public void setNumeroItemComponente(String numeroItemComponente) {
        this.numeroItemComponente = numeroItemComponente;
    }

    public int getImgItem() {
        return imgItem;
    }

    public void setImgItem(int imgItem) {
        this.imgItem = imgItem;
    }

    public int getImgitemDetalle() {
        return imgitemDetalle;
    }

    public void setImgitemDetalle(int imgitemDetalle) {
        this.imgitemDetalle = imgitemDetalle;
    }

    public String getDetalleItem() {
        return detalleItem;
    }

    public void setDetalleItem(String detalleItem) {
        this.detalleItem = detalleItem;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
