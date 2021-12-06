package pe.gob.sunat.base.android.api;

import android.content.Context;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class AndroidFastNetworkingClient {
    public static  OkHttpClient.Builder getokhttpclient(Context context) throws CertificateException, IOException,
            KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
/*
        CertificateFactory cf= null;
        cf = CertificateFactory.getInstance("X.509");

        Certificate ca;

        int certificado = R.raw.certificadolocal;

        if(BuildConfig.BUILD_TYPE.equals("local")){
            certificado = R.raw.certificadolocal;
        }else if(BuildConfig.BUILD_TYPE.equals("developer")){
            certificado = R.raw.certificadodesarrollo;
        }else if(BuildConfig.BUILD_TYPE.equals("qa")){
            certificado = R.raw.certificadocalidad;
        }else if(BuildConfig.BUILD_TYPE.equals("release")){
            certificado = R.raw.certificadoproduccion;
        }

        InputStream cert = context.getResources().openRawResource(certificado);
        try{
            ca=cf.generateCertificate(cert);
        }finally {
            cert.close();
        }


        String KeyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(KeyStoreType);
        keyStore.load(null,null);
        keyStore.setCertificateEntry("ca",ca);
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        TrustManager[] trustManagers = tmf.getTrustManagers();
        X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
*/


        final TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("SSL");
        //sslContext.init(null,tmf.getTrustManagers(),null); version anterior
        sslContext.init(null,trustAllCerts,new java.security.SecureRandom());

        // Create an ssl socket factory with our all-trusting manager
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


        OkHttpClient.Builder builder = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory);//,x509TrustManager );
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        return builder;


    }
}
