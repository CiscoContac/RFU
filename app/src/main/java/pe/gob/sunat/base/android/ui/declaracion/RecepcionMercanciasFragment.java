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
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterRecepcionMercanciasList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.RecepcionMercanciasViewModel;

public class RecepcionMercanciasFragment extends Fragment implements Injectable {

    public static final String TAG = RecepcionMercanciasFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_recepcion_mercancias)
    RecyclerView recyclerView;

    private View view;
    private AdapterRecepcionMercanciasList adapterRecepcionMercanciasList;

    private RecepcionMercanciasViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static RecepcionMercanciasFragment newInstance(Bundle bundle) {
        RecepcionMercanciasFragment fragment = new RecepcionMercanciasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recepcion_mercancias, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("RECEPCIÓN DE MERCANCÍAS (RM)");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecepcionMercanciasViewModel.class);
        // TODO: Use the ViewModel
        listaRecepcionMercancias();
    }

    private void listaRecepcionMercancias(){
        recyclerView = getView().findViewById(R.id.recycler_view_recepcion_mercancias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<Contenedor> list = new ArrayList<>();


        list.add(new Contenedor("CMAU0327214",10,0));

        list.add(new Contenedor("CRSU1001179",20,0));

        list.add(new Contenedor("TTNU1169272",5,0));

        adapterRecepcionMercanciasList = new AdapterRecepcionMercanciasList(list,
                this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterRecepcionMercanciasList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}