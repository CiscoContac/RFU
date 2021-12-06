package pe.gob.sunat.base.android.ui.gestion;

import androidx.cardview.widget.CardView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.declaracion.DeclaracionFragment;
import pe.gob.sunat.base.android.viewmodel.HerramientasGestionViewModel;

public class HerramientasGestionFragment extends Fragment implements Injectable {

    public static final String TAG = HerramientasGestionFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    @BindView(R.id.btnGestionEstadoRfu)
    CardView btnGestionEstadoRfu;

    @BindView(R.id.btnGestionTiemposAtencion)
    CardView btnGestionTiemposAtencion;

    @BindView(R.id.btnGestionTiemposPromedio)
    CardView btnGestionTiemposPromedio;

    Unbinder unbinder;
    private View view;

    private HerramientasGestionViewModel mViewModel;

    public static HerramientasGestionFragment newInstance(Bundle bundle) {
        HerramientasGestionFragment fragment = new HerramientasGestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_herramientas_gestion, container, false);
        unbinder = ButterKnife.bind(this, view);

        btnGestionEstadoRfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setGestionEstadoRfu(bundle);
            }
        });

        btnGestionTiemposAtencion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setGestionTiemposAtencion(bundle);
            }
        });

        btnGestionTiemposPromedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setGestionTiemposPromedio(bundle);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HerramientasGestionViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnFragmentIterationListener {
        void setGestionEstadoRfu(Bundle bundle);

        void setGestionTiemposAtencion(Bundle bundle);

        void setGestionTiemposPromedio(Bundle bundle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if(context instanceof HerramientasGestionFragment.OnFragmentIterationListener){
            listener = (HerramientasGestionFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }
}