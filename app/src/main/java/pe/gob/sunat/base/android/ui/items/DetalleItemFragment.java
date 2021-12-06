package pe.gob.sunat.base.android.ui.items;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.fotos.FotosFragment;
import pe.gob.sunat.base.android.ui.notas.NotasFragment;

public class DetalleItemFragment extends Fragment implements Injectable {

    public static final String TAG = DetalleItemFragment.class.getSimpleName();
    DetalleItemFragment.OnFragmentIterationListener listener;
    Unbinder unbinder;
    private View view;

    FragmentTransaction transaction;
    @BindView(R.id.iv_fotos)
    ImageView fotos;
    @BindView(R.id.iv_notas)
    ImageView notas;
    //private FragmentDetalleItemViewModel mViewModel;


    public static DetalleItemFragment newInstance(Bundle bundle) {
        DetalleItemFragment fragment = new DetalleItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detalle_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public interface OnFragmentIterationListener {

        void setFoto (Bundle bundle);

        void setNota (Bundle bundle);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof DetalleItemFragment.OnFragmentIterationListener) {
            listener = (DetalleItemFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(FragmentDetalleItemViewModel.class);
        // TODO: Use the ViewModel

        notas = getView().findViewById(R.id.iv_notas);
        fotos = getView().findViewById(R.id.iv_fotos);

        notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Aquí van las notas.",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                listener.setNota(bundle);
            }
        });

        fotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Aquí van las fotos.",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                listener.setFoto(bundle);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }


}