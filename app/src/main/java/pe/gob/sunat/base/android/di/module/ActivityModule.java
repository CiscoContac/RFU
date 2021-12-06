package pe.gob.sunat.base.android.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.gob.sunat.base.android.ui.main.MainActivity;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
