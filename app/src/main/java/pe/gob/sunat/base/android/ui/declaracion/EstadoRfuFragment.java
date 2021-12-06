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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.declaracion.dialog.DialogConfirmacionRfuFragment;
import pe.gob.sunat.base.android.viewmodel.EstadoRfuViewModel;

public class EstadoRfuFragment extends Fragment implements Injectable {

    public static final String TAG = EstadoRfuFragment.class.getSimpleName();

    Unbinder unbinder;

    /*@BindView(R.id.fab_rfu_add)
    FloatingActionButton fabRfuRegistro;*/

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    private View view;
    private EstadoRfuViewModel mViewModel;

    public static EstadoRfuFragment newInstance(Bundle bundle) {
        EstadoRfuFragment fragment = new EstadoRfuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estado_rfu, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("ESTADOS DE RECONOCIMIENTO FÍSICO");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton fabRfuRegistro = getView().findViewById(R.id.fab_rfu_add);
       fabRfuRegistro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                confirmacionRegistroRfu();
            }

        });
    }

    private void confirmacionRegistroRfu() {
        DialogConfirmacionRfuFragment dialogConfirmacionRfuFragment = new DialogConfirmacionRfuFragment();
        dialogConfirmacionRfuFragment.setTargetFragment(EstadoRfuFragment.this, 1);
        dialogConfirmacionRfuFragment.show(getFragmentManager(), "DialogConfirmacionRfu");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EstadoRfuViewModel.class);
        // TODO: Use the ViewModel
    }

}