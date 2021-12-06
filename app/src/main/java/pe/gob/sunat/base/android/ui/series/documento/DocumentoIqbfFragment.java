package pe.gob.sunat.base.android.ui.series.documento;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.viewmodel.DocumentoIqbfViewModel;

public class DocumentoIqbfFragment extends Fragment {

    public static final String TAG = DocumentoIqbfFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    private DocumentoIqbfViewModel mViewModel;

    public static DocumentoIqbfFragment newInstance(Bundle bundle) {
        DocumentoIqbfFragment fragment = new DocumentoIqbfFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_documento_iqbf, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DocumentoIqbfViewModel.class);
        // TODO: Use the ViewModel
    }
    public interface OnFragmentIterationListener{

    }

}