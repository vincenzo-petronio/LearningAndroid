package it.localhost.app.mobile.learningandroid.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import android.util.Log;

import it.localhost.app.mobile.learningandroid.App;

/**
 * @author vincenzo.petronio on 06/03/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
        Log.v(TAG, "onTokenRefresh");
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Refreshed token: " + refreshedToken);

        App.getInstance().getsSharedPrefs().saveFirebaseToken(App.getInstance().getApplicationContext(), refreshedToken);
    }
}
