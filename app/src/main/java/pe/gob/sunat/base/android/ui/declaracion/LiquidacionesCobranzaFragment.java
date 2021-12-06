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
import pe.gob.sunat.base.android.model.LiquidacionCobranza;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterLiquidacionesCobranzaList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.LiquidacionesCobranzaViewModel;

public class LiquidacionesCobranzaFragment extends Fragment implements Injectable {

    public static final String TAG = LiquidacionesCobranzaFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_liquidaciones_cobranza)
    RecyclerView recyclerView;

    private View view;
    private AdapterLiquidacionesCobranzaList adapterLiquidacionesCobranzaList;

    private LiquidacionesCobranzaViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static LiquidacionesCobranzaFragment newInstance(Bundle bundle) {
        LiquidacionesCobranzaFragment fragment = new LiquidacionesCobranzaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liquidaciones_cobranza, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTA DE LIQUIDACIONES DE COBRANZA");
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LiquidacionesCobranzaViewModel.class);
        // TODO: Use the ViewModel
        listasLiquidacionesCobranza();
    }

    private void listasLiquidacionesCobranza(){
        recyclerView = getView().findViewById(R.id.recycler_view_liquidaciones_cobranza);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<LiquidacionCobranza> list = new ArrayList<>();
        list.add(new LiquidacionCobranza("235-21-000213", "PERCEPCION IGV",
                "GARANTIZADO ART 160","25/10/2021","31/07/2021",
                250.0,"L/C COMPLEMENTARIA A LA DUA 235-21-000213 DE FECHA" +
                " 31/07/2021 PERCEPCION DE IGV - LEY 28053"));
        list.add(new LiquidacionCobranza("235-21-000214", "UTOLIQ ART 13 OMC DUDA" +
                " RAZONABLE GENERAL", "GARANTIZADO ART 160","25/10/2021",
                "31/07/2021", 550.0,"POR DIFERENCIA DE AJUSTE DE VALOR" +
                " SERIES NO. 1,2,3,4, DUA 235-21-000213 DE FECHA 31/07/2021"));

        adapterLiquidacionesCobranzaList = new AdapterLiquidacionesCobranzaList(list,
                this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterLiquidacionesCobranzaList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}