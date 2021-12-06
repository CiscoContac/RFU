package pe.gob.sunat.base.android.model;

import org.json.JSONObject;

public class SeguimientoDetalle {
    private String codAccion;
    private String codEstado;
    private String fecRecepcion;
    private String desObservacion;
    private String nomRemitente;
    private String desAccion;
    private String nomDestinatario;

    public SeguimientoDetalle(String codAccion, String codEstado, String fecRecepcion, String desObservacion, String nomRemitente, String desAccion, String nomDestinatario) {
        this.codAccion = codAccion;
        this.codEstado = codEstado;
        this.fecRecepcion = fecRecepcion;
        this.desObservacion = desObservacion;
        this.nomRemitente = nomRemitente;
        this.desAccion = desAccion;
        this.nomDestinatario = nomDestinatario;
    }

    public String getCodAccion() {
        return codAccion;
    }

    public SeguimientoDetalle setCodAccion(String codAccion) {
        this.codAccion = codAccion;
        return this;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public SeguimientoDetalle setCodEstado(String codEstado) {
        this.codEstado = codEstado;
        return this;
    }

    public String getFecRecepcion() {
        return fecRecepcion;
    }

    public SeguimientoDetalle setFecRecepcion(String fecRecepcion) {
        this.fecRecepcion = fecRecepcion;
        return this;
    }

    public String getDesObservacion() {
        return desObservacion;
    }

    public SeguimientoDetalle setDesObservacion(String desObservacion) {
        this.desObservacion = desObservacion;
        return this;
    }

    public String getNomRemitente() {
        return nomRemitente;
    }

    public SeguimientoDetalle setNomRemitente(String nomRemitente) {
        this.nomRemitente = nomRemitente;
        return this;
    }

    public String getDesAccion() {
        return desAccion;
    }

    public SeguimientoDetalle setDesAccion(String desAccion) {
        this.desAccion = desAccion;
        return this;
    }

    public String getNomDestinatario() {
        return nomDestinatario;
    }

    public SeguimientoDetalle setNomDestinatario(String nomDestinatario) {
        this.nomDestinatario = nomDestinatario;
        return this;
    }




    public  static class Builder {
        private SeguimientoDetalle seguimientoDetalle;


        public Builder(SeguimientoDetalle seguimientoDetalle) {
            this.seguimientoDetalle = seguimientoDetalle;
        }


        public SeguimientoDetalle build(){
            return seguimientoDetalle;
        }


        public static SeguimientoDetalle.Builder from(JSONObject jsonRequest) {

                return new Builder( new SeguimientoDetalle(
                        jsonRequest.optString("codAccion").trim(),
                        jsonRequest.optString("codEstado").trim(),
                        jsonRequest.optString("fecRecepcion").trim(),
                        jsonRequest.optString("desObservacion").trim(),
                        jsonRequest.optString("nomRemitente").trim(),
                        jsonRequest.optString("desAccion").trim(),
                        jsonRequest.optString("nomDestinatario").trim()

                ));

        }


    }
}
