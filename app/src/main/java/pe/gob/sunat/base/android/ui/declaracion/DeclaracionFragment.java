package pe.gob.sunat.base.android.ui.declaracion;

import androidx.lifecycle.ViewModelProvider;

import android.app.SearchManager;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.di.Injectable;
import pe.gob.sunat.base.android.ui.bandeja.BandejaFragment;
import pe.gob.sunat.base.android.viewmodel.DeclaracionViewModel;

public class DeclaracionFragment extends Fragment implements Injectable {

    public static final String TAG = DeclaracionFragment.class.getSimpleName();

    OnFragmentIterationListener listener;

    @BindView(R.id.text_view_modalidad)
    TextView textViewModalidad;
    @BindView(R.id.text_view_tipo_embarque)
    TextView textViewTipoEmbarque;
    @BindView(R.id.text_view_tipo_despacho)
    TextView textViewTipoDespacho;
    @BindView(R.id.text_view_importador_exportador)
    TextView textViewImportadorExportador;
    @BindView(R.id.text_view_agente_aduanas)
    TextView textViewAgenteAduanas;
    @BindView(R.id.text_view_ruc_nombre_consignatario)
    TextView textViewRucNombreConsignatario;
    @BindView(R.id.text_view_domicilio_consignatario)
    TextView textViewDomicilioConsignatario;
    @BindView(R.id.text_view_nombre_organizacion)
    TextView textViewNombreOrganizacion;
    @BindView(R.id.text_view_direccion_organizacion)
    TextView textViewDireccionOrganizacion;
    @BindView(R.id.text_view_valor_fob)
    TextView textViewValorFob;
    @BindView(R.id.text_view_total_clausula_venta)
    TextView textViewTotalClausulaVenta;
    @BindView(R.id.text_view_comision)
    TextView textViewComision;
    @BindView(R.id.text_view_cantidad_series)
    TextView textViewCantidadSeries;
    @BindView(R.id.text_view_flete)
    TextView textViewFlete;
    @BindView(R.id.text_view_otros_gastos_deducibles)
    TextView textViewOtrosGastosDeducibles;
    @BindView(R.id.text_view_peso_bruto)
    TextView textViewPesoBruto;
    @BindView(R.id.text_view_cantidad_bultos)
    TextView textViewCantidadBultos;
    @BindView(R.id.text_view_seguro)
    TextView textViewSeguro;
    @BindView(R.id.text_view_total_ajustes)
    TextView textViewTotalAjustes;
    @BindView(R.id.text_view_peso_neto)
    TextView textViewPesoNeto;
    @BindView(R.id.text_view_direccion_almacen)
    TextView textViewDireccionAlmacen;
    @BindView(R.id.text_view_ruc_nombre_empresa_transporte)
    TextView textViewRucNombreEmpresaTransporte;
    @BindView(R.id.image_view_respuesta_garantia_art160)
    ImageView imageViewGarantia160;
    @BindView(R.id.image_view_respuesta_importador_exportador_oea)
    ImageView imageViewOea;

    @BindView(R.id.btnDocumentosTransporte)
    LinearLayout btnDocumentosTransporte;

    @BindView(R.id.btnFacturasComerciales)
    LinearLayout btnFacturasComerciales;

    @BindView(R.id.btnContenedores)
    LinearLayout btnContenedores;

    @BindView(R.id.btnInformesSini)
    LinearLayout btnInformesSini;

    @BindView(R.id.btnRecepcionMercancias)
    LinearLayout btnRecepcionMercancias;

    @BindView(R.id.btnRecepcionCarga)
    LinearLayout btnRecepcionCarga;

    @BindView(R.id.btnPaseNaranjaRojo)
    LinearLayout btnPaseNaranjaRojo;

    @BindView(R.id.image_button_estado_rf)
    ImageButton imgbtnEstadoRfu;

    @BindView(R.id.image_button_funcionarios_rfu)
    ImageButton imgbtnFuncionariosRfu;

    @BindView(R.id.image_button_despachador_aduanero)
    ImageButton imgbtnDespachadorAduanero;



    Unbinder unbinder;
    private View view;

    private DeclaracionViewModel mViewModel;


    public static DeclaracionFragment newInstance(Bundle bundle) {
        DeclaracionFragment fragment = new DeclaracionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_declaracion, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnDocumentosTransporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                listener.setDocumentosTransporte(bundle);
            }
        });
        btnFacturasComerciales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setFacturasComerciales(bundle);
            }
        });

        btnContenedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setContenedores(bundle);
            }
        });

        btnInformesSini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setInformesSini(bundle);
            }
        });

        btnRecepcionMercancias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setRecepcionMercancias(bundle);
            }
        });

        btnRecepcionCarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setRecepcionCarga(bundle);
            }
        });

        btnPaseNaranjaRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setPaseNaranjaRojo(bundle);
            }
        });

        imgbtnEstadoRfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setEstadoRfu(bundle);
            }
        });

        imgbtnFuncionariosRfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setFuncionariosRfu(bundle);
            }
        });

        imgbtnDespachadorAduanero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                listener.setDespachadorAduanas(bundle);
            }
        });



        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DeclaracionViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnFragmentIterationListener {

        void setDocumentosTransporte(Bundle bundle);

        void setFacturasComerciales(Bundle bundle);

        void setContenedores(Bundle bundle);

        void setInformesSini(Bundle bundle);

        void setRecepcionMercancias(Bundle bundle);

        void setRecepcionCarga(Bundle bundle);

        void setPaseNaranjaRojo(Bundle bundle);

        void setEstadoRfu(Bundle bundle);

        void setFuncionariosRfu(Bundle bundle);

        void setDespachadorAduanas(Bundle bundle);

        void setDocumentoDigitalizado(Bundle bundle);

        void setIndicadorRiesgo(Bundle bundle);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        inflater.inflate(R.menu.menu_burbuja_declaracion, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        switch (id) {

            /**FILTROS**/

            case R.id.nav_documentos_digitalizados:

                listener.setDocumentoDigitalizado(bundle);
                return true;

            case R.id.nav_riesgo:
                listener.setIndicadorRiesgo(bundle);
                return true;

            default:
                return false;
        }

    }
    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof OnFragmentIterationListener) {
            listener = (OnFragmentIterationListener) context;
        } else {
            Log.e(TAG, "El Activity debe implementar la interfaz onFragmentIterationListener");
            throw new RuntimeException(context.toString() + " El Activity debe implementar la interfaz onFragmentIterationListener");
        }
    }
    
}