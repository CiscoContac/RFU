package pe.gob.sunat.base.android.util;

import android.net.Uri;

public class Constantes {


    public static final String TAG_HOME = "Principal";
    public static final String TAG_REQUESTS = "Solicitudes Pendientes";
    public static final String TAG_BALLOTS = "Papeletas Pendientes";
    public static final String TAG_ATTENDANCE= "Marcaciones";
    public static final String TAG_INCIDENT= "Incidentes";
    public static final String TAG_FILTER_REQUESTS= "Filtro Solicitudes";
    public static final String TAG_FILTER_BALLOTS= "Filtro Papeletas";
    public static final Uri CONTENT_URI = Uri.parse("content://pe.gob.sunat.authenticatorClientesSunat.provider/UsersClientesSunat");
    public static final String COLUMN_NAME_USUARIO = "usuario";
    public static final String COLUMN_NAME_TOKEN = "token";

    //Nombre del paquete donde se encuentra el authenticator
    public static final String AUTHENTICATOR_PACKAGE="pe.gob.sunat.tecnologia.seguridad.AuthenticatorClientesSunat";

    //Parámetro que recibe el authenticator para redireccionar a la aplicacion principal
    public static final String KEY_APLICATION_PRINCIPAL="rutaApp";

    //Nombre parametro - client id
    public static final String KEY_CLIENT_ID="clientId";

    //Nombre parametro - client secret
    public static final String KEY_CLIENT_SECRET="clientSecret";

    //NOMBRE DEL PAQUETE ACTUAL
    public static final String APLICATION_PRINCIPAL="pe.gob.sunat.base.android";

    //Nombre del tipo de cuenta
    public static final String ACCOUNT_TYPE = "pe.gob.sunat.clientessunat.auth";


    public static final String TITULO_BANDEJA = "Bandeja";

    public static final String ARG_TITULO_BANDEJA = "TITULO_BANDEJA";
    public static final String ARG_TITULO_DAM = "TITULO_DAM";
    public static final String ARG_TITULO_DOCUTRANS = "TITULO_DOCUTRANS";
    public static final String ARG_TITULO_CONTENEDORES = "TITULO_CONTENEDORES";
    public static final String ARG_TITULO_SERIES = "TITULO_SERIES";
    public static final String ARG_TITULO_SERIE = "TITULO_SERIE";
    public static final String ARG_TITULO_ITEMS = "TITULO_ITEMS";
    public static final String ARG_TITULO_ITEM = "TITULO_ITEM";

    public static final String ARG_TITULO_DILIGENCIA = "TITULO_DILIGENCIA";
}
