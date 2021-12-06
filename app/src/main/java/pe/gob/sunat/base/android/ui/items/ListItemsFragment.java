package pe.gob.sunat.base.android.ui.items;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.declaracion.DeclaracionFragment;
import pe.gob.sunat.base.android.ui.tiempo.TiemposPromedioGraficoFragment;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;


public class ListItemsFragment extends Fragment implements Injectable {

    public static final String TAG = ListItemsFragment.class.getSimpleName();
    OnFragmentIterationListener listener;

    FragmentTransaction transaction;
    @BindView(R.id.recycler_view_items)
    RecyclerView recyclerViewItems;

    Unbinder unbinder;
    private View view;
    private AdapterItems adapterItems;
    private ListItemsViewModel itemsViewModel;

    //Detalle Items
    String detalleItem;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;


    public static ListItemsFragment newInstance(Bundle bundle) {
        ListItemsFragment fragment = new ListItemsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTADO DE ÍTEMS");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        itemsViewModel = new ViewModelProvider(this).get(ListItemsViewModel.class);
        // TODO: Use the ViewModel
        agregarItems();
    }

    public interface OnFragmentIterationListener {

        void setDetalleItem (Bundle bundle);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof ListItemsFragment.OnFragmentIterationListener) {
            listener = (ListItemsFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

    private void agregarItems(){
        recyclerViewItems = getView().findViewById(R.id.recycler_view_items);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewItems.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerViewItems.setHasFixedSize(true);

        ArrayList<TestItems> listaItems = new ArrayList<>();
        listaItems.add(new TestItems("1", "Componente 1", R.drawable.ic_camera, R.drawable.ic_down, "123045206"));
        listaItems.add(new TestItems("2", "Componente 2", R.drawable.ic_camera, R.drawable.ic_down, "123045207"));
        listaItems.add(new TestItems("3", "Componente 3", R.drawable.ic_camera, R.drawable.ic_down, "123045208"));

        adapterItems = new AdapterItems(listaItems, this.getContext(), ItemAnimation.FADE_IN);

        //Función para onClick de Items dentro del Recycler
        adapterItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Selección de Item", Toast.LENGTH_SHORT).show();
                //Cambiar a FragmentDetalleItem
                Bundle bundle = new Bundle();
                listener.setDetalleItem(bundle);
            }
        });

        recyclerViewItems.setAdapter(adapterItems);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        inflater.inflate(R.menu.menu_opciones_adicionales_series, menu);
    }

}