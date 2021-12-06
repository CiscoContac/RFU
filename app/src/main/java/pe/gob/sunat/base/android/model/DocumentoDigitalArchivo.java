package pe.gob.sunat.base.android.model;

public class DocumentoDigitalArchivo  {

    String desTipodocumento;
    String obsOperador;
    String fecAtereq;
    String desProceso;
    String desRequerimiento;//PAS20201U220200053
    String codIdecm;
    String tipoReq;

    //INI GRAMIREZP PAS20191U220200080
    String nomArchivo;

    public DocumentoDigitalArchivo() {
     super();
    }

    public DocumentoDigitalArchivo(String desTipodocumento, String obsOperador, String fecAtereq, String desProceso, String desRequerimiento, String codIdecm, String tipoReq, String nomArchivo) {
        this.desTipodocumento = desTipodocumento;
        this.obsOperador = obsOperador;
        this.fecAtereq = fecAtereq;
        this.desProceso = desProceso;
        this.desRequerimiento = desRequerimiento;
        this.codIdecm = codIdecm;
        this.tipoReq = tipoReq;
        this.nomArchivo = nomArchivo;
    }

    public String getNomArchivo() {
        return nomArchivo;
    }

    public void setNomArchivo(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }
    //FIN GRAMIREZP PAS20191U220200080

    public String getDesTipodocumento() {
        return desTipodocumento;
    }

    public void setDesTipodocumento(String desTipodocumento) {
        this.desTipodocumento = desTipodocumento;
    }

    public String getObsOperador() {
        return obsOperador;
    }

    public void setObsOperador(String obsOperador) {
        this.obsOperador = obsOperador;
    }

    public String getFecAtereq() {
        return fecAtereq;
    }

    public void setFecAtereq(String fecAtereq) {
        this.fecAtereq = fecAtereq;
    }

    public String getDesProceso() {
        return desProceso;
    }

    public void setDesProceso(String desProceso) {
        this.desProceso = desProceso;
    }

    public String getCodIdecm() {
        return codIdecm;
    }

    public void setCodIdecm(String codIdecm) {
        this.codIdecm = codIdecm;
    }

    public String getDesRequerimiento() {
        return desRequerimiento;
    }

    public void setDesRequerimiento(String desRequerimiento) {
        this.desRequerimiento = desRequerimiento;
    }

    public String getTipoReq() {
        return tipoReq;
    }

    public void setTipoReq(String tipoReq) {
        this.tipoReq = tipoReq;
    }
}
