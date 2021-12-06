package pe.gob.sunat.base.android.ui.notas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import pe.gob.sunat.base.android.R;

public class DialogEliminacionNotasFragment extends DialogFragment {

    private static final String TAG = "DialogEliminacionNota";

    public AppCompatButton btnCancelar, btnAceptar;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_eliminar_notas, container, false);

        btnAceptar = view.findViewById(R.id.button_aceptar_nota);
        btnCancelar = view.findViewById(R.id.button_cancelar_nota);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Se ha eliminado la nota.", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

}
