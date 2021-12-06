package pe.gob.sunat.base.android.ui.registrorfu;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.declaracion.DeclaracionFragment;
import pe.gob.sunat.base.android.viewmodel.DatoComplementarioViewModel;

public class DatoComplementarioFragment extends Fragment implements Injectable {

    public static final String TAG = DatoComplementarioFragment.class.getSimpleName();

    OnFragmentIterationListener listener;
    Unbinder unbinder;

    @BindView(R.id.btn_dato_complementario_siguiente)
    Button btn_dato_complementario_siguiente;

    @BindView(R.id.btn_dato_complementario_regresar)
    Button btn_dato_complementario_regresar;

    @BindView(R.id.btn_mod_precintos)
    Button btn_mod_precintos;

    @BindView(R.id.button_agregar_foto)
    Button btn_agregar_foto;

    private View view;

    private DatoComplementarioViewModel mViewModel;

    public static DatoComplementarioFragment newInstance(Bundle bundle) {
        DatoComplementarioFragment fragment = new DatoComplementarioFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_dato_complementario, container, false);
        unbinder = ButterKnife.bind(this,view);

        btn_mod_precintos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setModificarPrecintoFragment(bundle);
            }
        });

        btn_dato_complementario_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setResumenDatoIngresado(bundle);
            }
        });

        btn_dato_complementario_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDespachoAuxliarFragment(bundle);
            }
        });


        btn_agregar_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setListaFotosFragment(bundle);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DatoComplementarioViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnFragmentIterationListener{
        void setResumenDatoIngresado (Bundle bundle);

        void setModificarPrecintoFragment (Bundle bundle);

        void setListaFotosFragment (Bundle bundle);

        void setDespachoAuxliarFragment(Bundle bundle);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof DatoComplementarioFragment.OnFragmentIterationListener) {
            listener = (DatoComplementarioFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }
}