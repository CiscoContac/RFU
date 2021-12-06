package pe.gob.sunat.base.android.ui.series.documento;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.viewmodel.DocumentoSeleccionadoViewModel;

public class DocumentoSeleccionadoFragment extends Fragment implements Injectable {

    public static final String TAG = DocumentoSeleccionadoFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    Unbinder unbinder;

    @BindView(R.id.button_listas_archivos_adjunto)
    Button btn_list_lista_archivo_adjunto;

    @BindView(R.id.button_doc_autirizante_IQBF)
    Button btn_list_doc_adjunto_Iqbf;

    private View view;

    private DocumentoSeleccionadoViewModel mViewModel;

    public static DocumentoSeleccionadoFragment newInstance(Bundle bundle) {
        DocumentoSeleccionadoFragment fragment = new DocumentoSeleccionadoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_documento_seleccionado, container, false);
        unbinder = ButterKnife.bind(this, view);

        btn_list_lista_archivo_adjunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setAdjuntoDocumentoControlFragment(bundle);

            }
        });

        btn_list_doc_adjunto_Iqbf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                //llamar al frag
                listener.setDocumentoIqbfFragment(bundle);
            }
        });
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DocumentoSeleccionadoViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnFragmentIterationListener{

        void setDocumentoIqbfFragment (Bundle bundle);

        void setAdjuntoDocumentoControlFragment (Bundle bundle);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof DocumentoSeleccionadoFragment.OnFragmentIterationListener) {
            listener = (DocumentoSeleccionadoFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

}