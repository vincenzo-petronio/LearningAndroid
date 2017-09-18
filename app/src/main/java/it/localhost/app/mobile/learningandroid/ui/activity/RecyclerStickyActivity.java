package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.doist.recyclerviewext.sticky_headers.StickyHeadersLinearLayoutManager;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Address;
import it.localhost.app.mobile.learningandroid.data.model.Company;
import it.localhost.app.mobile.learningandroid.data.model.User;
import it.localhost.app.mobile.learningandroid.ui.adapter.UsersStickyAdapter;

/**
 *
 */

public class RecyclerStickyActivity extends BaseActivity {

    private static final String TAG = RecyclerStickyActivity.class.getSimpleName();
    private List<User> mUserList;

    @BindView(R.id.rvItems)
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
        return R.layout.activity_recycler;
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
        mUserList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            User u = new User();
            if (i == 0) {
                u.setName("A");
            } else if (i == 5) {
                u.setName("B");
            } else if (i == 8) {
                u.setName("T");
            } else if (i == 10) {
                u.setName("S");
            } else {
                u.setName("name " + "" + i);
                u.setAddress(new Address());
                u.setCompany(new Company());
                u.setEmail("email " + "" + i);
                u.setId(i);
                u.setPhone("phone " + "" + i);
                u.setUsername("username " + "" + i);
                u.setWebsite("website " + "" + i);
            }
            mUserList.add(u);
        }
    }

    private void initUI() {
        UsersStickyAdapter adapter = new UsersStickyAdapter(mUserList, this);
        mRvItems.setAdapter(adapter);
        mRvItems.setLayoutManager(new StickyHeadersLinearLayoutManager<UsersStickyAdapter>(getContext()));
    }
}
