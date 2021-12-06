package pe.gob.sunat.base.android.ui.series.reposicion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.items.DetalleItemFragment;
import pe.gob.sunat.base.android.ui.series.notas.SerieNotasFragment;

public class SerieReposicionMercanciasFragment extends Fragment implements Injectable {

    public static final String TAG = SerieReposicionMercanciasFragment.class.getSimpleName();
    Unbinder unbinder;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public static SerieReposicionMercanciasFragment newInstance(Bundle bundle) {
        SerieReposicionMercanciasFragment fragment = new SerieReposicionMercanciasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_certificado_reposicion_franquicia, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("CERTIFICADO DE REPOSICIÓN EN FRANQUICIA");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
