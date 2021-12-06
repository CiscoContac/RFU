package pe.gob.sunat.base.android.ui.registrorfu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.ui.fotos.DialogDescripcionFotosFragment;

public class DialogDescripcionDiligenciaFotosFragment extends DialogFragment {

    private static final String TAG = "DialogDescripcion";

    public AppCompatButton btnCancelar, btnAceptar;
    public TextInputEditText txtDescripcion;
    public String descripcion;


    //Interface Envio Descripción
    public interface EnviarDescripcion {
        public void enviarDatos(String descripcion);
    }
    public DialogDescripcionFotosFragment.EnviarDescripcion ED;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_descripcion_fotos, container, false);

        //Botones
        btnAceptar = view.findViewById(R.id.button_aceptar);
        btnCancelar = view.findViewById(R.id.button_cancelar);
        txtDescripcion = view.findViewById(R.id.tv_dialog_descripcion);

        //Aceptar
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descripcion = txtDescripcion.getText().toString();
                //Toast.makeText(getContext(), descripcion, Toast.LENGTH_SHORT).show();
                if (!descripcion.equals("")){
                    ED.enviarDatos(descripcion);
                }
                getDialog().dismiss();
            }
        });
        //Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
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
        try{
            ED = (DialogDescripcionFotosFragment.EnviarDescripcion) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}
