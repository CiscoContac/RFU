package pe.gob.sunat.base.android.ui.series.documento;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.DocumentoControlViewModel;

public class DocumentoControlFragment extends Fragment {

    public static final  String TAG = DocumentoControlFragment.class.getSimpleName();
    DocumentoControlFragment.OnFragmentIterationListener listener;
    Unbinder unbinder;

    @BindView(R.id.recycler_view_documento_control)
    RecyclerView recyclerView;

    private View view;
    private AdapterDocumentoControl adapterDocumentoControl;

    private DocumentoControlViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    public DocumentoControlFragment(){

    }

    public static DocumentoControlFragment newInstance(Bundle bundle) {
        DocumentoControlFragment fragment = new  DocumentoControlFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_documento_control, container, false);
        unbinder = ButterKnife.bind(this,view);
        tvTitulo.setText("LISTADO DE DOCUMENTOS DE CONTROL");
        return view;
    }

    public interface OnFragmentIterationListener {

        void setDocumentoSeleccionado (Bundle bundle);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if(context instanceof  DocumentoControlFragment.OnFragmentIterationListener){
            listener = (DocumentoControlFragment.OnFragmentIterationListener)context;
        }else {

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DocumentoControlViewModel.class);
        // TODO: Use the ViewModel
        listaDocumentoControl();
    }

    private void listaDocumentoControl(){
        recyclerView = getView().findViewById(R.id.recycler_view_documento_control);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<TestDocumentoControl> list = new ArrayList<>();
        list.add(new TestDocumentoControl("98 - CODIGO DE EXONERACION QUE EXIGE", "", "EXONERADO"));
        list.add(new TestDocumentoControl("07 - INFORMER TECNICO DE AUTORIZACIÓN" , "2021049301" , "05 - SENASA (MINAG)"));
        list.add(new TestDocumentoControl("21 - DOCUMENTO RESOLUTIVO VUCE" , "2021102930", "04 - DIGESA"));
        list.add(new TestDocumentoControl("14 - AUTORIZACION DE IMPORTACION IQBF" ,"167" , "30- SUNAT INT.NAC.INS. QUIMICO Y BS FISCALIZADOS"));

        adapterDocumentoControl= new AdapterDocumentoControl(list,this.getContext(), ItemAnimation.FADE_IN);
        adapterDocumentoControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDocumentoSeleccionado(bundle);
            }
        });
        recyclerView.setAdapter(adapterDocumentoControl);
    }

}