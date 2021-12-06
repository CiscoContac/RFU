package pe.gob.sunat.base.android.di.module;


import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pe.gob.sunat.base.android.model.Session;
import pe.gob.sunat.base.android.viewmodel.ProjectViewModelFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    Session provideSession(Context context){

        return new Session(context);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ProjectViewModelFactory(viewModelSubComponent.build());
    }
}
