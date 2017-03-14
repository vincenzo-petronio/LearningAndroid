package it.localhost.app.mobile.learningandroid;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import it.localhost.app.mobile.learningandroid.util.Constants;

/**
 *
 */
public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();

        initRealm();
    }

    private void initRealm() {
        Log.v(TAG, "onCreate");
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Constants.DB_NAME)
                .schemaVersion(Constants.DB_SCHEMA_VERSION)
//                .initialData(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//
//                    }
//                })
//                .inMemory()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
