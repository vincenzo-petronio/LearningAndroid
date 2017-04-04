package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Task;
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
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.tv_results)
    TextView mTvResults;

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
        return R.id.toolbar;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_realm, menu);

        onCreateOptionsMenuSearch(menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_search:
                return true;
            case R.id.toolbar_sortid:
                mRealmAdapter.sortItems();
                return true;
            case R.id.toolbar_searchneeded:
                mRealmAdapter.searchItem(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    }

    private void initUI() {


        // REALM
        mRealm = Realm.getDefaultInstance();

        // RECYCLER
        RealmResults<UserStory> userStories = mRealm.where(UserStory.class).findAll();
        mRealmAdapter = new RealmAdapter(userStories, true, mRealm);
        mRvItems.setAdapter(mRealmAdapter);
        mRvItems.setLayoutManager(new LinearLayoutManager(this));
        mRealmAdapter.setIAdapterCallback(mIAdapterCallback);

        setTvRisultati(userStories.size());
    }

    RealmAdapter.IAdapterCallback mIAdapterCallback = new RealmAdapter.IAdapterCallback() {
        @Override
        public void onItemLongClicked(RealmList<Task> tasks) {
            if (tasks.isEmpty()) {
                return;
            }

            mLlContainer.removeAllViews();

            for (Task task : tasks) {
                CheckedTextView checkedTextView = new CheckedTextView(RealmActivity.this);
                if (task.isNeeded()) {
                    checkedTextView.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
                } else {
                    checkedTextView.setCheckMarkDrawable(0);
                }
                checkedTextView.setText(
                        Integer.toString(task.getId()) + " " +
                                task.getTitle() + " " +
                                Long.toString(task.getTimestamp()));
                mLlContainer.addView(checkedTextView);
            }
        }

        @Override
        public void onCollectionSizeChanged(int size) {
            setTvRisultati(size);
        }
    };

    private void setTvRisultati(int size) {
        mTvResults.setText(String.format(Locale.ITALY, getString(R.string.realm_tvresults),
                getResources().getQuantityString(R.plurals.risultati, size, size)));
    }

    /**
     * Gestione del menu Search
     *
     * @param menu Menu
     */
    private void onCreateOptionsMenuSearch(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.toolbar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mRealmAdapter.searchItem(newText);
                return false;
            }
        });
    }
}
