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
import pe.gob.sunat.base.android.BaseActivity;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.model.DocumentoTransporte;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterDocTransList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.DocumentosTransporteViewModel;

public class DocumentosTransporteFragment extends Fragment implements Injectable {

    public static final String TAG = DocumentosTransporteFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_doctrans)
    RecyclerView recyclerView;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    private View view;
    private AdapterDocTransList adapterDocTransList;

    private DocumentosTransporteViewModel mViewModel;

    public static DocumentosTransporteFragment newInstance(Bundle bundle) {
        DocumentosTransporteFragment fragment = new DocumentosTransporteFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_documentos_transporte, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTADO DE DOCUMENTOS DE TRANSPORTE");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DocumentosTransporteViewModel.class);
        // TODO: Use the ViewModel
        listaDocumentos();
    }

    private void listaDocumentos(){
        recyclerView = getView().findViewById(R.id.recycler_view_doctrans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<DocumentoTransporte> list = new ArrayList<>();
        list.add(new DocumentoTransporte("NEK0-NYAN-001"));
        list.add(new DocumentoTransporte("NEK0-NYAN-023"));
        list.add(new DocumentoTransporte("NEK0-NYAN-034"));
        list.add(new DocumentoTransporte("NEK0-NYAN-041"));
        list.add(new DocumentoTransporte("NEK0-NYAN-048"));
        list.add(new DocumentoTransporte("NEK0-NYAN-052"));
        list.add(new DocumentoTransporte("NEK0-NYAN-058"));
        list.add(new DocumentoTransporte("NEK0-NYAN-063"));
        list.add(new DocumentoTransporte("NEK0-NYAN-069"));
        list.add(new DocumentoTransporte("NEK0-NYAN-071"));
        list.add(new DocumentoTransporte("NEK0-NYAN-074"));

        adapterDocTransList = new AdapterDocTransList(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterDocTransList);
        //Eliminar la linea de decoracion entre elementos
        while (recyclerView.getItemDecorationCount()>0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

}