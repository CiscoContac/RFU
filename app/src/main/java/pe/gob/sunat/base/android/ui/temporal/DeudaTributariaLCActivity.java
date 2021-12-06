package pe.gob.sunat.base.android.ui.temporal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pe.gob.sunat.base.android.BuildConfig;
import pe.gob.sunat.base.android.R;


public class DeudaTributariaLCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deuda_tributaria_lc);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Notas");
        }
        TextView txtAppVersion;
        txtAppVersion = findViewById(R.id.textview_app_version);
        txtAppVersion.setText("Versión: "+ BuildConfig.VERSION_NAME);
    }

}
