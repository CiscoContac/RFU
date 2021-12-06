package pe.gob.sunat.base.android;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Response;
import pe.gob.sunat.base.android.api.EndPointSunatApi;
import pe.gob.sunat.base.android.model.Session;
import pe.gob.sunat.base.android.ui.main.SplashActivity;
import pe.gob.sunat.base.android.util.PermissionUtils;
import pe.gob.sunat.base.android.util.UtilLocation;
import pe.gob.sunat.base.android.util.Utils;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    protected Session session;
    private Dialog dialogLoading;
    private String latitud, longitud;
    private Boolean flagUbicacion, flagAlmacen, isLoading;
    private final int PERMISSIONS_REQUEST_LOCATION = 400;
    private final int PERMISSIONS_REQUEST_STORAGE = 300;
    private long mLastClickTime = 0;

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        session = new Session(this);
        verificarPermisosStorage();
        verificarPermisosUbicacion();
        isLoading = false;
    }


    public void verificarPermisosUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            flagUbicacion = false;
            PermissionUtils.requestPermission(this);
        } else {
            flagUbicacion = true;
            UtilLocation utilLocation = new UtilLocation(getBaseContext());
            latitud= Double.toString(utilLocation.getLatitude()) ;
            longitud= Double.toString(utilLocation.getLongitude());
        }
    }

    public void verificarPermisosStorage() {
        flagAlmacen =(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if(!flagAlmacen){
            PermissionUtils.requestPermission(this);
        }
        Log.i("FLAAAAAG", String.valueOf(flagAlmacen));
    }


    protected abstract int getLayoutId();


    public Session getSession() {
        return session;
    }


    public void Logout() {
        session.logoutUser();
        Intent intentStart = new Intent(getApplicationContext(), SplashActivity.class);
        intentStart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intentStart);
        finish();
    }


    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }




    public void showLogoutDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_logout);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        (dialog.findViewById(R.id.bt_no)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        (dialog.findViewById(R.id.bt_si)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Logout();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    public void showTokenDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_token_expired);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        (dialog.findViewById(R.id.bt_aceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }



    public void actualizarUbicacion(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this);
            return;
        }
        LocationManager mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        if(bestLocation==null)
        {
            Toast.makeText(getBaseContext(), "No se pudo obtener la ubicación, intente más tarde", Toast.LENGTH_SHORT).show();
            return;
        }

        longitud = Double.toString(bestLocation.getLongitude());
        latitud = Double.toString(bestLocation.getLatitude());
    }


    public void solicitarPermisosUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION);

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_LOCATION);

            }
            flagUbicacion = false;
        } else {
            flagUbicacion = true;
        }
    }

    public void solicitarPermisosStorage() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_STORAGE);

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_STORAGE);
            }
            flagAlmacen = false;
        } else {
            flagAlmacen = true;
        }

    }

    public void obtenerDocumento(String codDocumento) {
        if (flagAlmacen) {

            showLoadingDialog();

            String finalUrl = EndPointSunatApi.getDocument();
            AndroidNetworking.post(finalUrl)
                    .addPathParameter("codFuncionario", session.getSunatCodFuncionario())
                    .addPathParameter("codDocumento", codDocumento)
                    .addHeaders("Authorization", "Bearer " + session.getSunatToken())
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsOkHttpResponse(new OkHttpResponseListener() {
                        @Override
                        public void onResponse(Response response) {
                            JSONObject json;
                            try {
                                json = new JSONObject(response.body().source().readUtf8());

                                boolean mExternalStorageWriteable = false;
                                String state = Environment.getExternalStorageState();

                                if (Environment.MEDIA_MOUNTED.equals(state)) {
                                    // We can read and write the media

                                } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                                    // We can only read the media

                                    mExternalStorageWriteable = false;
                                } else {
                                    // Something else is wrong. It may be one of many other states, but all we need
                                    //  to know is we can neither read nor write
                                    mExternalStorageWriteable = false;
                                }

                                if (mExternalStorageWriteable) {
                                    File dwldsPath  = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/" +json.optString("nomArchivo"));
                                    byte[] pdfAsBytes = Base64.decode(json.optString("arcSustentoBase64"), 0);
                                    FileOutputStream os;
                                    os = new FileOutputStream(dwldsPath,false);
                                    os.write(pdfAsBytes);
                                    os.flush();
                                    os.close();

                                    File filePdf = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/" +json.optString("nomArchivo"));

                                    Intent target = new Intent(Intent.ACTION_VIEW);


                                    Uri apkURI = FileProvider.getUriForFile(
                                            getBaseContext(),
                                            getApplicationContext()
                                                    .getPackageName() + ".provider", filePdf);

                                    target.setDataAndType(apkURI, "application/pdf");

                                    target.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                                    hideLoadingDialog();

                                    Intent intent = Intent.createChooser(target, "Open File");
                                    try {
                                        startActivity(intent);
                                    } catch (ActivityNotFoundException e) {
                                        showErrorDialog("Debe instalar un lector de PDF", false);
                                    }
                                }

                            } catch (JSONException e) {
                                showErrorDialog("Error: " + e.getMessage(), false);
                            } catch (IOException e) {
                                showErrorDialog("Error: " + e.getMessage(), false);
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            if (anError.getErrorCode() == 401) {
                                showTokenDialog();
                            } else {
                                showErrorDialog(Utils.messageError(anError),false);
                            }
                        }
                    });
        } else {
            solicitarPermisosStorage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager lm = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                PERMISSIONS_REQUEST_LOCATION);
                        return;
                    }

                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location!=null)
                    {
                        longitud = Double.toString(location.getLongitude());
                        latitud = Double.toString(location.getLatitude());
                        flagUbicacion=true;
                    }


                } else {
                    showErrorDialog("Necesita otorgar permisos de ubicación al aplicativo",false);
                }
                return;
            }
            case PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        flagAlmacen=true;

                } else {
                    showErrorDialog("Necesita otorgar permisos de almacenamiento al aplicativo",false);
                }

            }

        }
    }

    public void showLoadingDialog() {
        isLoading =true;
        dialogLoading = new Dialog(this);
        dialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLoading.setContentView(R.layout.dialog_loading);
        dialogLoading.setCancelable(false);
        dialogLoading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogLoading.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogLoading.show();
        dialogLoading.getWindow().setAttributes(lp);
    }

    public void hideLoadingDialog() {
        dialogLoading.dismiss();
    }

    public void showErrorDialog(String mensaje, final Boolean closeView) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_error);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if(!mensaje.isEmpty()){
            ((TextView)(dialog.findViewById(R.id.textview_dialog_menssage))).setText(mensaje);
        }
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        (dialog.findViewById(R.id.button_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(closeView){ finish();}
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
        if(isLoading){
            hideLoadingDialog();
        }
    }
    public void showWarningDialog(String mensaje, final Boolean closeView) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_warning);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if(!mensaje.isEmpty()){
            ((TextView)(dialog.findViewById(R.id.textview_dialog_menssage))).setText(mensaje);
        }
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        (dialog.findViewById(R.id.button_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(closeView){ finish();}
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
        if(isLoading){
            hideLoadingDialog();
        }
    }
}
