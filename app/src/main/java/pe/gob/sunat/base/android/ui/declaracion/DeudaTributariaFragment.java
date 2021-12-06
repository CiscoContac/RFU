package pe.gob.sunat.base.android.ui.declaracion;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.viewmodel.DeudaTributariaViewModel;

public class DeudaTributariaFragment extends Fragment implements Injectable {

    public static final String TAG =DeudaTributariaFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    @BindView(R.id.btnLiquidacionCobranza)
    LinearLayout btnLiquidacionCobranza;

    Unbinder unbinder;
    private View view;

    private DeudaTributariaViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public static DeudaTributariaFragment newInstance(Bundle bundle) {
        DeudaTributariaFragment fragment = new DeudaTributariaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_deuda_tributaria, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("DEUDA TRIBUTARIA");

        btnLiquidacionCobranza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setLiquidacionesCobranza(bundle);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DeudaTributariaViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnFragmentIterationListener {
        void setLiquidacionesCobranza(Bundle bundle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof OnFragmentIterationListener) {
            listener = (OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }
}