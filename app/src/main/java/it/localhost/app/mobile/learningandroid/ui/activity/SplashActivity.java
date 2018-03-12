package it.localhost.app.mobile.learningandroid.ui.activity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import it.localhost.app.mobile.learningandroid.data.model.Task;

/**
 * @author vincenzo.petronio on 06/03/2018.
 */

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        if (isReadyToStart()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        
        finish();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    private boolean isReadyToStart() {
        return isPlayServicesReady();
    }

    private boolean isPlayServicesReady() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.e(TAG, "This device is not supported: Play Services unavailable!");
            }
            return false;
        }

        return true;
    }
}
