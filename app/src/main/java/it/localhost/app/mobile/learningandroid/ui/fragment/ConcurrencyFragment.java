package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.doist.recyclerviewext.dividers.DividerItemDecoration;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.ApiJsonPlaceholderEndpoint;
import it.localhost.app.mobile.learningandroid.data.ServiceGenerator;
import it.localhost.app.mobile.learningandroid.data.model.User;
import it.localhost.app.mobile.learningandroid.ui.adapter.SimpleUsersAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author vincenzo.petronio on 10/12/2017.
 */

public class ConcurrencyFragment extends BaseFragment {

    private static final String TAG = ConcurrencyFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private SimpleUsersAdapter mUsersAdapter;
    private ApiJsonPlaceholderEndpoint mClient;

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
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        initUI();
        loadData();
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

        mUnbinder.unbind();
    }

    @Override
    public int getIdLayout() {
        return R.layout.fragment_concurrency;
    }

    private void initUI() {
        Log.v(TAG, "initUI");
        mUsersAdapter = new SimpleUsersAdapter();
        mRvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvItems.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRvItems.setAdapter(mUsersAdapter);
    }

    private void showProgress(boolean show) {
        if (show) {
            mLoadingProgressBar.show();
        } else {
            mLoadingProgressBar.hide();
        }
    }

    private void loadData() {
        Log.v(TAG, "loadData");
        showProgress(true);

        mClient = ServiceGenerator.createService(ApiJsonPlaceholderEndpoint.class);
        int[] ids = {2, 4, 6, 8};
        List<User> userList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(ids.length);

        for (int id : ids) {
            Log.v(TAG, "loadData.getUser: " + "" + id);
            mClient.getUser("" + id).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    userList.add(response.body());
                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t);
                }
            });
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean completed = countDownLatch.await(5, TimeUnit.SECONDS);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (completed) {
                                    Log.v(TAG, "loadData.completed");
                                    showProgress(false);
                                    mUsersAdapter.updateCollection(userList);
                                } else {
                                    Log.v(TAG, "loadData.NOTcompleted");
                                    showProgress(false);
                                    Toast.makeText(getActivity(), "ERROR!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (InterruptedException ie) {
                    Log.e(TAG, "InterruptedException", ie.getCause());
                }
            }
        }).start();
    }
}
