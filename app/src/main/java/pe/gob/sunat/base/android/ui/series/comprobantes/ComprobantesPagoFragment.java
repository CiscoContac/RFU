package pe.gob.sunat.base.android.ui.series.comprobantes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;

public class ComprobantesPagoFragment extends Fragment {

    public static final String TAG = ComprobantesPagoFragment.class.getSimpleName();
    Unbinder unbinder;

    @BindView(R.id.recycler_view_comprobantes)
    RecyclerView recyclerView;

    private View view;
    private AdapterComprobantesPago adapterComprobantesPago;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public ComprobantesPagoFragment() {
        // Required empty public constructor
    }

    public static ComprobantesPagoFragment newInstance(Bundle bundle){
        ComprobantesPagoFragment fragment = new ComprobantesPagoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_comprobante, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTADO DE COMPROBANTES DE PAGO");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        listaComprobantes();
    }

    private void listaComprobantes(){
        recyclerView = getView().findViewById(R.id.recycler_view_comprobantes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<TestComprobantesPago> list = new ArrayList<>();
        list.add(new TestComprobantesPago("E001-000230","20100192650 - MICHELLY S.A.","Factura",
                "00 - Del exportador", "20100192650 - MICHELLY S.A.", "31/07/2021","FOB"));
        list.add(new TestComprobantesPago("NC01-000020","20100192650 - MICHELLY S.A.","Nota de Crédito",
                "00 - Del exportador", "20100192650 - MICHELLY S.A.", "31/07/2021","CIF"));
        list.add(new TestComprobantesPago("B001-002001","20100192650 - MICHELLY S.A.","Boleta de Venta",
                "00 - Del exportador", "20100192650 - MICHELLY S.A.", "29/07/2021","CIF"));
        list.add(new TestComprobantesPago("ND01-000023","20100192650 - MICHELLY S.A.","Nota de Débito",
                "00 - Del exportador", "20100192650 - MICHELLY S.A.", "21/07/2021","FOB"));
        list.add(new TestComprobantesPago("E001-000230","20100192650 - MICHELLY S.A.","Factura",
                "00 - Del exportador", "20100192650 - MICHELLY S.A.", "20/07/2021","FOB"));

        adapterComprobantesPago = new AdapterComprobantesPago(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterComprobantesPago);
    }

}
