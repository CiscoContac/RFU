package pe.gob.sunat.base.android.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import pe.gob.sunat.base.android.BaseActivity;
import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.model.Serie;
import pe.gob.sunat.base.android.ui.bandeja.BandejaFragment;

import pe.gob.sunat.base.android.ui.declaracion.DeclaracionFragment;
import pe.gob.sunat.base.android.ui.declaracion.DeudaTributariaFragment;
import pe.gob.sunat.base.android.ui.declaracion.FacturasComercialesFragment;
import pe.gob.sunat.base.android.ui.fotos.FotosFragment;
import pe.gob.sunat.base.android.ui.gestion.GestionEstadoRfuFragment;
import pe.gob.sunat.base.android.ui.gestion.HerramientasGestionFragment;
import pe.gob.sunat.base.android.ui.items.DetalleItemFragment;
import pe.gob.sunat.base.android.ui.items.ListItemsFragment;
import pe.gob.sunat.base.android.ui.notas.NotasFragment;
import pe.gob.sunat.base.android.ui.registrorfu.AgregarFotoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DatoComplementarioFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DatoReconocimientoFisicoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DespachoAuxliarFragment;
import pe.gob.sunat.base.android.ui.registrorfu.ModificarPrecintoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.ResumenDatoIngresadoFragment;
import pe.gob.sunat.base.android.ui.series.DetalleSerieFragment;
import pe.gob.sunat.base.android.ui.series.SeriesFragment;
import pe.gob.sunat.base.android.ui.series.documento.DocumentoControlFragment;
import pe.gob.sunat.base.android.ui.series.documento.DocumentoIqbfFragment;
import pe.gob.sunat.base.android.ui.series.documento.DocumentoSeleccionadoFragment;
import pe.gob.sunat.base.android.ui.tiempo.TiemposAtencionFragment;
import pe.gob.sunat.base.android.ui.tiempo.TiemposPromedioFragment;
import pe.gob.sunat.base.android.util.Constantes;
import pe.gob.sunat.base.android.util.Navigation;
import pe.gob.sunat.base.android.util.Tools;

public class MainActivity extends BaseActivity implements
        HasSupportFragmentInjector,
        BandejaFragment.OnFragmentIterationListener,
        DeclaracionFragment.OnFragmentIterationListener,
        HerramientasGestionFragment.OnFragmentIterationListener,
        GestionEstadoRfuFragment.OnFragmentIterationListener,
        TiemposAtencionFragment.OnFragmentIterationListener,
        TiemposPromedioFragment.OnFragmentIterationListener,
        DatoReconocimientoFisicoFragment.OnFragmentIterationListener,
        DespachoAuxliarFragment.OnFragmentIterationListener,
        DatoComplementarioFragment.OnFragmentIterationListener,
        ListItemsFragment.OnFragmentIterationListener,
        SeriesFragment.OnFragmentIterationListener,
        DetalleSerieFragment.OnFragmentIterationListener,
        FotosFragment.OnFragmentIterationListener,
        NotasFragment.OnFragmentIterationListener,
        DetalleItemFragment.OnFragmentIterationListener,
        AgregarFotoFragment.OnFragmentIterationListener,
        DeudaTributariaFragment.OnFragmentIterationListener,
        DocumentoSeleccionadoFragment.OnFragmentIterationListener,
        DocumentoIqbfFragment.OnFragmentIterationListener,
        DocumentoControlFragment.OnFragmentIterationListener,
        ResumenDatoIngresadoFragment.OnFragmentIterationListener,
        ModificarPrecintoFragment.OnFragmentIterationListener
{


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private final Handler handler = new Handler();
    private boolean isGPSLocation;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.main_fragment_placeholder)
    FrameLayout lytContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottom_navigation_view_dam)
    BottomNavigationView bottomNavigationViewDam;

    @Inject
    Navigation navigation;

    private String[] activityTitles;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    private ActionBar actionBar;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setSupportActionBar(toolbar);

        if (!session.isLoggedIn()) {
            //showTokenDialog();
        }

        initToolbarHome();
       // drawer.openDrawer(navigationView);
        setInitialFragment();
    }
    private void setInitialFragment() {
        Bundle bundle = new Bundle();
        irBandeja(bundle);
        navegacion(bundle);
    }
    private void initToolbarHome() {

        toolbar.setNavigationIcon(R.drawable.ic_menu);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        Tools.setSystemBarColor(this);
        initNavigationMenu();
    }

    private void initNavigationMenu() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawer.closeDrawers();
                displayView(menuItem.getItemId());
                return true;
            }
        });
    }


    private void activeNavigation(String tipo) {

        if (Constantes.ARG_TITULO_BANDEJA.equals(tipo)) {
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_bandeja).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_dam).setEnabled(false);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_series).setEnabled(false);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_diligencia).setEnabled(false);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_bandeja).setChecked(true);
        } else if (Constantes.ARG_TITULO_DAM.equals(tipo)) {
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_dam).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_bandeja).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_series).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_diligencia).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_dam).setChecked(true);

        } else if (Constantes.ARG_TITULO_SERIES.equals(tipo)) {
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_series).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_dam).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_bandeja).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_diligencia).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_series).setChecked(true);
        } else if (Constantes.ARG_TITULO_DILIGENCIA.equals(tipo)) {
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_diligencia).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_series).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_dam).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_bandeja).setEnabled(true);
            bottomNavigationViewDam.getMenu().findItem(R.id.navigation_diligencia).setChecked(true);
        }


    }

    private void displayView(int viewId) {
        Bundle bundle = new Bundle();
        switch (viewId) {
            case R.id.nav_bandeja:
                irBandeja(bundle);
                break;
            case R.id.nav_prueba:
                irDeclaracion(bundle);
                break;

            case R.id.nav_registro_rfu:
                irRegistroDeligenciaRFU(bundle);
                break;
            case R.id.nav_herramientas_gestion_rfu:
                irHerramientasGestionRFU(bundle);
                break;
            case R.id.nav_deuda_tributaria:
                irDeudaTributaria(bundle);
                break;
            case R.id.nav_sign_out:
                showLogoutDialog();
                break;
        }
    }

    private void navegacion(Bundle bundle) {
        bottomNavigationViewDam.setOnNavigationItemSelectedListener(item -> {

            boolean marca = true;
            switch (item.getItemId()) {
                case R.id.navigation_bandeja:
//                    setBandejaFragment(bundle);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    break;
                case R.id.navigation_dam:
                    irDeclaracion(bundle);
                    break;
                case R.id.navigation_series:
                    setListSerie(bundle);
                    marca = false;
                    break;
                case R.id.navigation_diligencia:
                    irRegistroDeligenciaRFU(bundle);
                    marca = false;
                    break;
            }
            return marca;
        });
    }

    private void irDeclaracion(Bundle bundle) {
        initToolbarBack();
        getSupportActionBar().setTitle("DAM 118-2021-10-000234");

        navigation.navigationToDeclaracionFragment(bundle);
        activeNavigation(Constantes.ARG_TITULO_DAM);
    }
    private void initToolbarBack() {

        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
    }
    private void irBandeja(Bundle bundle) {
        getSupportActionBar().setTitle("DILIGENCIA DE RECONOCIMIENTO FISICO UNICO (RFU)");
        navigation.navigateToInitialBandejaFragment(bundle);
    }

    private void irRegistroDeligenciaRFU(Bundle bundle) {
        getSupportActionBar().setTitle("DAM 118-2021-10-000234");
        navigation.navigationToDiligenciaRFUFragment(bundle);
    }

    private void irHerramientasGestionRFU(Bundle bundle) {
        navigation.navigationToHerramientasGestionFragment(bundle);
    }

    private void irListadoItems(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234 - SERIE 1");
        navigation.navigationToListItemsFragment(bundle);
    }

    private void irDetalleItem(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234 - SERIE 1");
        navigation.navigationToDetalleItemFragment(bundle);
    }

    private void irListadoSeries(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234");
        navigation.navigationToSeriesFragment(bundle);
    }

    private void irDetalleSerie(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234");
        navigation.navigationToDetalleSerieFragment(bundle);
    }

    private void irFotoItem(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234 - SERIE 1 - ITEM 1");
        navigation.navigationToFotosFragment(bundle);
    }

    private void irNotaItem(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234 - SERIE 1 - ITEM 1");
        navigation.navigationToNotasFragment(bundle);
    }

    private void irFotoSerie(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234 - SERIE 1");
        navigation.navigationToSerieFotosFragment(bundle);
    }

    private void irNotaSerie(Bundle bundle){
        getSupportActionBar().setTitle("DAM 118-2021-10-000234 - SERIE 1");
        navigation.navigationToSerieNotasFragment(bundle);
    }
    private void irDeudaTributaria(Bundle bundle){
        navigation.navigationToDeudaTributariaFragment(bundle);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();


    }



    @Override
    public void setDocumentosTransporte(Bundle bundle) {
        navigation.navigationToDocumentosTransporteFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setFacturasComerciales(Bundle bundle) {
        navigation.navigationToFacturasComercialesFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setContenedores(Bundle bundle) {
        navigation.navigationToContenedoresFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setInformesSini(Bundle bundle) {
        navigation.navigationToInformesSiniFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setRecepcionMercancias(Bundle bundle) {
        navigation.navigationToRecepcionMercanciasFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setRecepcionCarga(Bundle bundle) {
        navigation.navigationToRecepcionCargaFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setPaseNaranjaRojo(Bundle bundle) {
        navigation.navigationToPaseNaranjaRojoFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setEstadoRfu(Bundle bundle) {
        navigation.navigationToEstadoRfuFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setFuncionariosRfu(Bundle bundle) {
        navigation.navigationToFuncionariosRfuFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setDespachadorAduanas(Bundle bundle) {
        navigation.navigationToDespachadorAduanasFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setDocumentoDigitalizado(Bundle bundle) {
        navigation.navigationToDocumentosDigitalizadosFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setIndicadorRiesgo(Bundle bundle) {
        navigation.navigationToIndicadorRiesgo(bundle);
        toolbar.setNavigationOnClickListener(v -> irDeclaracion(bundle));
    }

    @Override
    public void setDetalleItem(Bundle bundle) {
        navigation.navigationToDetalleItemFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irListadoItems(bundle));
    }


    public void setListSerie(Bundle bundle) {
        navigation.navigationToSeriesFragment(bundle);
    }


    @Override
    public void setGestionEstadoRfu(Bundle bundle) {
        navigation.navigationToGestionEstadoRfuFragment(bundle);
    }

    @Override
    public void setGestionTiemposAtencion(Bundle bundle) {
        navigation.navigationToTiemposAtencionFragment(bundle);
    }

    @Override
    public void setGestionTiemposPromedio(Bundle bundle) {
        navigation.navigationToTiemposPromedioFragment(bundle);
    }


    @Override
    public void setGestionEstadoRfuGrafico(Bundle bundle) {
        navigation.navigationToGestionEstadoRfuGraficoFragment(bundle);
    }

    @Override
    public void setGestionTiemposAtencionGrafico(Bundle bundle) {
        navigation.navigationToTiemposAtencionGraficoFragment(bundle);

    }

    @Override
    public void setGestionTiemposPromedioGrafico(Bundle bundle) {
        navigation.navigationToTiemposPromedioGraficoFragment(bundle);
    }


    @Override
    public void setResumenDatoIngresado(Bundle bundle) {
        navigation.navigationToResumenDatoIngresadoFragment(bundle);
    }

    @Override
    public void setModificarPrecintoFragment(Bundle bundle) {
        navigation.navigationToModificarPrecintoFragment(bundle);
    }

    @Override
    public void setListaFotosFragment(Bundle bundle) {
        navigation.navigationToAgregaFotoFragment(bundle);
    }

    @Override
    public void setDespachoAuxliarFragment(Bundle bundle) {
        navigation.navigationToDespachoAuxliarFragment(bundle);
    }

    @Override
    public void setDatoComplementarioFragment(Bundle bundle) {
        navigation.navigationToDatoComplementarioFragment(bundle);
    }

    @Override
    public void setDatoReconocimientoFisicoFragment(Bundle bundle) {
        navigation.navigationToDatoReconocimientoFisicoFragment(bundle);
    }


    @Override
    public void setSerieDetalle(Bundle bundle) {
        navigation.navigationToDetalleSerieFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irListadoSeries(bundle));
    }

    @Override
    public void setSerieFoto(Bundle bundle) {
        navigation.navigationToSerieFotosFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDetalleSerie(bundle));
    }

    @Override
    public void setSerieNota(Bundle bundle) {
        navigation.navigationToSerieNotasFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDetalleSerie(bundle));
    }

    @Override
    public void setSerieItem(Bundle bundle) {
        navigation.navigationToListItemsFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDetalleSerie(bundle));
    }

    @Override
    public void setSerieReposicionMercancias(Bundle bundle) {
        navigation.navigationToSerieReposicionMercanciasFragment(bundle);
    }

    @Override
    public void setSerieComprobantesPago(Bundle bundle) {
        navigation.navigationToComprobantesPagoFragment(bundle);
    }

    @Override
    public void setDocumentoControlFragment(Bundle bundle) {
        navigation.navigationToDocumentoControlFragment(bundle);
    }

    @Override
    public void setFoto(Bundle bundle) {
        navigation.navigationToFotosFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDetalleSerie(bundle));
    }

    @Override
    public void setNota(Bundle bundle) {
        navigation.navigationToNotasFragment(bundle);
        toolbar.setNavigationOnClickListener(v -> irDetalleItem(bundle));
    }

    @Override
    public void setLiquidacionesCobranza(Bundle bundle) {
        navigation.navigationToLiquidacionesCobranzaFragment(bundle);
    }

    @Override
    public void setDocumentoSeleccionado(Bundle bundle) {
        navigation.navigationToDocumentoSeleccionadoFragment(bundle);
    }

    @Override
    public void setDocumentoIqbfFragment(Bundle bundle) {
        navigation.navigationToDocumentoIqbfFragment(bundle);
    }

    @Override
    public void setAdjuntoDocumentoControlFragment(Bundle bundle) {
        navigation.navigationToAdjuntoDocumentoControlFragment(bundle);
    }

    @Override
    public void setDatoComplementarioFragmemt(Bundle bundle) {
        navigation.navigationToDatoComplementarioFragment(bundle);
    }
}
