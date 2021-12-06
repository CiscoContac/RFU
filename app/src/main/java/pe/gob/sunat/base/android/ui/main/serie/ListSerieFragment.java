package pe.gob.sunat.base.android.ui.main.serie;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.model.Serie;
import pe.gob.sunat.base.android.model.SerieItem;
import pe.gob.sunat.base.android.ui.main.serie.adapter.AdapterSerieList;
import pe.gob.sunat.base.android.ui.main.serie.adapter.AdapterSeriesList;
import pe.gob.sunat.base.android.util.ItemAnimation;
import pe.gob.sunat.base.android.util.LineItemDecoration;
import pe.gob.sunat.base.android.viewmodel.ContenedoresViewModel;
import pe.gob.sunat.base.android.viewmodel.ListSeriesViewModel;


public class ListSerieFragment extends Fragment implements Injectable {

    public static final String TAG = ListSerieFragment.class.getSimpleName();

    @BindView(R.id.recycler_view_serie)
    RecyclerView recyclerView;

    /*@Inject
    ViewModelProvider.Factory viewModelFactory;
   // @BindView(R.id.recycler_view_serie)*
    RecyclerView recyclerView;*/
    private View view;
    private AdapterSeriesList adapterSeriesList;

    private ListSeriesViewModel mViewModel;

    public ListSerieFragment() {
        // Required empty public constructor
    }


    public static ListSerieFragment newInstance(Bundle params) {
        ListSerieFragment fragment = new ListSerieFragment();
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
       /*
        mAdapter = new AdapterSerieList(this.getContext(), new SerieItem(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(mAdapter);
        return view;*/
        view = inflater.inflate(R.layout.fragment_list_serie, container, false);
        return view;
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

        List<Serie> list = new ArrayList<>();
        list.add(new Serie("1", "MINICOMPONENTE LG CL87", "8527910000"));
        list.add(new Serie("2", "PARLANTE BLUETOOTH PORTATIL LG PM7", "8527920000"));
        list.add(new Serie("3", "MINI RADIO CD PLAYER RN7", "8527930000"));
        list.add(new Serie("4", "MINI RADIO CD PLAYER LG, RN9", "8527940000"));
        list.add(new Serie("5", "BARRA SONIDO WIRELESS, LG, SN6Y", "8527950000"));

        adapterSeriesList = new AdapterSeriesList(list, this.getContext(), ItemAnimation.FADE_IN);
        recyclerView.setAdapter(adapterSeriesList);
    }
}