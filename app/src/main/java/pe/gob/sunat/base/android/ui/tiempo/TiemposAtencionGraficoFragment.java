package pe.gob.sunat.base.android.ui.tiempo;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.viewmodel.TiemposAtencionGraficoViewModel;

public class TiemposAtencionGraficoFragment extends Fragment implements Injectable {

    public static final String TAG = TiemposAtencionGraficoFragment.class.getSimpleName();

    private TiemposAtencionGraficoViewModel mViewModel;

    public static TiemposAtencionGraficoFragment newInstance(Bundle bundle) {
        TiemposAtencionGraficoFragment fragment = new TiemposAtencionGraficoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tiempos_atencion_grafico, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TiemposAtencionGraficoViewModel.class);
        // TODO: Use the ViewModel
    }

}