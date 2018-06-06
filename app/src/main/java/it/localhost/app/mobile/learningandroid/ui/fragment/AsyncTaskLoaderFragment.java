package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.doist.recyclerviewext.dividers.DividerItemDecoration;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.ApiJsonPlaceholderEndpoint;
import it.localhost.app.mobile.learningandroid.data.ServiceGenerator;
import it.localhost.app.mobile.learningandroid.data.model.Post;
import it.localhost.app.mobile.learningandroid.ui.adapter.PostsAdapter;
import it.localhost.app.mobile.learningandroid.util.Constants;
import retrofit2.Call;

/**
 * @author vincenzo.petronio on 05/06/2018.
 */
public class AsyncTaskLoaderFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<Post>> {

    private static final String TAG = AsyncTaskLoaderFragment.class.getSimpleName();
    private static final int ID_LOADER_USERS = 20;
    private static final String EXTRA_KEY_USERID = "EXTRA_KEY_USERID";
    private PostsAdapter mPostAdapter;

    @BindView(R.id.rv_items)
    RecyclerView mRvItems;
    @BindView(R.id.progress)
    ContentLoadingProgressBar mLoadingProgressBar;

    @Override
    public void onAttach(Context context) {
        Log.v(TAG, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        initUI();

        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_KEY_USERID, 1);
        getLoaderManager().initLoader(ID_LOADER_USERS, bundle, this);
    }

    @Override
    public void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_asynctaskloader;
    }

    @OnClick(R.id.btn_change)
    void onBtnChangeClickListener() {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_KEY_USERID, new Random().nextInt(5) + 1);
        // restartLoader segue lo stesso flusso dell'initLoader se l'ID non esiste, altrimenti
        // se è un loader già istanziato riavvia quel loader eseguendo il replace dei dati, quindi aggira
        // la "cache" del metodo initLoader.
        getLoaderManager().restartLoader(ID_LOADER_USERS, bundle, this);
    }

    @NonNull
    @Override
    public Loader<List<Post>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v(TAG, "onCreateLoader");
        showProgress(true);

        return new AsyncTaskLoaderTask(getActivity(), args);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Post>> loader, List<Post> data) {
        Log.v(TAG, "onLoadFinished");
        showProgress(false);

        if (data == null) {
            return;
        }

        mPostAdapter.updateCollection(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Post>> loader) {
        Log.v(TAG, "onLoaderReset");
        showProgress(false);
    }

    private void initUI() {
        Log.v(TAG, "initUI");
        mPostAdapter = new PostsAdapter();
        mRvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvItems.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRvItems.setAdapter(mPostAdapter);
    }

    private void showProgress(boolean show) {
        if (show) {
            mLoadingProgressBar.show();
        } else {
            mLoadingProgressBar.hide();
        }
    }


    private static class AsyncTaskLoaderTask extends AsyncTaskLoader<List<Post>> {

        static final String TAG = AsyncTaskLoaderFragment.class.getSimpleName() + "." + AsyncTaskLoaderTask.class.getSimpleName();
        private ApiJsonPlaceholderEndpoint mClient;
        private List<Post> mPostsCached;
        private int mUserId;

        AsyncTaskLoaderTask(Context context, Bundle bundle) {
            super(context);

            // BUNDLE
            mUserId = bundle == null ? 0 : bundle.getInt(EXTRA_KEY_USERID, 0);
        }

        @Override
        protected void onStartLoading() {
            Log.v(TAG, "onStartLoading");
            super.onStartLoading();

            if (mPostsCached != null) {
                // Qui viene saltato il loadInBackground
                deliverResult(mPostsCached);
            } else {
                forceLoad();
            }
        }

        @Override
        public List<Post> loadInBackground() {
            Log.v(TAG, "loadInBackground");

            if (mClient == null) {
                Log.i(TAG, "new instance of JsonPlaceholderClient!");
                mClient = new ServiceGenerator
                        .Builder(Constants.API_JSONPLACEHOLDER_URL_BASE)
                        .connectionTimeout(2)
                        .build()
                        .createService(ApiJsonPlaceholderEndpoint.class);
            }

            Call<List<Post>> call = mClient.getPosts(String.valueOf(mUserId));
            try {
                // synchronous call
                mPostsCached = call.execute().body();
            } catch (IOException ioe) {
                mPostsCached = null;
                Log.e(TAG, "IOException", ioe);
            }

            return mPostsCached;
        }

        @Override
        public void deliverResult(@Nullable List<Post> data) {
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
        public void onCanceled(List<Post> data) {
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
