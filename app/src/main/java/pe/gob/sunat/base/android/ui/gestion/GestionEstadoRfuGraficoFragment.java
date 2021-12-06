package pe.gob.sunat.base.android.ui.gestion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.viewmodel.GestionEstadoRfuGraficoViewModel;

public class GestionEstadoRfuGraficoFragment extends Fragment implements Injectable {

    public static final String TAG = GestionEstadoRfuGraficoFragment.class.getSimpleName();

    private GestionEstadoRfuGraficoViewModel mViewModel;

    View view;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static GestionEstadoRfuGraficoFragment newInstance(Bundle bundle) {
        GestionEstadoRfuGraficoFragment fragment = new GestionEstadoRfuGraficoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gestion_estado_rfu_grafico, container, false);
        unbinder = ButterKnife.bind(this, view);
        //tvTitulo.setText("INGRESE TITULO");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GestionEstadoRfuGraficoViewModel.class);
        // TODO: Use the ViewModel
    }


}