package pe.gob.sunat.base.android.ui.registrorfu;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.ui.series.SeriesFragment;
import pe.gob.sunat.base.android.viewmodel.DiligenciaRfuViewModel;

public class DiligenciaRfuFragment extends Fragment {

    public static final String TAG = DiligenciaRfuFragment.class.getSimpleName();

    private DiligenciaRfuViewModel mViewModel;

    View view;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static DiligenciaRfuFragment newInstance(Bundle bundle) {
        DiligenciaRfuFragment fragment = new DiligenciaRfuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diligencia_rfu_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("REGISTRAR DILIGENCIA RFU");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DiligenciaRfuViewModel.class);
        // TODO: Use the ViewModel
    }
}