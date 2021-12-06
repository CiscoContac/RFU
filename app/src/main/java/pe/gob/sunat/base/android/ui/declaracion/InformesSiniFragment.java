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
import pe.gob.sunat.base.android.model.InformeSini;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterInformesSiniList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.InformesSiniViewModel;

public class InformesSiniFragment extends Fragment implements Injectable {

    public static final String TAG = InformesSiniFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_informessini)
    RecyclerView recyclerView;

    private View view;
    private AdapterInformesSiniList adapterInformesSiniList;

    private InformesSiniViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static InformesSiniFragment newInstance(Bundle bundle) {
        InformesSiniFragment fragment = new InformesSiniFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_informes_sini, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("INFORME DE INSPECCIÓN NO INTRUSIVA");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InformesSiniViewModel.class);
        // TODO: Use the ViewModel
        listaInformeSini();
    }

    private void listaInformeSini(){
        recyclerView = getView().findViewById(R.id.recycler_view_informessini);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<Contenedor> list = new ArrayList<>();
        list.add(new Contenedor("CMAU0327214",
                new InformeSini("SOSPECHOSO","Three line text string example with two " +
                        "actions. One to two lines is preferable. Three lines should be considered" +
                        " the maximum string length on desktop inorder to keep messages short " +
                        "and actionable.")));

        list.add(new Contenedor("CRSU1001179",
                new InformeSini("SOSPECHOSO","Three line text string example with two " +
                        "actions. One to two lines is preferable. Three lines should be considered" +
                        " the maximum string length on desktop inorder to keep messages short " +
                        "and actionable.")));

        list.add(new Contenedor("TTNU1169272",
                new InformeSini("SOSPECHOSO","Three line text string example with two " +
                        "actions. One to two lines is preferable. Three lines should be considered" +
                        " the maximum string length on desktop inorder to keep messages short " +
                        "and actionable.")));

        adapterInformesSiniList = new AdapterInformesSiniList(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterInformesSiniList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}