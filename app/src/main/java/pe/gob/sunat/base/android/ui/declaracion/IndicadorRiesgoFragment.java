package pe.gob.sunat.base.android.ui.declaracion;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.model.Riesgo;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterDetalleRiesgo;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterIndicadorRiesgo;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.viewmodel.DeudaTributariaViewModel;

public class IndicadorRiesgoFragment extends Fragment implements Injectable {

    public static final String TAG = IndicadorRiesgoFragment.class.getSimpleName();




    Unbinder unbinder;
    private View view;

    private DeudaTributariaViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    @BindView(R.id.recycler_lista_riesgo)
    RecyclerView recyclerListaRiesgo;

    @BindView(R.id.recycler_detalle_riesgo)
    RecyclerView recyclerDetalleRiesgo;

    @BindView(R.id.ll_detalle_Riesgo)
    LinearLayout  llDetalleRiesgo;



    AdapterIndicadorRiesgo adapterIndicadorRiesgo;
    AdapterDetalleRiesgo adapterDetalleRiesgo;

    public static IndicadorRiesgoFragment newInstance(Bundle bundle) {
        IndicadorRiesgoFragment fragment = new IndicadorRiesgoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_indicador_riesgo, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText(getResources().getString(R.string.title_indicadores_riesgo));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DeudaTributariaViewModel.class);
        // TODO: Use the ViewModel
        listaIndicador();
    }

    private void listaIndicador(){
        recyclerListaRiesgo.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerListaRiesgo.setHasFixedSize(true);

        List<Riesgo> list = new ArrayList<>();
        list.add(new Riesgo("FMV","0","INCUMPLIMIENTO RELACIONADOS A MERCANCIAS RESTRINGIDAS","VERIFICAR MARCA Y MODELO DE LAS MERCANCIAS","TNUEER12","5"));
        list.add(new Riesgo("FMV","6","INCUMPLIMIENTO RELACIONADOS A MERCANCIAS RESTRINGIDAS","VERIFICAR MARCA Y MODELO DE LAS MERCANCIAS","TNUEER13","4"));
        list.add(new Riesgo("FMV","1","INCUMPLIMIENTO RELACIONADOS A MERCANCIAS RESTRINGIDAS","VERIFICAR MARCA Y MODELO DE LAS MERCANCIAS","TNUEER12","5"));



        adapterIndicadorRiesgo = new AdapterIndicadorRiesgo(list,getContext(), ItemAnimation.FADE_IN);
        adapterIndicadorRiesgo.setOnItemClickListener(new AdapterIndicadorRiesgo.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Riesgo item, int position) {

                    Bundle params = getArguments();

                    recyclerDetalleRiesgo.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerDetalleRiesgo.setHasFixedSize(true);
                    adapterDetalleRiesgo = new AdapterDetalleRiesgo(list,getContext(),ItemAnimation.FADE_IN);

                recyclerDetalleRiesgo.setAdapter(adapterDetalleRiesgo);
                llDetalleRiesgo.setVisibility(View.VISIBLE);


            }
        });
        recyclerListaRiesgo.setAdapter(adapterIndicadorRiesgo);

    }



 /*   @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof OnFragmentIterationListener) {
            listener = (OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }*/
}