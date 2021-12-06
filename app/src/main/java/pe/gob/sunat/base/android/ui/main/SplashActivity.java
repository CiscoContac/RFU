package pe.gob.sunat.base.android.ui.main;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;

import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import pe.gob.sunat.base.android.BaseActivity;
import pe.gob.sunat.base.android.BuildConfig;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.util.Constantes;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends BaseActivity {

    private String eToken;
    private String eUsuario;
    private AccountManager accountManager;
    public static final String[] columnas = new String[]{"_id", "usuario", "token"};
    private final String TAG = "SUNAT-SIRH";
    private static final int UI_ANIMATION_DELAY = 0;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {

            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        }
    };

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        mContentView = findViewById(R.id.fullscreen_content);
        accountManager = AccountManager.get(this);
        delayedHide(2500);
        StartAnimations();
        session.setClientIdSunat(BuildConfig.CLIENT_ID);
        session.setClientSecretSunat(BuildConfig.CLIENT_SECRET);

        eUsuario = getIntent().getStringExtra("usuario");
        eToken = getIntent().getStringExtra("token");
        Log.d("SUNAT_LOG", "token: " + eToken);
        Log.e("ALAN","SESSION"+session.getSunatToken());
        //if (eToken != null && eUsuario != null) { //validación proveniente del authenticador
            intentMain(eUsuario, eToken);
        //}

        /*else if(session.getSunatToken()=="" || !validarToken2()){

            new ConfigApp().execute();
        }

        else{
            Log.d("SUNAT_LOG", "token2: " + session.getSunatToken());
            intentMain(session.getSunatUsuario(), session.getSunatToken());
        }*/


    }

    private class ConfigApp extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // This is called before sending actual HTTP call...
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            // Write the code here to make HTTP calls....
            // For the sake of simplicity of the post, I am only mentioning what to write here and not the actual code.
            // Once your HTTP calls are complete, onPostExecute method will be called with the intended result.

            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // This method will be called after completion of HTTP call. So, Retrieve the intended data
            // and start the main activity. You can also pass this data to main activity using putExtra or
            // some similar methods.
            // Make sure you close this activity after starting MainActivity.

            //Log.e("ALAN","ENTRO");
            Account cuentas[] = accountManager.getAccountsByType(Constantes.ACCOUNT_TYPE);

            int numCuentasRegistradas = cuentas.length;

            //Log.e("ALAN",""+cuentas.length);
            if (numCuentasRegistradas > 1) {
                //mostrarListaCuentas(cuentas);
                intentLoginAppAutenticador();
            } else if (numCuentasRegistradas == 1) {
                Account cuenta = cuentas[0];
                validarToken(cuenta);
            } else {
                intentLoginAppAutenticador();
            }

        }

    }

    private void validarToken(Account cuenta) {


        String[] parts = cuenta.name.split("(?=-)");
        eUsuario = parts[0];

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(Constantes.CONTENT_URI,
                columnas, //Columnas a devolver
                "usuario=?",      //Condición de la query
                new String[]{eUsuario},//Argumentos variables de la query
                null);      //Orden de los resultados

        //Verifica en BD
        if (cur.moveToFirst()) {
            String usuario = cur.getString(cur.getColumnIndex("usuario"));
            eToken = cur.getString(cur.getColumnIndex("token"));
            if (eToken != null && !eToken.isEmpty()) {
                intentMain(usuario, eToken);
            } else {
                intentLoginAppAutenticador();
            }
        } else {
            intentLoginAppAutenticador();
        }
    }

    private boolean validarToken2() {

        boolean token = false;

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(Constantes.CONTENT_URI,
                columnas, //Columnas a devolver
                null,      //CondiciÃ³n de la query
                null,//Argumentos variables de la query
                null);      //Orden de los resultados

        //Verifica en BD
        if (cur!=null) {
            if (cur.moveToFirst()) {
                //String pUsuario = cur.getString(cur.getColumnIndex(Constantes.COLUMN_NAME_USUARIO));
                //String pToken = cur.getString(cur.getColumnIndex(Constantes.COLUMN_NAME_TOKEN));
                //session.setSunatUsuario(pUsuario);
                //session.setSunatToken(pToken);
                token = true;
                Log.e("ALAN","TRUE");
            } else {
                token = false;
                Log.e("ALAN","FALSE");
            }
        }

        return token;
    }


    private void intentMain(String usuario, String token) {
        session.setLogin(true);
        //guardarTokenPreferencia(token);

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();

    }



    private void guardarTokenPreferencia(String token) {

        String[] split = token.split("\\.");
        byte[] data = Base64.decode(split[1], Base64.URL_SAFE);
        String text = new String(data, StandardCharsets.UTF_8);

        JSONObject jsonData;
        JSONObject userJson;
        try {
            jsonData = new JSONObject(text);
            userJson = jsonData.getJSONObject("userdata");
            session.setNombreCompleto(userJson.getString("nombreCompleto"));
            session.setSunatToken(token);
            session.setSunatCodUo(userJson.getString("codUO"));
            session.setSunatCorreo(userJson.getString("correo"));
            session.setSunatUsuario(userJson.getString("login"));
            session.setSunatCodFuncionario(userJson.getString("nroRegistro"));
            JSONArray jsonArrayPerfiles = jsonData.getJSONArray("profiles");
            for (int i = 0; i < jsonArrayPerfiles.length(); i++) {
                String value =  jsonArrayPerfiles.get(i).toString().replace("[","").replace("]","").replace("\"","");
                if(value.equalsIgnoreCase("SIRH-JEFE")){
                    session.setSunatUsuarioJefe(true);
                    break;
                }
            }
            session.setSunatUsuarioPerfiles(jsonArrayPerfiles.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void intentLoginAppAutenticador() {
        Intent intentLogin = getPackageManager().getLaunchIntentForPackage(Constantes.AUTHENTICATOR_PACKAGE);
        if (intentLogin != null) {
            intentLogin.putExtra(Constantes.KEY_APLICATION_PRINCIPAL, Constantes.APLICATION_PRINCIPAL);
            intentLogin.putExtra(Constantes.KEY_CLIENT_ID, session.getClientIdSunat());
            intentLogin.putExtra(Constantes.KEY_CLIENT_SECRET, session.getClientSecretSunat());
            startActivity(intentLogin);
            finish();
        } else {
            Toast.makeText(getBaseContext(), "INSTALAR EL APK AUTHENTICADOR - IR A LA GOOGLE PLAY", Toast.LENGTH_LONG).show();
        }
    }

    private void StartAnimations() {
        TextView tv = findViewById(R.id.textView_frase);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_alpha);
        anim.reset();

        tv.clearAnimation();
        tv.startAnimation(anim);
    }



    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        // Schedule a runnable to remove the status and navigation bar after a delay

        //mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }


    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
