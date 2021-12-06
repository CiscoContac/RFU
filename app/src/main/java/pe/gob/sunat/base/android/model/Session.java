package pe.gob.sunat.base.android.model;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;

import pe.gob.sunat.base.android.util.Constantes;

public class Session {

    SharedPreferences prefs;

    private Context context;

    public static final String PREF_NAME            = "SolicitudesSIRH";

    public static final String KEY_IS_LOGGEDIN     = "isLoggedIn";


    /** DATOS PRVENIENTES DEL TOKEN **/

    public static final String SUNAT_TOKEN         = "sunat_token";
    public static final String SUNAT_USUARIO_JEFE       = "sunat_profiles_jefe";
    public static final String SUNAT_PERFILES_USUARIO       = "sunat_profiles";
    public static final String SUNAT_COD_UO        = "sunat_cod_uo";
    public static final String NOMBRE_COMPLETO     = "nombre_completo";
    public static final String SUNAT_CORREO        = "sunat_correo";
    public static final String SUNAT_COD_FUNCIONARIO = "sunat_cod_funcionario";
    public static final String SUNAT_USUARIO         = "sunat_usuario";

    /** DATOS DEL CLIENT AUTENTICAR **/
    public static final String CLIENT_ID_SUNAT     = "client_id_sunat";
    public static final String CLIENT_SECRET_SUNAT = "client_secret_sunat";

    public Session(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }
    public String getSunatToken() {
        return prefs.getString(SUNAT_TOKEN, "");
    }
    public Boolean getSunatUsuarioJefe() {
        return prefs.getBoolean(SUNAT_USUARIO_JEFE, false);
    }
    public String getSunatUsuarioPerfiles() {
        return prefs.getString(SUNAT_PERFILES_USUARIO, "");
    }
    public String getSunatCodUo() {
        return prefs.getString(SUNAT_COD_UO, "");
    }
    public String getSunatCodFuncionario() { return prefs.getString(SUNAT_COD_FUNCIONARIO, "");}
    public String getNombreCompleto() { return prefs.getString(NOMBRE_COMPLETO, ""); }
    public String getSunatCorreo() { return prefs.getString(SUNAT_CORREO, ""); }
    public String getSunatUsuario() { return prefs.getString(SUNAT_USUARIO, "");}


    public void setSunatToken(String access_token){
        prefs.edit().putString(Session.SUNAT_TOKEN,access_token).commit();
    }
    public void setSunatUsuarioJefe(boolean isBoss){
        prefs.edit().putBoolean(Session.SUNAT_USUARIO_JEFE,isBoss).commit();
    }
    public void setSunatCodUo(String sunat_cod_uo){
        prefs.edit().putString(Session.SUNAT_COD_UO,sunat_cod_uo).commit();
    }
    public void setSunatUsuarioPerfiles(String sunat_usu_perfiles){
        prefs.edit().putString(Session.SUNAT_PERFILES_USUARIO,sunat_usu_perfiles).commit();
    }
    public void setSunatCodFuncionario(String sunat_cod_funcionario){
        prefs.edit().putString(Session.SUNAT_COD_FUNCIONARIO,sunat_cod_funcionario).commit();
    }
    public void setNombreCompleto(String nombre_completo){
        prefs.edit().putString(Session.NOMBRE_COMPLETO,nombre_completo).commit();
    }
    public void setSunatCorreo(String sunat_correo){
        prefs.edit().putString(Session.SUNAT_CORREO,sunat_correo).commit();
    }
    public void setSunatUsuario(String sunat_usuario){
        prefs.edit().putString(Session.SUNAT_USUARIO,sunat_usuario).commit();
    }

    public void setLogin(boolean isLoggedIn){
        prefs.edit().putBoolean(KEY_IS_LOGGEDIN,isLoggedIn).commit();
    }

    public boolean isLoggedIn(){
        return prefs.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public Session logoutUser() {
        setLogin(false);
        blanquearToken();
        prefs.edit().remove(CLIENT_ID_SUNAT).commit();
        prefs.edit().remove(CLIENT_SECRET_SUNAT).commit();
        prefs.edit().remove(SUNAT_TOKEN).commit();
        prefs.edit().remove(SUNAT_COD_UO).commit();
        prefs.edit().remove(NOMBRE_COMPLETO).commit();
        prefs.edit().remove(SUNAT_CORREO).commit();
        prefs.edit().remove(SUNAT_COD_FUNCIONARIO).commit();
        prefs.edit().remove(SUNAT_PERFILES_USUARIO).commit();
        prefs.edit().remove(SUNAT_USUARIO_JEFE).commit();
        prefs.edit().commit();
        return this;
    }

    private void blanquearToken() {

        ContentResolver resolver = context.getContentResolver();
        int rowsUpdated = resolver.delete(Constantes.CONTENT_URI, null,null);


    }

    public String getClientIdSunat(){
        return prefs.getString(CLIENT_ID_SUNAT,"");
    }
    public void setClientIdSunat(String client_id_sunat){
        prefs.edit().putString(Session.CLIENT_ID_SUNAT,client_id_sunat).commit();
    }
    public String getClientSecretSunat(){
        return prefs.getString(CLIENT_SECRET_SUNAT,"");
    }

    public void setClientSecretSunat(String client_secret_sunat){
        prefs.edit().putString(Session.CLIENT_SECRET_SUNAT,client_secret_sunat).commit();
    }


}
