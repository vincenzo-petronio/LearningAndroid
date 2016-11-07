package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;
import it.localhost.app.mobile.learningandroid.ui.adapter.DifferentRowAdapter;

/**
 *
 */

public class DifferentRowActivity extends BaseActivity {

    private static final String TAG = DifferentRowActivity.class.getSimpleName();
    private DifferentRowAdapter mAdapter;

    @BindView(R.id.lvItems)
    protected ListView mLvItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initAdapter();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_differentrow;
    }

    @Override
    public int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @OnClick(R.id.btModify)
    void btModifyOnClick() {
        mAdapter.changeCollection(true);
    }

    private void initAdapter() {
        View emptyView = getLayoutInflater().inflate(R.layout.emptyview, null);
        mLvItems.setEmptyView(emptyView); // FIXME
        mAdapter = new DifferentRowAdapter(DifferentRowActivity.this);
        mLvItems.setAdapter(mAdapter);
    }

    private void initData() {
        final List<Todo> todoList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Todo todo = new Todo();
            todo.setUserId(i + 1000);
            todo.setTitle("TITLE: " + "" + i);
            todo.setId(i);
            todo.setCompleted(new Random().nextBoolean());
            todoList.add(todo);
        }

        // FAKE latency
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.updateCollection(todoList);
            }
        }, 3000);
    }
}
