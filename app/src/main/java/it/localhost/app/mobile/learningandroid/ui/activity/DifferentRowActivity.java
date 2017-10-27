package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;
import it.localhost.app.mobile.learningandroid.helper.CustomListIterable;
import it.localhost.app.mobile.learningandroid.ui.adapter.DifferentRowAdapter;

/**
 *
 */

public class DifferentRowActivity extends BaseActivity {

    private static final String TAG = DifferentRowActivity.class.getSimpleName();
    private DifferentRowAdapter mAdapter;
    private CustomListIterable<Todo> todoCustomListIterable;

    @BindView(R.id.lvItems)
    protected ListView mLvItems;
    @BindView(R.id.emptyview)
    FrameLayout mFlEmptyView;

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

    @OnClick({R.id.btModify, R.id.btChange})
    void btModifyOnClick(View view) {
        switch (view.getId()) {
            case R.id.btModify:
                mAdapter.changeCollection(new Random().nextBoolean());
                break;
            case R.id.btChange:
                if (mAdapter.getCount() > 0) {
                    changeData();
                } else {
                    initData();
                }
                break;
        }
    }

    /**
     *
     */
    private void initAdapter() {
        mLvItems.setEmptyView(mFlEmptyView);
//        mAdapter = new DifferentRowAdapter(DifferentRowActivity.this);
//        mLvItems.setAdapter(mAdapter);
    }

    private void initData() {
//        final List<Todo> todoList = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            Todo todo = new Todo();
//            todo.setUserId(i + 1000);
//            todo.setTitle("TITLE: " + "" + i);
//            todo.setId(i);
//            todo.setCompleted(new Random().nextBoolean());
//            todoList.add(todo);
//        }
//
//        // FAKE latency
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter.updateCollection(todoList);
//            }
//        }, 2000);


        Todo[] todos = new Todo[50];
        for (int i = 0; i < todos.length; i++) {
            Todo todo = new Todo();
            todo.setUserId(i + 1000);
            todo.setTitle("TITLE: " + "" + i);
            todo.setId(i);
            todo.setCompleted(new Random().nextBoolean());
            todos[i] = todo;
        }
        todoCustomListIterable = new CustomListIterable<>(todos);


        // FAKE latency
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter = new DifferentRowAdapter(DifferentRowActivity.this, todoCustomListIterable);
                mLvItems.setAdapter(mAdapter);
            }
        }, 2000);
    }

    private void changeData() {
//        mAdapter.updateCollection();
        for (Todo t : todoCustomListIterable) {
            t.setTitle("new title after iterator");
        }
        mAdapter = new DifferentRowAdapter(DifferentRowActivity.this, todoCustomListIterable);
        mLvItems.setAdapter(mAdapter);
    }
}
