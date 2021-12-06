package pe.gob.sunat.base.android.util;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import java.security.PublicKey;

import javax.inject.Inject;

import pe.gob.sunat.base.android.R;
import pe.gob.sunat.base.android.ui.bandeja.BandejaFragment;
import pe.gob.sunat.base.android.ui.declaracion.ContenedoresFragment;
import pe.gob.sunat.base.android.ui.declaracion.DeclaracionFragment;
import pe.gob.sunat.base.android.ui.declaracion.DespachadorAduanasFragment;
import pe.gob.sunat.base.android.ui.declaracion.DeudaTributariaFragment;
import pe.gob.sunat.base.android.ui.declaracion.DocumentosTransporteFragment;
import pe.gob.sunat.base.android.ui.declaracion.EstadoRfuFragment;
import pe.gob.sunat.base.android.ui.declaracion.FacturasComercialesFragment;
import pe.gob.sunat.base.android.ui.declaracion.FuncionariosRfuFragment;
import pe.gob.sunat.base.android.ui.declaracion.IndicadorRiesgoFragment;
import pe.gob.sunat.base.android.ui.declaracion.InformesSiniFragment;
import pe.gob.sunat.base.android.ui.declaracion.LiquidacionesCobranzaFragment;
import pe.gob.sunat.base.android.ui.declaracion.PaseNaranjaRojoFragment;
import pe.gob.sunat.base.android.ui.declaracion.RecepcionCargaFragment;
import pe.gob.sunat.base.android.ui.declaracion.RecepcionMercanciasFragment;
import pe.gob.sunat.base.android.ui.documentos.DocumentosDigitalizadosFragment;
import pe.gob.sunat.base.android.ui.fotos.FotosFragment;
import pe.gob.sunat.base.android.ui.gestion.GestionEstadoRfuFragment;
import pe.gob.sunat.base.android.ui.gestion.GestionEstadoRfuGraficoFragment;
import pe.gob.sunat.base.android.ui.gestion.HerramientasGestionFragment;
import pe.gob.sunat.base.android.ui.items.DetalleItemFragment;
import pe.gob.sunat.base.android.ui.items.ListItemsFragment;
import pe.gob.sunat.base.android.ui.main.MainActivity;
import pe.gob.sunat.base.android.ui.main.serie.ListSerieFragment;
import pe.gob.sunat.base.android.ui.notas.NotasFragment;
import pe.gob.sunat.base.android.ui.registrorfu.AgregarFotoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DatoComplementarioFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DatoReconocimientoFisicoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DespachoAuxliarFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DiligenciaRfuFragment;
import pe.gob.sunat.base.android.ui.registrorfu.ModificarPrecintoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.ResumenDatoIngresadoFragment;
import pe.gob.sunat.base.android.ui.series.DetalleSerieFragment;
import pe.gob.sunat.base.android.ui.series.SeriesFragment;
import pe.gob.sunat.base.android.ui.series.comprobantes.ComprobantesPagoFragment;
import pe.gob.sunat.base.android.ui.series.documento.AdjuntoDocumentoControlFragment;
import pe.gob.sunat.base.android.ui.series.documento.DocumentoControlFragment;
import pe.gob.sunat.base.android.ui.series.documento.DocumentoIqbfFragment;
import pe.gob.sunat.base.android.ui.series.documento.DocumentoSeleccionadoFragment;
import pe.gob.sunat.base.android.ui.series.fotos.SerieFotosFragment;
import pe.gob.sunat.base.android.ui.series.notas.SerieNotasFragment;
import pe.gob.sunat.base.android.ui.series.reposicion.SerieReposicionMercanciasFragment;
import pe.gob.sunat.base.android.ui.tiempo.TiemposAtencionFragment;
import pe.gob.sunat.base.android.ui.tiempo.TiemposAtencionGraficoFragment;
import pe.gob.sunat.base.android.ui.tiempo.TiemposPromedioFragment;
import pe.gob.sunat.base.android.ui.tiempo.TiemposPromedioGraficoFragment;

public class Navigation {
    private final int containerId;
    private final FragmentManager fragmentManager;
    private final Context context;

    @Inject
    public Navigation(MainActivity mainActivity) {
        this.containerId = R.id.main_fragment_placeholder;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
        this.context = mainActivity.getApplicationContext();
    }

    public void navigateToInitialBandejaFragment(Bundle bundle){
        fragmentManager.beginTransaction()
                .addToBackStack(BandejaFragment.TAG).replace(containerId, BandejaFragment.newInstance(bundle),BandejaFragment.TAG)
                .commit();
    }

    public void navigationToDeclaracionFragment(Bundle bundle){
        fragmentManager.beginTransaction()
                .addToBackStack(DeclaracionFragment.TAG).replace(containerId, DeclaracionFragment.newInstance(bundle), DeclaracionFragment.TAG).commit();
    }

    public  void navigationToDocumentosTransporteFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DocumentosTransporteFragment.TAG).replace(containerId, DocumentosTransporteFragment.newInstance(bundle), DocumentosTransporteFragment.TAG).commit();
    }

    public void navigationToFacturasComercialesFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(FacturasComercialesFragment.TAG).replace(containerId, FacturasComercialesFragment.newInstance(bundle),FacturasComercialesFragment.TAG).commit();
    }

    public void navigationToContenedoresFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(ContenedoresFragment.TAG).replace(containerId, ContenedoresFragment.newInstance(bundle),ContenedoresFragment.TAG).commit();
    }

    public void navigationToInformesSiniFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(InformesSiniFragment.TAG).replace(containerId, InformesSiniFragment.newInstance(bundle),InformesSiniFragment.TAG).commit();
    }

    public void navigationToRecepcionMercanciasFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(RecepcionMercanciasFragment.TAG).replace(containerId, RecepcionMercanciasFragment.newInstance(bundle),RecepcionMercanciasFragment.TAG).commit();
    }

    public void navigationToRecepcionCargaFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(RecepcionCargaFragment.TAG).replace(containerId, RecepcionCargaFragment.newInstance(bundle),RecepcionCargaFragment.TAG).commit();
    }
    public void navigationToPaseNaranjaRojoFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(PaseNaranjaRojoFragment.TAG).replace(containerId, PaseNaranjaRojoFragment.newInstance(bundle),PaseNaranjaRojoFragment.TAG).commit();
    }

    public void navigationToEstadoRfuFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(EstadoRfuFragment.TAG).replace(containerId, EstadoRfuFragment.newInstance(bundle),EstadoRfuFragment.TAG).commit();
    }

    public void navigationToFuncionariosRfuFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(FuncionariosRfuFragment.TAG).replace(containerId, FuncionariosRfuFragment.newInstance(bundle),FuncionariosRfuFragment.TAG).commit();
    }

    public void navigationToDespachadorAduanasFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DespachadorAduanasFragment.TAG).replace(containerId, DespachadorAduanasFragment.newInstance(bundle),DespachadorAduanasFragment.TAG).commit();
    }

    public void navigationToGestionEstadoRfuFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(GestionEstadoRfuFragment.TAG).replace(containerId, GestionEstadoRfuFragment.newInstance(bundle),GestionEstadoRfuFragment.TAG).commit();
    }

    public void navigationToHerramientasGestionFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(HerramientasGestionFragment.TAG).replace(containerId, HerramientasGestionFragment.newInstance(bundle),HerramientasGestionFragment.TAG).commit();
    }

    public void navigationToTiemposAtencionFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(TiemposAtencionFragment.TAG).replace(containerId, TiemposAtencionFragment.newInstance(bundle),TiemposAtencionFragment.TAG).commit();
    }

    public void navigationToTiemposPromedioFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(TiemposPromedioFragment.TAG).replace(containerId, TiemposPromedioFragment.newInstance(bundle),TiemposPromedioFragment.TAG).commit();
    }

    public void navigationToGestionEstadoRfuGraficoFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(GestionEstadoRfuGraficoFragment.TAG).replace(containerId, GestionEstadoRfuGraficoFragment.newInstance(bundle),GestionEstadoRfuGraficoFragment.TAG).commit();
    }

    public void navigationToTiemposAtencionGraficoFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(TiemposAtencionGraficoFragment.TAG).replace(containerId, TiemposAtencionGraficoFragment.newInstance(bundle),TiemposAtencionGraficoFragment.TAG).commit();
    }

    public void navigationToTiemposPromedioGraficoFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(TiemposPromedioGraficoFragment.TAG).replace(containerId, TiemposPromedioGraficoFragment.newInstance(bundle),TiemposPromedioGraficoFragment.TAG).commit();
    }

    // APARTADO DE FRAGMENTOS DE ITEMS Y FUNCIONALIDADES
    public void navigationToListItemsFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(ListItemsFragment.TAG).replace(containerId, ListItemsFragment.newInstance(bundle), ListItemsFragment.TAG).commit();
    }

    public void navigationToDetalleItemFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DetalleItemFragment.TAG).replace(containerId, DetalleItemFragment.newInstance(bundle), DetalleItemFragment.TAG).commit();
    }

    public void navigationToFotosFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(FotosFragment.TAG).replace(containerId, FotosFragment.newInstance(bundle), FotosFragment.TAG).commit();
    }

    public void navigationToNotasFragment (Bundle bundle) {
        fragmentManager.beginTransaction().addToBackStack(NotasFragment.TAG).replace(containerId, NotasFragment.newInstance(bundle), NotasFragment.TAG).commit();
    }

    public void navigationToDatoComplementarioFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DatoComplementarioFragment.TAG).replace(containerId, DatoComplementarioFragment.newInstance(bundle),DatoComplementarioFragment.TAG).commit();
    }

    public void navigationToResumenDatoIngresadoFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(ResumenDatoIngresadoFragment.TAG).replace(containerId, ResumenDatoIngresadoFragment.newInstance(bundle),ResumenDatoIngresadoFragment.TAG).commit();
    }

    public void navigationToDatoReconocimientoFisicoFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DatoReconocimientoFisicoFragment.TAG).replace(containerId, DatoReconocimientoFisicoFragment.newInstance(bundle),DatoReconocimientoFisicoFragment.TAG).commit();
    }

    public void navigationToDiligenciaRFUFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DiligenciaRfuFragment.TAG).replace(containerId, DiligenciaRfuFragment.newInstance(bundle),DiligenciaRfuFragment.TAG).commit();
    }

    public void navigationToDespachoAuxliarFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DespachoAuxliarFragment.TAG).replace(containerId, DespachoAuxliarFragment.newInstance(bundle),DespachoAuxliarFragment.TAG).commit();
    }

    public void navigationToModificarPrecintoFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(ModificarPrecintoFragment.TAG).replace(containerId, ModificarPrecintoFragment.newInstance(bundle),ModificarPrecintoFragment.TAG).commit();
    }

    public void navigationToListSerieFragment(Bundle bundle){
         fragmentManager.beginTransaction().addToBackStack(DeclaracionFragment.TAG).replace(containerId, ListSerieFragment.newInstance(bundle), ListSerieFragment.TAG).commit();
    }

    //APARTADO DE FRAGMENTOS DE SERIES Y FUNCIONALIDADES
    public void navigationToSeriesFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(SeriesFragment.TAG).replace(containerId, SeriesFragment.newInstance(bundle), SeriesFragment.TAG).commit();
    }

    public void navigationToDetalleSerieFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DetalleSerieFragment.TAG).replace(containerId, DetalleSerieFragment.newInstance(bundle), DetalleSerieFragment.TAG).commit();
    }

    public void navigationToSerieFotosFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(SerieFotosFragment.TAG).replace(containerId, SerieFotosFragment.newInstance(bundle), SerieFotosFragment.TAG).commit();
    }

    public void navigationToSerieNotasFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(SerieNotasFragment.TAG).replace(containerId, SerieNotasFragment.newInstance(bundle), SerieNotasFragment.TAG).commit();
    }

    public void navigationToSerieReposicionMercanciasFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(SerieReposicionMercanciasFragment.TAG).replace(containerId, SerieReposicionMercanciasFragment.newInstance(bundle), SerieReposicionMercanciasFragment.TAG).commit();
    }

    public void navigationToComprobantesPagoFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(ComprobantesPagoFragment.TAG).replace(containerId, ComprobantesPagoFragment.newInstance(bundle), ComprobantesPagoFragment.TAG).commit();
    }


    public void navigationToAgregaFotoFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(AgregarFotoFragment.TAG).replace(containerId,AgregarFotoFragment.newInstance(bundle),AgregarFotoFragment.TAG).commit();
    }

    public void navigationToDocumentoControlFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(ContenedoresFragment.TAG).replace(containerId, DocumentoControlFragment.newInstance(bundle),DocumentoControlFragment.TAG).commit();
    }

    public void navigationToDeudaTributariaFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DeudaTributariaFragment.TAG).replace(containerId,DeudaTributariaFragment.newInstance(bundle),DeudaTributariaFragment.TAG).commit();
    }

    public void navigationToLiquidacionesCobranzaFragment(Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(LiquidacionesCobranzaFragment.TAG).replace(containerId, LiquidacionesCobranzaFragment.newInstance(bundle),LiquidacionesCobranzaFragment.TAG).commit();
    }
    public void navigationToDocumentosDigitalizadosFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DocumentosDigitalizadosFragment.TAG).replace(containerId,DocumentosDigitalizadosFragment.newInstance(bundle),DocumentosDigitalizadosFragment.TAG).commit();
    }

    public void navigationToDocumentoSeleccionadoFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(DocumentoSeleccionadoFragment.TAG).replace(containerId,DocumentoSeleccionadoFragment.newInstance(bundle),DocumentoSeleccionadoFragment.TAG).commit();
    }

    public void navigationToAdjuntoDocumentoControlFragment (Bundle bundle){
        fragmentManager.beginTransaction().addToBackStack(AdjuntoDocumentoControlFragment.TAG).replace(containerId, AdjuntoDocumentoControlFragment.newInstance(bundle),AdjuntoDocumentoControlFragment.TAG).commit();
    }

    public void navigationToDocumentoIqbfFragment (Bundle bundle) {
        fragmentManager.beginTransaction().addToBackStack(DocumentoIqbfFragment.TAG).replace(containerId, DocumentoIqbfFragment.newInstance(bundle), DocumentoIqbfFragment.TAG).commit();
    }
    public void navigationToIndicadorRiesgo (Bundle bundle) {
        fragmentManager.beginTransaction().addToBackStack(IndicadorRiesgoFragment.TAG).replace(containerId, IndicadorRiesgoFragment.newInstance(bundle), IndicadorRiesgoFragment.TAG).commit();
    }

}
