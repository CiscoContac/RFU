package pe.gob.sunat.base.android.ui.bandeja;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.bandeja.adapter.ViewPagerAdapter;

public class BandejaFragment extends Fragment implements Injectable {
    public static final String TAG =BandejaFragment.class.getSimpleName();


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private OnFragmentIterationListener listener;

    Unbinder unbinder;
    @BindView(R.id.view_pager_bandeja)
    ViewPager vistaTabBandeja;
    @BindView(R.id.tab_bandeja_principal)
    TabLayout tabBandejaPrincipal;

    private View view;
    private PendientesFragment pendientesFragment;
    private  DiligenciadosFragment diligenciadosFragment;
    private ViewPagerAdapter adapter;

    public BandejaFragment() {
        // Required empty public constructor
    }

    public static BandejaFragment newInstance(Bundle params) {
        BandejaFragment fragment = new BandejaFragment();
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void setUpView(){
        pendientesFragment = new PendientesFragment();
        diligenciadosFragment = new DiligenciadosFragment();
        tabBandejaPrincipal.setupWithViewPager(vistaTabBandeja);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), 0);
        adapter.addFragment(pendientesFragment, "Pendientes");
        adapter.addFragment(diligenciadosFragment, "Diligenciados");
        vistaTabBandeja.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bandeja, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        inflater.inflate(R.menu.menu_bandeja, menu);
    }

    public interface OnFragmentIterationListener{

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof BandejaFragment.OnFragmentIterationListener) {
            listener = (BandejaFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}