package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.tv_userstory)
    TextView mTvUserStory;
    @BindView(R.id.bt_add)
    Button mBtAdd;
    @BindView(R.id.rv_items)
    RecyclerView mRvItems;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState, persistentState);

        ButterKnife.bind(this);

        initData();
        initUI();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
        mRealm.close();
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

    private void initData() {

    }

    private void initUI() {
        // REALM
        mRealm = Realm.getDefaultInstance();

        // RECYCLER
        RealmResults<UserStory> userStories = mRealm.where(UserStory.class).findAll();
        mRvItems.setAdapter(new RealmAdapter(userStories, true));
    }
}
