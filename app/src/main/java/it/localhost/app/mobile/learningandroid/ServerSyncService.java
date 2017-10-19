package it.localhost.app.mobile.learningandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import it.localhost.app.mobile.learningandroid.ui.adapter.ServerSyncAdapter;

/**
 *
 */

public class ServerSyncService extends Service {

    private static final String TAG = ServerSyncService.class.getSimpleName();
    private static final Object SYNC_ADAPTER_LOCK = new Object();
    private ServerSyncAdapter mSyncAdapter;

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();

        synchronized (SYNC_ADAPTER_LOCK) {
            if (mSyncAdapter == null) {
                mSyncAdapter = new ServerSyncAdapter(this, true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");
        return mSyncAdapter.getSyncAdapterBinder();
    }
}
