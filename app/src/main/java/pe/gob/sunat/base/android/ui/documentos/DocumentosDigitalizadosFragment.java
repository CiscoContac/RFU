package pe.gob.sunat.base.android.ui.documentos;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.DocumentoDigitalArchivo;
import pe.gob.sunat.base.android.model.DocumentoTransporte;
import pe.gob.sunat.base.android.ui.declaracion.DeclaracionFragment;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterDocDigitalizadoList;
import pe.gob.sunat.base.android.ui.declaracion.adapter.AdapterDocTransList;
import pe.gob.sunat.base.android.ui.registrorfu.AgregarFotoFragment;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.DocumentosDigitalizadosViewModel;

public class DocumentosDigitalizadosFragment extends Fragment {

    public static final String TAG = DocumentosDigitalizadosFragment.class.getSimpleName();
    Unbinder unbinder;
    private View view;

    private DocumentosDigitalizadosViewModel mViewModel;

    @BindView(R.id.recycler_documento_Digita)
    RecyclerView recyclerView;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;

    AdapterDocDigitalizadoList adapterDocDigitalizadoList;

    public static DocumentosDigitalizadosFragment newInstance(Bundle bundle) {
        DocumentosDigitalizadosFragment fragment = new DocumentosDigitalizadosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_documentos_digitalizados, container, false);
        unbinder = ButterKnife.bind(this, view);

        tvTitulo.setText(getResources().getString(R.string.title_documentos_digitalizados));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DocumentosDigitalizadosViewModel.class);
        // TODO: Use the ViewModel
        listaDocumentos();
        inicializarEventos();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void listaDocumentos() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        List<DocumentoDigitalArchivo> list = new ArrayList<>();

        list.add(new DocumentoDigitalArchivo("DOCUMENTO TRANSPORTE", "BL021334", "10/10/2019", "IMPORTACION PARA EL CONSUMO", "DOCUMENTO QUE SUSTENTAN EL DESPACHO", "", "", ""));
        list.add(new DocumentoDigitalArchivo("CERTIFICADO DE ORIGEN", "RESOLUCIÓN MTC", "10/10/2019", "IMPORTACION PARA EL CONSUMO", "DOCUMENTO QUE SUSTENTAN EL DESPACHO", "", "", ""));


        adapterDocDigitalizadoList = new AdapterDocDigitalizadoList(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterDocDigitalizadoList);
    }
    private void inicializarEventos(){

    }


}