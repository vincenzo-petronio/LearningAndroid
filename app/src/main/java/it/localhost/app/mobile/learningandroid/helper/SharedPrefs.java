package it.localhost.app.mobile.learningandroid.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.util.Constants;

/**
 * @author vincenzo.petronio on 08/03/2018.
 */

public class SharedPrefs {

    private static final String TAG = SharedPrefs.class.getSimpleName();
    private static SharedPrefs sInstance;

    private static final String FILENAME_SHARED_BASE = R.class.getPackage().getName() + ".shared_base";

    public static SharedPrefs getInstance() {
        if (sInstance == null) {
            Log.i(TAG, "new Instance of SharedPrefs!");
            sInstance = new SharedPrefs();
        }

        return sInstance;
    }


    private SharedPreferences getSharedPreferences(Context context, String filename) {
        return context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

//    private SharedPreferences.Editor getEditor() {
//        return getSharedPreferences().getSharedPreferences(filename, Context.MODE_PRIVATE).edit();
//    }

    /**
     * @param context Context
     * @param token   String
     */
    public void saveFirebaseToken(Context context, String token) {
        sInstance.getSharedPreferences(context, FILENAME_SHARED_BASE)
                .edit()
                .putString(Constants.SHARED_KEY_FIREBASE_TOKEN, token)
                .apply();
    }

    /**
     * @param context Context
     * @return String
     */
    public String getFirebaseToken(Context context) {
        return getSharedPreferences(context, FILENAME_SHARED_BASE)
                .getString(Constants.SHARED_KEY_FIREBASE_TOKEN, "");
    }
}
