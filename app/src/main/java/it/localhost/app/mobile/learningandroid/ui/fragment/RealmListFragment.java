package it.localhost.app.mobile.learningandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Task;
import it.localhost.app.mobile.learningandroid.data.model.UserStory;
import it.localhost.app.mobile.learningandroid.ui.activity.RealmActivity;
import it.localhost.app.mobile.learningandroid.ui.adapter.RealmAdapter;

/**
 * @author vincenzo.petronio on 31/10/2017.
 */

public class RealmListFragment extends BaseFragment {

    private static final String TAG = RealmListFragment.class.getSimpleName();
    private Unbinder mUnbinder;
    private RealmAdapter mRealmAdapter;
    private RealmResults<UserStory> mUserStories;

    @BindView(R.id.et_userstory)
    EditText mEtUserStory;
    @BindView(R.id.et_id)
    EditText mEtId;
    @BindView(R.id.bt_add)
    Button mBtAdd;
    @BindView(R.id.tv_results)
    TextView mTvResults;
    @BindView(R.id.rv_items)
    RecyclerView mRvItems;

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
        initData();
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
        return R.layout.fragment_realmlist;
    }

    @OnClick(R.id.bt_add)
    void onBtAddClickListener() {
        if (TextUtils.isEmpty(mEtUserStory.getText()) || TextUtils.isEmpty(mEtId.getText())) {
            return;
        }

        UserStory userStory = new UserStory();
        userStory.setCompleted(false);
        userStory.setName(mEtUserStory.getText().toString());
        try {
            userStory.setId(Integer.parseInt(mEtId.getText().toString()));
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            return;
        }

        RealmList<Task> taskRealmList = new RealmList<>();
        for (int i = 0; i < 4; i++) {
            Task task = new Task();
            task.setId(new Random().nextInt());
            task.setNeeded(new Random().nextBoolean());
            task.setTimestamp(System.currentTimeMillis() / 1000);
            task.setTitle("Task_" + "" + i);
            taskRealmList.add(task);
        }
        userStory.setTaskRealmCollection(taskRealmList);
        mRealmAdapter.addItem(userStory);
    }

    private void initData() {
        try (Realm mRealm = Realm.getDefaultInstance()) {
            mUserStories = mRealm.where(UserStory.class).findAll();
            mUserStories.addChangeListener(new RealmChangeListener<RealmResults<UserStory>>() {

                @Override
                public void onChange(RealmResults<UserStory> userStories) {
                    setTvRisultati(userStories == null ? 0 : userStories.size());
                }
            });
            mRealmAdapter.updateData(mUserStories);
        }
    }

    private void initUI() {
        mRvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRealmAdapter = new RealmAdapter(null, true);
        mRealmAdapter.setIAdapterCallback(mIAdapterCallback);
        mRvItems.setAdapter(mRealmAdapter);
        setTvRisultati(mUserStories == null ? 0 : mUserStories.size());
    }

    private void setTvRisultati(int size) {
        mTvResults.setText(String.format(Locale.ITALY, getString(R.string.realm_tvresults),
                getResources().getQuantityString(R.plurals.risultati, size, size)));
    }

    private RealmAdapter.IAdapterCallback mIAdapterCallback = new RealmAdapter.IAdapterCallback() {

        @Override
        public void onItemLongClicked(UserStory userStory) {
            Log.v(TAG, "onItemLongClicked");
            if (userStory == null || getActivity() == null) {
                return;
            }

            ((RealmActivity) getActivity()).navToDetails(userStory);
        }

        @Override
        public void onCollectionSizeChanged(int size) {
            Log.i(TAG, "onCollectionSizeChanged");
            setTvRisultati(size);
        }
    };


    public RealmAdapter getRealmAdapter() {
        return mRealmAdapter;
    }
}
