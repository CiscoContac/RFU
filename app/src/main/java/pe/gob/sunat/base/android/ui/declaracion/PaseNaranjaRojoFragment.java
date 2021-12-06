package pe.gob.sunat.base.android.ui.declaracion;

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
import pe.gob.sunat.base.android.viewmodel.PaseNaranjaRojoViewModel;

public class PaseNaranjaRojoFragment extends Fragment implements Injectable {

    public static final String TAG = PaseNaranjaRojoFragment.class.getSimpleName();

    private PaseNaranjaRojoViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;
    private View view;

    public static PaseNaranjaRojoFragment newInstance(Bundle bundle) {
        PaseNaranjaRojoFragment fragment = new PaseNaranjaRojoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pase_naranja_rojo, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("PASE DE NARANJA A ROJO");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PaseNaranjaRojoViewModel.class);
        // TODO: Use the ViewModel
    }

}