package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.UserStory;
import it.localhost.app.mobile.learningandroid.ui.adapter.RealmAdapter;

/**
 *
 */

public class RealmActivity extends BaseActivity {

    private static final String TAG = RealmActivity.class.getSimpleName();
    private Realm mRealm;
    private RealmAdapter mRealmAdapter;

    @BindView(R.id.et_userstory)
    EditText mEtUserStory;
    @BindView(R.id.et_id)
    EditText mEtId;
    @BindView(R.id.bt_add)
    Button mBtAdd;
    @BindView(R.id.rv_items)
    RecyclerView mRvItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initData();
        initUI();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();

        if (mRealm != null) {
            mRealm.close();
        }
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_realm;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
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
        mRealmAdapter.addItem(userStory);
    }

    private void initData() {
    }

    private void initUI() {
        // REALM
        mRealm = Realm.getDefaultInstance();

        // RECYCLER
        RealmResults<UserStory> userStories = mRealm.where(UserStory.class).findAll();
        mRealmAdapter = new RealmAdapter(userStories, true, mRealm);
        mRvItems.setAdapter(mRealmAdapter);
        mRvItems.setLayoutManager(new LinearLayoutManager(this));
    }
}
