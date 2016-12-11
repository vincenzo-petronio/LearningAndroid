package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.ApiJsonPlaceholderEndpoint;
import it.localhost.app.mobile.learningandroid.data.ServiceGenerator;
import it.localhost.app.mobile.learningandroid.data.model.Comment;
import it.localhost.app.mobile.learningandroid.ui.adapter.CommentsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class RetrofitActivity extends BaseActivity {

    private static final String TAG = RetrofitActivity.class.getSimpleName();
    private CommentsAdapter mAdapter;
    private List<Comment> mCommentList;

    @BindView(R.id.lvItems)
    protected ListView mLvItems;
    @BindView(R.id.pb)
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initUI();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_retrofit;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @OnClick({R.id.btCase1})
    protected void onBtCaseClickListener(View view) {
        switch (view.getId()) {
            case R.id.btCase1:

                break;
        }
    }

    private void initUI() {
        mCommentList = Collections.emptyList();
        mAdapter = new CommentsAdapter(this);
        mLvItems.setAdapter(mAdapter);
    }

    private void loadData() {
        showProgress(true);

        ApiJsonPlaceholderEndpoint client = ServiceGenerator.createService(ApiJsonPlaceholderEndpoint.class);

        // execute() sul Thread UI va in Exception, è da utilizzare solo in un Background Service
        // o Thread secondario e viene eseguito in sincrono
        Call<List<Comment>> call = client.getComments("1");
        try {
            mCommentList = call.execute().body();
            mAdapter.updateCollection(mCommentList);
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }


        // enqueue viene eseguito in un thread secondario asincrono.
        // clone() serve per creare una copia di call, che altrimenti non può essere eseguito più
        // di una volta.
        try {
            call.clone().enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    mCommentList = response.body();
                    mAdapter.updateCollection(mCommentList);
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
                    Log.e(TAG, "onFailure", t);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }

        showProgress(false);
    }

    private void showProgress(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
