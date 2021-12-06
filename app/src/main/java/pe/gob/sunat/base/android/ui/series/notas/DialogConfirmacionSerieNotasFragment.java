package pe.gob.sunat.base.android.ui.series.notas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import pe.gob.sunat.base.android.R;

public class DialogConfirmacionSerieNotasFragment extends DialogFragment {

    private static final String TAG = "DialogConfirmacionSerieNota";

    public AppCompatButton btnConfirmar;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_registro_notas, container, false);

        btnConfirmar = view.findViewById(R.id.button_confirmar_nota);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Se ha guardado la nota.", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        return view;
    }
}
