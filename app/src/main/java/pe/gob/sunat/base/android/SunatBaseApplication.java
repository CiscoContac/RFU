package pe.gob.sunat.base.android;

import android.app.Activity;
import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import pe.gob.sunat.base.android.api.AndroidFastNetworkingClient;
import pe.gob.sunat.base.android.di.AppInjector;

public class SunatBaseApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate(){
        super.onCreate();

        AppInjector.init(this);

    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
