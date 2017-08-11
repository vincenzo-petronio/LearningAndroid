package it.localhost.app.mobile.learningandroid.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.localhost.app.mobile.learningandroid.App;
import it.localhost.app.mobile.learningandroid.util.NetworkStateManager;

/**
 * Dagger Module.<br>
 * I Modules dicono cosa fornire e come costruire tali oggetti.<br>
 * Con questo Module vengono fornite le dipendenze globali.
 */
@Module
public class AppModule {

    /**
     * E' il Context dell'applicazione
     */
//    private final App mApp;
//
//    public AppModule(@NonNull App application) {
//        this.mApp = application;
//    }

    // I metodi che forniscono gli oggetti per la dependencies injection sono annotati
    // come @Provides.

    /**
     * Fornisce una singola istanza del Context dell'applicazione
     *
     * @return Context
     */
//    @Provides
//    @Singleton
//    Context provideContext() {
//        return mApp;
//    }
    @Provides
    @Singleton
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    /**
     * Fornisce una singola istanza delle SharedPreferences
     *
     * @return SharedPreferences
     */
    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Fornisce una singola istanza della classe ConnectivityManager
     *
     * @return ConnectivityManager
     */
    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * Fornisce una singola istanza della classe NetworkStateManager
     *
     * @param connectivityManager Dipendenza fornita attraverso un altro metodo @Provides
     * @return NetworkStateManager
     */
    @Provides
    @Singleton
    NetworkStateManager provideNetworkStateManager(ConnectivityManager connectivityManager) {
        return new NetworkStateManager(connectivityManager);
    }
}
