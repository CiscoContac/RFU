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
import pe.gob.sunat.base.android.viewmodel.DespachoAuxliarViewModel;

public class DespachoAuxliarFragment extends Fragment implements Injectable {

    public static final String TAG = DespachoAuxliarFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    Unbinder unbinder;

    @BindView(R.id.btn_despacho_auxiliar_siguiente)
    Button btn_despacho_auxiliar_siguiente;

    @BindView(R.id.btn_despacho_auxiliar_regresar)
    Button btn_despacho_auxiliar_regresar;

    private View view;

    private DespachoAuxliarViewModel mViewModel;

    public static DespachoAuxliarFragment newInstance(Bundle bundle) {
        DespachoAuxliarFragment fragment= new DespachoAuxliarFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_despacho_auxliar, container, false);
        unbinder = ButterKnife.bind(this,view);
        btn_despacho_auxiliar_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDatoComplementarioFragment(bundle);
            }
        });

        btn_despacho_auxiliar_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDatoReconocimientoFisicoFragment(bundle);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DespachoAuxliarViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnFragmentIterationListener {
        void setDatoComplementarioFragment(Bundle bundle);

        void setDatoReconocimientoFisicoFragment(Bundle bundle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof DespachoAuxliarFragment.OnFragmentIterationListener) {
            listener = (DespachoAuxliarFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }


}