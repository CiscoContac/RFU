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
import pe.gob.sunat.base.android.model.DocumentoTransporte;
import pe.gob.sunat.base.android.model.FacturaComercial;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterDocTransList;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterFactuComercList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.FacturasComercialesViewModel;

public class FacturasComercialesFragment extends Fragment implements Injectable {

    public static final String TAG = FacturasComercialesFragment.class.getSimpleName();


    @BindView(R.id.recycler_view_factucomerc)
    RecyclerView recyclerView;

    private View view;
    private AdapterFactuComercList adapterFactuComercList;

    private FacturasComercialesViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static FacturasComercialesFragment newInstance(Bundle bundle) {
        FacturasComercialesFragment fragment = new FacturasComercialesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_facturas_comerciales, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTADO DE FACTURAS COMERCIALES");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FacturasComercialesViewModel.class);
        // TODO: Use the ViewModel
        listaFacturas();
    }

    private void listaFacturas(){
        recyclerView = getView().findViewById(R.id.recycler_view_factucomerc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<FacturaComercial> list = new ArrayList<>();
        list.add(new FacturaComercial("E001-009876"));
        list.add(new FacturaComercial("E101-000100"));
        list.add(new FacturaComercial("E201-000021"));
        list.add(new FacturaComercial("E301-009876"));
        list.add(new FacturaComercial("E401-000100"));
        list.add(new FacturaComercial("E501-000021"));
        list.add(new FacturaComercial("E601-009876"));
        list.add(new FacturaComercial("E701-000100"));
        list.add(new FacturaComercial("E801-000021"));
        list.add(new FacturaComercial("E901-009876"));
        list.add(new FacturaComercial("E991-000100"));


        adapterFactuComercList = new AdapterFactuComercList(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterFactuComercList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}