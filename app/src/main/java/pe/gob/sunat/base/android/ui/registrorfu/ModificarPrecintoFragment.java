package pe.gob.sunat.base.android.ui.registrorfu;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.registrorfu.adapter.AdapterModificarPrecintoList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.ModificarPrecintoViewModel;

public class ModificarPrecintoFragment extends Fragment  implements Injectable {

    public static final String TAG = ModificarPrecintoFragment.class.getSimpleName();

    ModificarPrecintoFragment.OnFragmentIterationListener listener;

    Unbinder unbinder;

    @BindView(R.id.recycler_view_modificar_precinto)
    RecyclerView recyclerView;

    @BindView(R.id.btn_modificar_prec_regresar)
    Button btn_regresar;

    private View view;
    private AdapterModificarPrecintoList adapterModificarPrecintoList;

    private ModificarPrecintoViewModel mViewModel;

    public ModificarPrecintoFragment(){

    }

    public static ModificarPrecintoFragment newInstance(Bundle bundle) {
        ModificarPrecintoFragment fragment = new ModificarPrecintoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_modificar_precinto, container, false);
        unbinder = ButterKnife.bind(this,view);

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDatoComplementarioFragmemt(bundle);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ModificarPrecintoViewModel.class);
        // TODO: Use the ViewModel
        listadecontenedores();
    }

    private void listadecontenedores(){

        recyclerView = getView().findViewById(R.id.recycler_view_modificar_precinto);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<TestPrecinto> list = new ArrayList<>();
        list.add(new TestPrecinto("P42000208","P4567981","Sin Modificar"));
        list.add(new TestPrecinto("P42000202","P4567982","Sin Modificar"));
        list.add(new TestPrecinto("P42000203","P4567983","Sin Modificar"));

        adapterModificarPrecintoList = new AdapterModificarPrecintoList(list, this.getContext(), ItemAnimation.FADE_IN);

        adapterModificarPrecintoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),"Selccion de item",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                //listener.setSerieDetalle(bundle);
            }
        });
        recyclerView.setAdapter(adapterModificarPrecintoList);

    }

    public interface OnFragmentIterationListener {
        void setDatoComplementarioFragmemt(Bundle bundle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof ModificarPrecintoFragment.OnFragmentIterationListener) {
            listener = (ModificarPrecintoFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

}