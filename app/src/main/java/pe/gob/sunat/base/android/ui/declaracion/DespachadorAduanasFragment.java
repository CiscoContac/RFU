package pe.gob.sunat.base.android.ui.declaracion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.viewmodel.DespachadorAduanasViewModel;

public class DespachadorAduanasFragment extends Fragment implements Injectable {

    public static final String TAG = DespachadorAduanasFragment.class.getSimpleName();

    private View view;

    private DespachadorAduanasViewModel mViewModel;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    public static DespachadorAduanasFragment newInstance(Bundle bundle) {
        DespachadorAduanasFragment fragment = new DespachadorAduanasFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_despachador_aduanas, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("DESPACHADOR DE ADUANAS");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AutoCompleteTextView autoCompleteTextView = getView().findViewById(R.id.autoCompleteTextView);
        List<String> list = new ArrayList<String>(Arrays.asList(
                getResources().getStringArray(R.array.tipo_documento)));
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(),
                R.layout.dropdown_tipo_documento,
                R.id.textViewTiposDocumentos,
                list);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DespachadorAduanasViewModel.class);
        // TODO: Use the ViewModel
    }

}