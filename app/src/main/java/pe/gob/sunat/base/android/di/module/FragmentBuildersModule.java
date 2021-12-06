package pe.gob.sunat.base.android.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
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
import pe.gob.sunat.base.android.ui.fotos.FotosFragment;
import pe.gob.sunat.base.android.ui.gestion.GestionEstadoRfuFragment;
import pe.gob.sunat.base.android.ui.gestion.GestionEstadoRfuGraficoFragment;
import pe.gob.sunat.base.android.ui.gestion.HerramientasGestionFragment;
import pe.gob.sunat.base.android.ui.items.DetalleItemFragment;
import pe.gob.sunat.base.android.ui.items.ListItemsFragment;
import pe.gob.sunat.base.android.ui.main.serie.ListSerieFragment;
import pe.gob.sunat.base.android.ui.notas.NotasFragment;
import pe.gob.sunat.base.android.ui.registrorfu.AgregarFotoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DatoComplementarioFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DatoReconocimientoFisicoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.DespachoAuxliarFragment;
import pe.gob.sunat.base.android.ui.registrorfu.ModificarPrecintoFragment;
import pe.gob.sunat.base.android.ui.registrorfu.ResumenDatoIngresadoFragment;
import pe.gob.sunat.base.android.ui.series.DetalleSerieFragment;
import pe.gob.sunat.base.android.ui.series.SeriesFragment;
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

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract BandejaFragment contributeBandejaFragment();

    @ContributesAndroidInjector
    abstract DeclaracionFragment contributeDeclaracionFragment();

    @ContributesAndroidInjector
    abstract DocumentosTransporteFragment contributeDocumentosTransporteFragment();

    @ContributesAndroidInjector
    abstract FacturasComercialesFragment contributeFacturasComercialesFragment();

    @ContributesAndroidInjector
    abstract ContenedoresFragment contributeContenedoresFragment();

    @ContributesAndroidInjector
    abstract InformesSiniFragment contributeInformesSiniFragment();

    @ContributesAndroidInjector
    abstract RecepcionMercanciasFragment contributeRecepcionMercanciasFragment();

    @ContributesAndroidInjector
    abstract RecepcionCargaFragment contributeRecepcionCargaFragment();

    @ContributesAndroidInjector
    abstract PaseNaranjaRojoFragment contributePaseNaranjaRojoFragment();

    @ContributesAndroidInjector
    abstract EstadoRfuFragment contributeEstadoRfuFragment();

    @ContributesAndroidInjector
    abstract FuncionariosRfuFragment contributeFuncionariosRfuFragment();

    @ContributesAndroidInjector
    abstract DespachadorAduanasFragment contributeDespachadorAduanasFragment();

    @ContributesAndroidInjector
    abstract HerramientasGestionFragment contributeHerramientasGestionFragment();

    @ContributesAndroidInjector
    abstract GestionEstadoRfuFragment contributeGestionEstadoRfuFragment();

    @ContributesAndroidInjector
    abstract GestionEstadoRfuGraficoFragment contributeGestionEstadoRfuGraficoFragment();

    @ContributesAndroidInjector
    abstract TiemposAtencionFragment contributeTiemposAtencionFragment();

    @ContributesAndroidInjector
    abstract TiemposAtencionGraficoFragment contributeTiemposAtencionGraficoFragment();

    @ContributesAndroidInjector
    abstract TiemposPromedioFragment contributeTiemposPromedioFragment();

    @ContributesAndroidInjector
    abstract TiemposPromedioGraficoFragment contributeTiemposPromedioGraficoFragment();

    @ContributesAndroidInjector
    abstract DatoReconocimientoFisicoFragment contributeDatoReconocimientoFisicoFragment();

    @ContributesAndroidInjector
    abstract DespachoAuxliarFragment contributeDespachoAuxliarFragment();

    @ContributesAndroidInjector
    abstract DatoComplementarioFragment contributeDatoComplementarioFragment();

    @ContributesAndroidInjector
    abstract ResumenDatoIngresadoFragment contributeResumenDatoIngresadoFragment();

    @ContributesAndroidInjector
    abstract ModificarPrecintoFragment contributeModificarPrecintoFragment();

    @ContributesAndroidInjector
    abstract ListSerieFragment contributeListSerieFragment();

    @ContributesAndroidInjector
    abstract SeriesFragment contributeSeriesFragment();

    @ContributesAndroidInjector
    abstract ListItemsFragment contributeListItemsFragment();

    @ContributesAndroidInjector
    abstract DetalleItemFragment contributeDetalleItemFragment();

    @ContributesAndroidInjector
    abstract DetalleSerieFragment contributeDetalleSerieFragment();

    @ContributesAndroidInjector
    abstract SerieFotosFragment contributeSerieFotosFragment();

    @ContributesAndroidInjector
    abstract SerieNotasFragment contributeSerieNotasFragment();

    @ContributesAndroidInjector
    abstract SerieReposicionMercanciasFragment contributeSerieReposicionMercanciasFragment();

    @ContributesAndroidInjector
    abstract FotosFragment contributeFotosFragment();

    @ContributesAndroidInjector
    abstract NotasFragment contributeNotasFragment();

    @ContributesAndroidInjector
    abstract AgregarFotoFragment contributeAgregarFotoFragment();

    @ContributesAndroidInjector
    abstract DocumentoControlFragment contributeDocumentoControlFragment();

    @ContributesAndroidInjector
    abstract DeudaTributariaFragment conntributeDeudaTributariaFragment();

    @ContributesAndroidInjector
    abstract LiquidacionesCobranzaFragment contributeLiquidacionesCobranzaFragment();

    @ContributesAndroidInjector
    abstract DocumentoSeleccionadoFragment contributeDocumentoSeleccionadoFragment();

    @ContributesAndroidInjector
    abstract AdjuntoDocumentoControlFragment contributeAdjuntoDocumentoControlFragment();

    @ContributesAndroidInjector
    abstract DocumentoIqbfFragment contributeDocumentoIqbfFragment();
    @ContributesAndroidInjector
    abstract IndicadorRiesgoFragment contributeIndicadorRiesgoFragment();

}
