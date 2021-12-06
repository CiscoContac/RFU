package pe.gob.sunat.base.android.ui.declaracion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;


import butterknife.BindView;
import pe.gob.sunat.base.android.R;

public class DialogConfirmacionRfuFragment extends DialogFragment {

    private static final String TAG = "DialogConfirmacionRfu";

    /*@BindView(R.id.button_confirmar_rfu)
    AppCompatButton btnConfirmarRfu;*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_registro_rfu, container, false);

        AppCompatButton btnConfirmarRfu = view.findViewById(R.id.button_confirmar_rfu);

        btnConfirmarRfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Se ha guardado el rfu.", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        return view;
    }
}
