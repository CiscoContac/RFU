package pe.gob.sunat.base.android.ui.series.fotos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import pe.gob.sunat.base.android.R;

public class DialogDescripcionSerieFotosFragment extends DialogFragment {

    private static final String TAG = "DialogSerieDescripcion";

    public AppCompatButton btnSerieCancelar, btnSerieAceptar;
    public TextInputEditText txtSerieDescripcion;
    public String serieDescripcion;

    //Interface Envio Descripcion
    public interface EnviarSerieDescripcion {
        public void enviarSerieDatos(String descripcion);
    }
    public EnviarSerieDescripcion ESD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_descripcion_serie_foto, container, false);

        //Botones
        btnSerieCancelar = view.findViewById(R.id.button_serie_cancelar);
        btnSerieAceptar = view.findViewById(R.id.button_serie_aceptar);
        txtSerieDescripcion = view.findViewById(R.id.tv_dialog_serie_foto_descripcion);

        //Aceptar
        btnSerieAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serieDescripcion = txtSerieDescripcion.getText().toString();
                if (!serieDescripcion.equals("")){
                    ESD.enviarSerieDatos(serieDescripcion);
                }
                getDialog().dismiss();
            }
        });
        //Cancelar
        btnSerieCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Botón Cancelar", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            ESD = (EnviarSerieDescripcion) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}
