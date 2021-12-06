package pe.gob.sunat.base.android.api;

import static pe.gob.sunat.base.android.BuildConfig.SUNAT_URL;


public class EndPointSunatApi {

    /**   URLS HERRAMIENTAS  **/
    public static String getUrlTools() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/parametrosglosa/043";}

    /**   MENU  **/
    public static String getMenuOptions() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/menu/opciones";}

    /**   FILTRO  **/
    public static String getFiltersAll() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/parametros/681";}

    /**   MARCACIONES  **/
    public static String getAttendanceAll() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/marcaciones/listar";}
    public static String getRegisterAttendance() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/marcaciones/registrarDesdeApp";}

    /**   PAPELETAS  **/
    public static String getRejectAllBallotPaper() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/papeletas/rechazarmasiva";}
    public static String getAcceptAllBallotPaper() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/papeletas/aprobarmasiva";}
    public static String getRejectBallotPaper() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/papeletas/rechazar";}
    public static String getAcceptBallotPaper() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/papeletas/aprobar";}
    public static String getPendingBallotPaper() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/papeletasPendientes";}

    /**   SOLICITUDES  **/
    public static String getPendingRequest() { return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/pendientes";}
    public static String getDetailRequest(){ return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/solicitud";}
    public static String getDetailTracking(){ return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/seguimiento";}
    public static String getAcceptRequest() {return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/aprobar";}
    public static String getRejectRequest() {return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/rechazar";}
    public static String getAcceptAllRequest() {return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/aprobarmasiva";}
    public static String getRejectAllRequest() {return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/rechazarmasiva";}
    public static String getDocument() {return SUNAT_URL+"v1/recurso/solicitudesdeasistencia/{codFuncionario}/archivo/{codDocumento}";}
}
