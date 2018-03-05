package it.localhost.app.mobile.learningandroid.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *
 */

public class NetworkStateManager {

    private ConnectivityManager connectivityManager;

    public NetworkStateManager(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    /**
     * Restituisce true se la connessione è stabilita, false altrimenti.
     *
     * @return boolean
     */
    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
