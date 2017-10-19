package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

/**
 * SyncAdapter: encapsulate my data transfer code
 */

public class ServerSyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String TAG = ServerSyncAdapter.class.getSimpleName();
    private ContentResolver mContentResolver;


    public ServerSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        mContentResolver = context.getContentResolver();
    }

    public ServerSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        mContentResolver = context.getContentResolver();
    }

    /**
     * Metodo invocato dal framework per effettuare la sincronizzazione dei dati in un thread in
     * background. Per invocare il metodo il framework utilizza il Service.
     */
    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.v(TAG, "onPerformSync");
    }
}
