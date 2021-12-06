
package pe.gob.sunat.base.android.model;



/**
 * Riesgo
 */
 public class Riesgo {


    private String modelo;

    private String series;

    private String fraude;

    private String detalle;

    private String  desSeleccion ;

    private String desUnidad;

    /******************* GET AND SET ***********************/
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getFraude() {
        return fraude;
    }

    public void setFraude(String fraude) {
        this.fraude = fraude;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDesSeleccion() {
        return desSeleccion;
    }

    public void setDesSeleccion(String desSeleccion) {
        this.desSeleccion = desSeleccion;
    }

    public String getDesUnidad() {
        return desUnidad;
    }

    public void setDesUnidad(String desUnidad) {
        this.desUnidad = desUnidad;
    }

    public Riesgo(String modelo, String series, String fraude, String detalle, String desSeleccion, String desUnidad) {
        this.modelo = modelo;
        this.series = series;
        this.fraude = fraude;
        this.detalle = detalle;
        this.desSeleccion = desSeleccion;
        this.desUnidad = desUnidad;
    }
}



