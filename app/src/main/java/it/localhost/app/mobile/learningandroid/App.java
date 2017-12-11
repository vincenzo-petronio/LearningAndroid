package it.localhost.app.mobile.learningandroid;

import com.akaita.java.rxjava2debug.RxJava2Debug;
import com.facebook.soloader.SoLoader;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import it.localhost.app.mobile.learningandroid.dagger.components.DaggerAppComponent;
import it.localhost.app.mobile.learningandroid.util.Constants;

/**
 *
 */
public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();

        initDagger();
        initRealm();
        initLitho();
        initRxDebug();
    }

    private void initDagger() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
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

    private void initLitho() {
        SoLoader.init(this, false);
    }

    private void initRxDebug() {
        RxJava2Debug.enableRxJava2AssemblyTracking();
//        RxJava2Debug.enableRxJava2AssemblyTracking(
//                new String[]{getApplicationContext().getPackageName()}
//        );
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
