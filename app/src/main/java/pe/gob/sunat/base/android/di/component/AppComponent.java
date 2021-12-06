package pe.gob.sunat.base.android.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import pe.gob.sunat.base.android.SunatBaseApplication;
import pe.gob.sunat.base.android.di.module.ActivityModule;
import pe.gob.sunat.base.android.di.module.AppModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);
        AppComponent build();

    }

    void inject(SunatBaseApplication sunatBaseApplication);
}
