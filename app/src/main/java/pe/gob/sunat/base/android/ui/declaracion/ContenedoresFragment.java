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
import pe.gob.sunat.base.android.model.FacturaComercial;
import pe.gob.sunat.base.android.model.Precinto;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterContenedoresList;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterFactuComercList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.ContenedoresViewModel;

public class ContenedoresFragment extends Fragment implements Injectable {

    public static final String TAG = ContenedoresFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_contenedores)
    RecyclerView recyclerView;
    Unbinder unbinder;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    private View view;
    private AdapterContenedoresList adapterContenedoresList;

    private ContenedoresViewModel mViewModel;

    public static ContenedoresFragment newInstance(Bundle bundle) {
        ContenedoresFragment fragment = new ContenedoresFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_contenedores, container, false);
       unbinder = ButterKnife.bind(this, view);
       tvTitulo.setText("LISTADO DE CONTENEDORES");
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContenedoresViewModel.class);
        // TODO: Use the ViewModel
        listaContenedores();
    }

    private void listaContenedores(){
        recyclerView = getView().findViewById(R.id.recycler_view_contenedores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<Contenedor> list = new ArrayList<>();
        List<Precinto> precintos1 = new ArrayList<>();
        List<Precinto> precintos2 = new ArrayList<>();
        List<Precinto> precintos3 = new ArrayList<>();
        precintos1.add(new Precinto("P42000208"));
        precintos1.add(new Precinto("P42000209"));
        list.add(new Contenedor("CMAU0327214","---",precintos1));
        precintos2.add(new Precinto("G8625177"));
        precintos2.add(new Precinto("G8625178"));
        list.add(new Contenedor("CRSU1001179","---",precintos2));
        precintos3.add(new Precinto("G8625203"));
        precintos3.add(new Precinto("G8625205"));
        list.add(new Contenedor("TTNU1169272","---",precintos3));

        adapterContenedoresList = new AdapterContenedoresList(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterContenedoresList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}