package pe.gob.sunat.base.android.ui.declaracion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.model.Contenedor;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterRecepcionCargasList;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterRecepcionMercanciasList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.RecepcionCargaViewModel;

public class RecepcionCargaFragment extends Fragment implements Injectable {

    public static final String TAG = RecepcionCargaFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_recepcion_cargas)
    RecyclerView recyclerView;

    private View view;
    private AdapterRecepcionCargasList adapterRecepcionCargasList;

    private RecepcionCargaViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static RecepcionCargaFragment newInstance(Bundle bundle) {
        RecepcionCargaFragment fragment = new RecepcionCargaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recepcion_cargas, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("RECEPCIÓN DE CARGAS A EMBARCAR (RCE)");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecepcionCargaViewModel.class);
        // TODO: Use the ViewModel
        listaRecepcionCargas();
    }

    private void listaRecepcionCargas(){
        recyclerView = getView().findViewById(R.id.recycler_view_recepcion_cargas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<Contenedor> list = new ArrayList<>();

        list.add(new Contenedor("CMAU0327214",0,3));
        list.add(new Contenedor("CRSU1001179",0,423));
        list.add(new Contenedor("TTNU1169272",0,134));

        adapterRecepcionCargasList = new AdapterRecepcionCargasList(list,
                this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterRecepcionCargasList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}