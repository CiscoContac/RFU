package pe.gob.sunat.base.android.ui.fotos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import pe.gob.sunat.base.android.R;

public class DialogEliminacionFotosFragment extends DialogFragment {

    public AppCompatButton btnCancelar, btnAceptar;
    //LISTENER ELIMINAR FOTOS
    public AdapterFotos adapterFotos;
    Integer position ;
    public DialogEliminacionFotosFragment(AdapterFotos adapterFotos,Integer position ) {
        this.adapterFotos = adapterFotos;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_eliminar_fotos, container, false);

        btnAceptar = view.findViewById(R.id.button_aceptar_foto);
        btnCancelar = view.findViewById(R.id.button_cancelar_foto);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Se ha eliminado la nota.", Toast.LENGTH_SHORT).show();
                adapterFotos.deleteItem(position);
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