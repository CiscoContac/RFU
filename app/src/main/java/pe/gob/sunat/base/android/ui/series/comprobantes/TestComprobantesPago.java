package pe.gob.sunat.base.android.ui.series.comprobantes;

public class TestComprobantesPago {

    private String numeroPago, emisor;
    //Detalle
    private String tipoComprobante, tipoOperacion, detalleEmisor, fechaEmision, incoterm;
    private boolean visible;

    public TestComprobantesPago(String numeroPago, String emisor, String tipoComprobante, String tipoOperacion, String detalleEmisor, String fechaEmision, String incoterm) {
        this.numeroPago = numeroPago;
        this.emisor = emisor;
        this.tipoComprobante = tipoComprobante;
        this.tipoOperacion = tipoOperacion;
        this.detalleEmisor = detalleEmisor;
        this.fechaEmision = fechaEmision;
        this.incoterm = incoterm;
        this.visible = false;
    }

    public String getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(String numeroPago) {
        this.numeroPago = numeroPago;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getDetalleEmisor() {
        return detalleEmisor;
    }

    public void setDetalleEmisor(String detalleEmisor) {
        this.detalleEmisor = detalleEmisor;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
