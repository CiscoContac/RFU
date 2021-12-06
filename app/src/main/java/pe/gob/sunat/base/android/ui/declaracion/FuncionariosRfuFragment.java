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
import pe.gob.sunat.base.android.model.FuncionarioRFU;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterFuncionariosRfuList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.FuncionariosRfuViewModel;

public class FuncionariosRfuFragment extends Fragment implements Injectable {

    public static final String TAG = FuncionariosRfuFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_funcionarios_rfu)
    RecyclerView recyclerView;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    private View view;
    private AdapterFuncionariosRfuList adapterFuncionariosRfuList;

    private FuncionariosRfuViewModel mViewModel;

    public static FuncionariosRfuFragment newInstance(Bundle bundle) {
        FuncionariosRfuFragment fragment = new FuncionariosRfuFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_funcionarios_rfu, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("FUNCIONARIOS DE RECONOCIMIENTO FISICO ÚNICO");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FuncionariosRfuViewModel.class);
        // TODO: Use the ViewModel
        listaFuncionariosRfu();
    }

    private void listaFuncionariosRfu(){
        recyclerView = getView().findViewById(R.id.recycler_view_funcionarios_rfu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<FuncionarioRFU> list = new ArrayList<>();

        List<String> telefonos1 = new ArrayList<>();
        telefonos1.add("961696798");
        telefonos1.add("961696799");

        List<String> telefonos2 = new ArrayList<>();
        telefonos2.add("961796798");
        telefonos2.add("961796799");

        list.add(new FuncionarioRFU("9876","VILLARAN FLORES",
                "FIORELLA GARCIA","fgarciav@sunat.gob.pe",
                "Funcionario del RFU/RFU SINI",telefonos1,"9876perfil.png"));

        list.add(new FuncionarioRFU("5432","OTERO LACHIRA",
                "JOSE LUIS","joterolv@sunat.gob.pe",
                "Funcionario del Régimen",telefonos2,"5432perfil.png"));


        adapterFuncionariosRfuList = new AdapterFuncionariosRfuList(list,
                this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterFuncionariosRfuList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}