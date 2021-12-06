package pe.gob.sunat.base.android.ui.series.documento;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.AdjuntoDocumentoControlViewModel;

public class AdjuntoDocumentoControlFragment extends Fragment {

    public static final String TAG =AdjuntoDocumentoControlFragment.class.getSimpleName();

    private AdjuntoDocumentoControlViewModel mViewModel;

    @BindView(R.id.recycler_view_adjunt_doc_control)
    RecyclerView recyclerView;

    private View view;
    private AdapterAdjuntoDocumentoControl adapterAdjuntoDocumentoControl;

    private AdjuntoDocumentoControlFragment(){}

    public static AdjuntoDocumentoControlFragment newInstance(Bundle bundle) {
        AdjuntoDocumentoControlFragment fragment = new AdjuntoDocumentoControlFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adjunto_documento_control, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdjuntoDocumentoControlViewModel.class);
        // TODO: Use the ViewModel
        listaDocAdjunto();
    }

    private void listaDocAdjunto(){
        recyclerView = getView().findViewById(R.id.recycler_view_adjunt_doc_control);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<TestAdjuntoDocumento> list = new ArrayList<>();

        list.add(new TestAdjuntoDocumento("expediente.pdf"));
        list.add(new TestAdjuntoDocumento("DR2021040203.pdf"));
        list.add(new TestAdjuntoDocumento("ACUSERECIBO.pdf"));
        list.add(new TestAdjuntoDocumento("suce202100230.pdf"));

        adapterAdjuntoDocumentoControl = new AdapterAdjuntoDocumentoControl(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterAdjuntoDocumentoControl);

    }


}