package pe.gob.sunat.base.android.ui.bandeja;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.Declaracion;
import pe.gob.sunat.base.android.ui.bandeja.adapter.AdapterDeclaracion;
import pe.gob.sunat.base.android.util.ItemAnimation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PendientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendientesFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.recycler_view_pendientes)
    RecyclerView recyclerView;

    private View view;
    private AdapterDeclaracion adapterDeclaracion;

    public PendientesFragment() {
        // Required empty public constructor
    }

    public static PendientesFragment newInstance(Bundle bundle) {
        PendientesFragment fragment = new PendientesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_pendientes, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Declaracion> lista = new ArrayList<>();
        lista.add(new Declaracion());
        lista.add(new Declaracion());
        lista.add(new Declaracion());
        lista.add(new Declaracion());
        lista.add(new Declaracion());
        lista.add(new Declaracion());
        lista.add(new Declaracion());
        lista.add(new Declaracion());
        lista.add(new Declaracion());

        adapterDeclaracion = new AdapterDeclaracion(lista, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterDeclaracion);
    }
}