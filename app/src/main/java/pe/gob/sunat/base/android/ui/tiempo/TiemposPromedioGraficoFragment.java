package pe.gob.sunat.base.android.ui.tiempo;

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
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.viewmodel.TiemposPromedioGraficoViewModel;

public class TiemposPromedioGraficoFragment extends Fragment implements Injectable {

    public static final String TAG = TiemposPromedioGraficoFragment.class.getSimpleName();

    private TiemposPromedioGraficoViewModel mViewModel;

    View view;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static TiemposPromedioGraficoFragment newInstance(Bundle bundle) {
        TiemposPromedioGraficoFragment fragment = new TiemposPromedioGraficoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tiempos_promedio_grafico, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("TIEMPOS PROMEDIO DE ATENCIÓN");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TiemposPromedioGraficoViewModel.class);
        // TODO: Use the ViewModel
    }

}