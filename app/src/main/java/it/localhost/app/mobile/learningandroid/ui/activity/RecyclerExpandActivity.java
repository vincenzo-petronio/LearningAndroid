package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Day;
import it.localhost.app.mobile.learningandroid.data.model.Hour;
import it.localhost.app.mobile.learningandroid.ui.adapter.ExpandableAdapter;

/**
 *
 */
public class RecyclerExpandActivity extends BaseActivity {

    private static final String TAG = RecyclerExpandActivity.class.getSimpleName();
    private ExpandableAdapter mAdapter;
    private List<Day> mDayList;
    private List<Hour> mHourList;

    @BindView(R.id.rvItems)
    protected RecyclerView mRvItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        // INIT
        mDayList = new ArrayList<>();

        for (int j = 0; j < 10; j++) {
            mHourList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Hour hh = new Hour("10.".concat("" + i), new Random().nextBoolean());
                mHourList.add(hh);
            }
            mDayList.add(new Day("Giorno ".concat("" + j), mHourList));
        }


        // RECYCLER
        mRvItems.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ExpandableAdapter(mDayList);
        mRvItems.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
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
}
