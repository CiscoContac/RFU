package pe.gob.sunat.base.android.ui.series;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.items.DetalleItemFragment;
import pe.gob.sunat.base.android.ui.items.ListItemsFragment;
import pe.gob.sunat.base.android.ui.series.fotos.SerieFotosFragment;
import pe.gob.sunat.base.android.ui.series.notas.SerieNotasFragment;
import pe.gob.sunat.base.android.ui.series.reposicion.SerieReposicionMercanciasFragment;

public class DetalleSerieFragment extends Fragment implements Injectable {

    public static final String TAG = DetalleSerieFragment.class.getSimpleName();
    DetalleSerieFragment.OnFragmentIterationListener listener;
    Unbinder unbinder;
    private View view;

    FragmentTransaction transaction;
    @BindView(R.id.iv_series_fotos)
    ImageView fotos;
    @BindView(R.id.iv_series_notas)
    ImageView notas;
    @BindView(R.id.iv_series_items)
    ImageView items;
    @BindView(R.id.layout_reposicion_mercancias)
    LinearLayout reposicionMercancias;
    @BindView(R.id.layout_comprobante_pago_electronico)
    LinearLayout comprobantePago;

    @BindView(R.id.layout_documento_control)
    LinearLayout detalleDocumentoControl;

    public static DetalleSerieFragment newInstance(Bundle bundle) {
        DetalleSerieFragment fragment = new DetalleSerieFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detalle_serie, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public interface OnFragmentIterationListener {

        void setSerieFoto (Bundle bundle);

        void setSerieNota (Bundle bundle);

        void setSerieItem (Bundle bundle);

        void setSerieReposicionMercancias (Bundle bundle);

        void setSerieComprobantesPago (Bundle bundle);

        void setDocumentoControlFragment (Bundle bundle);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof DetalleSerieFragment.OnFragmentIterationListener) {
            listener = (DetalleSerieFragment.OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(FragmentDetalleItemViewModel.class);
        // TODO: Use the ViewModel

        notas = getView().findViewById(R.id.iv_series_notas);
        fotos = getView().findViewById(R.id.iv_series_fotos);
        items = getView().findViewById(R.id.iv_series_items);
        reposicionMercancias = getView().findViewById(R.id.layout_reposicion_mercancias);
        comprobantePago = getView().findViewById(R.id.layout_comprobante_pago_electronico);

        detalleDocumentoControl = getView().findViewById(R.id.layout_documento_control);

        notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Aquí van las notas.",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                listener.setSerieNota(bundle);
            }
        });

        fotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Aquí van las fotos.",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                listener.setSerieFoto(bundle);
            }
        });

        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Aquí van los items.",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                listener.setSerieItem(bundle);
            }
        });

        reposicionMercancias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Aquí va el certificado de reposición de mercancías.",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                listener.setSerieReposicionMercancias(bundle);
            }
        });

        comprobantePago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),"Aquí van los comprobantes de pago.",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                listener.setSerieComprobantesPago(bundle);
            }
        });

        detalleDocumentoControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDocumentoControlFragment(bundle);
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    }
}
