package it.localhost.app.mobile.learningandroid;

import com.akaita.java.rxjava2debug.RxJava2Debug;
import com.facebook.soloader.SoLoader;
import com.facebook.stetho.Stetho;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.log.RealmLog;
import it.localhost.app.mobile.learningandroid.dagger.components.DaggerAppComponent;
import it.localhost.app.mobile.learningandroid.helper.SharedPrefs;
import it.localhost.app.mobile.learningandroid.util.Constants;

/**
 *
 */
public class App extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static final String TAG = App.class.getSimpleName();
    private static App sInstance = null;
    private SharedPrefs sSharedPrefs;

    @Override
    protected void attachBaseContext(Context base) {
        Log.v(TAG, "attachBaseContext");
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();

        sInstance = this;

        initData();
        initDagger();
        initRealm();
        initLitho();
        initRxDebug();
        initStetho();
    }

    private void initData() {
        Log.v(TAG, "initData");
        sSharedPrefs = SharedPrefs.getInstance();
        Log.i(TAG, getsSharedPrefs().getFirebaseToken(this));

    }

    private void initDagger() {
        Log.v(TAG, "initDagger");
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    private void initRealm() {
        Log.v(TAG, "initRealm");
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
        RealmLog.setLevel(Log.ERROR);
    }

    private void initLitho() {
        Log.v(TAG, "initLitho");
        SoLoader.init(this, false);
    }

    private void initRxDebug() {
        Log.v(TAG, "initRxDebug");
        RxJava2Debug.enableRxJava2AssemblyTracking();
//        RxJava2Debug.enableRxJava2AssemblyTracking(
//                new String[]{getApplicationContext().getPackageName()}
//        );
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    /**
     * @return App
     */
    public static App getInstance() {
        return sInstance;
    }

    public SharedPrefs getsSharedPrefs() {
        return sSharedPrefs;
    }
}
