package pe.gob.sunat.base.android.ui.series.fotos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import pe.gob.sunat.base.android.R;

public class DialogEliminacionSerieFotosFragment extends DialogFragment {

    private static final String TAG = "DialogEliminacionFoto";

    public AppCompatButton btnCancelar, btnAceptar;
    public AdapterSerieFotos adapterSerieFotos;
    Integer position ;
    public DialogEliminacionSerieFotosFragment(AdapterSerieFotos adapterSerieFotos,Integer position ) {
        this.adapterSerieFotos = adapterSerieFotos;
        this.position = position;
    }

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
                //listSerieFotos.remove(getAdapterPosition());
                //notifyItemRemoved(getAdapterPosition());
                adapterSerieFotos.deleteItem(position);
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
    public void aceptar(){

    }
}