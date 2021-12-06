package pe.gob.sunat.base.android.ui.series;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.items.DetalleItemFragment;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;

public class SeriesFragment extends Fragment implements Injectable {

    public static final String TAG = SeriesFragment.class.getSimpleName();
    SeriesFragment.OnFragmentIterationListener listener;

    @BindView(R.id.recycler_view_serie)
    RecyclerView recyclerView;

    //Título
    @BindView(R.id.tv_titulo_general)
    TextView tvTitulo;
    Unbinder unbinder;

    /*@Inject
    ViewModelProvider.Factory viewModelFactory;
   // @BindView(R.id.recycler_view_serie)*
    RecyclerView recyclerView;*/
    private View view;
    private AdapterSeries adapterSeriesList;

    private ListSeriesViewModel mViewModel;

    public SeriesFragment() {
        // Required empty public constructor
    }

    public static SeriesFragment newInstance(Bundle params) {
        SeriesFragment fragment = new SeriesFragment();
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list_serie, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitulo.setText("LISTADO DE SERIES");
        setHasOptionsMenu(true);
        return view;
    }

    public interface OnFragmentIterationListener {

        void setSerieDetalle (Bundle bundle);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof SeriesFragment.OnFragmentIterationListener) {
            listener = (SeriesFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListSeriesViewModel.class);
        // TODO: Use the ViewModel
        listaSeries();
    }

    private void listaSeries(){
        recyclerView = getView().findViewById(R.id.recycler_view_serie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new LineItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        recyclerView.setHasFixedSize(true);

        List<TestSeries> list = new ArrayList<>();
        list.add(new TestSeries("1", "MINICOMPONENTE LG CL87", "8527910000", "Detalle Serie 1"));
        list.add(new TestSeries("2", "PARLANTE BLUETOOTH PORTATIL LG PM7", "8527920000", "Detalle Serie 2"));
        list.add(new TestSeries("3", "MINI RADIO CD PLAYER RN7", "8527930000", "Detalle Serie 3"));
        list.add(new TestSeries("4", "MINI RADIO CD PLAYER LG, RN9", "8527940000", "Detalle Serie 4"));
        list.add(new TestSeries("5", "BARRA SONIDO WIRELESS, LG, SN6Y", "8527950000", "Detalle Serie 5"));

        adapterSeriesList = new AdapterSeries(list, this.getContext(), ItemAnimation.FADE_IN);

        //Función para onClick de Items dentro del Recycler
        adapterSeriesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Selección de Item", Toast.LENGTH_SHORT).show();
                //Cambiar a FragmentDetalleItem
                Bundle bundle = new Bundle();
                listener.setSerieDetalle(bundle);
            }
        });

        recyclerView.setAdapter(adapterSeriesList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        inflater.inflate(R.menu.menu_opciones_adicionales_series, menu);
    }
}
