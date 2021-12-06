package pe.gob.sunat.base.android.util;

import android.content.res.Resources;
import android.util.Log;

import com.androidnetworking.error.ANError;


public class Utils {

    public static void logError(String TAG, ANError error) {
        if (error.getErrorCode() != 0) {
            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
        } else {
            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
        }
    }


    public static String messageError (ANError error){
        switch (error.getErrorCode()){
            case 0:
                return "Error de conexión, comprueba tu conexión o prueba una conexión diferente e inténtalo más tarde.";
            case 400:
                return "Ocurrió un error con el servicio, ErrorCode: " + error.getErrorCode() + ", inténtalo más tarde.";
            case 404:
                return "No se puede acceder al servicio, ErrorCode:" + error.getErrorCode() + ", inténtalo más tarde.";
            case 500:
                return "Ocurrió un error con el servicio, ErrorCode: " + error.getErrorCode() + ", inténtalo más tarde.";
            default:
                return error.getErrorCode()+", " +error.getErrorDetail() + ", " +error.getErrorBody();
        }
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


}
