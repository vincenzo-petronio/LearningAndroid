package it.localhost.app.mobile.learningandroid.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.ApiJsonPlaceholderEndpoint;
import it.localhost.app.mobile.learningandroid.data.ServiceGenerator;
import it.localhost.app.mobile.learningandroid.data.model.User;
import it.localhost.app.mobile.learningandroid.util.Constants;
import retrofit2.Call;

/**
 * @author vincenzo.petronio on 05/06/2018.
 */
public class AsyncTaskLoaderActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<User> {

    // ****** WARNING ******
    // I Loader seguono il ciclo di vita dell'Activity/Fragment, quindi vengono distrutti con essi.
    // NON utilizzare i Loader se è necessario completare un task a prescindere dall'Activity/Fragment!

    private static final String TAG = AsyncTaskLoaderActivity.class.getSimpleName();
    private static final int ID_LOADER_USER = 10;

    @BindView(R.id.tv_top)
    TextView mTvUser;
    @BindView(R.id.loading)
    ContentLoadingProgressBar mLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // INIT
        ButterKnife.bind(this);
        getSupportLoaderManager().initLoader(ID_LOADER_USER, null, this);
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_asynctaskloader;
    }

    @Override
    public int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @NonNull
    @Override
    public Loader<User> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v(TAG, "onCreateLoader");
        mLoading.show();
        return new AsyncTaskLoaderTask(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<User> loader, User data) {
        Log.v(TAG, "onLoadFinished");
        mLoading.hide();

        if (data != null) {
            mTvUser.setText(String.format(Locale.ITALY,
                    "%1$s - %2$s - %3$s",
                    data.getId(),
                    data.getName(),
                    data.getUsername()));
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<User> loader) {
        mLoading.hide();
        Log.v(TAG, "onLoaderReset");
    }


    /**
     * Loader Task
     */
    private static class AsyncTaskLoaderTask extends AsyncTaskLoader<User> {

        static final String TAG = AsyncTaskLoaderTask.class.getSimpleName();
        private ApiJsonPlaceholderEndpoint mClient;
        private User userCached;

        AsyncTaskLoaderTask(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            Log.v(TAG, "onStartLoading");
            super.onStartLoading();

            if (userCached != null) {
                // Qui viene saltato il loadInBackground
                deliverResult(userCached);
            } else {
                forceLoad();
            }
        }

        @Override
        public User loadInBackground() {
            Log.v(TAG, "loadInBackground");

            if (mClient == null) {
                Log.i(TAG, "new instance of JsonPlaceholderClient!");
                mClient = new ServiceGenerator
                        .Builder(Constants.API_JSONPLACEHOLDER_URL_BASE)
                        .connectionTimeout(2)
                        .build()
                        .createService(ApiJsonPlaceholderEndpoint.class);
            }

            Call<User> call = mClient.getUser("2");
            try {
                // synchronous call
                userCached = call.execute().body();
            } catch (IOException ioe) {
                userCached = null;
                Log.e(TAG, "IOException", ioe);
            }

            return userCached;
        }

        @Override
        public void deliverResult(@Nullable User data) {
            Log.v(TAG, "deliverResult");
            super.deliverResult(data);
        }

        @Override
        protected void onStopLoading() {
            Log.v(TAG, "onStopLoading");
            super.onStopLoading();
        }

        @Override
        protected boolean onCancelLoad() {
            Log.v(TAG, "onCancelLoad");
            return super.onCancelLoad();
        }

        @Override
        public void onCanceled(User data) {
            Log.v(TAG, "onCanceled");
            super.onCanceled(data);
        }

        @Override
        protected void onReset() {
            Log.v(TAG, "onReset");
            super.onReset();
        }
    }
}