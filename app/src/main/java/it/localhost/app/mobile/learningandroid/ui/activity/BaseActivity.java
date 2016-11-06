package it.localhost.app.mobile.learningandroid.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private Context mContext;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private int mIdLayout, mIdToolbar, mIdFab;


    // EXTENDING

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        initUI();
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.v(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }


    // GETTER & SETTER

    public Context getContext() {
        return mContext;
    }

    public void setContext(@NonNull Context context) {
        mContext = context;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public FloatingActionButton getFab() {
        return mFab;
    }

    /**
     * Restituisce il layout da associare all'Activity. <br>
     *
     * @return int
     * @see <a href="https://en.wikipedia.org/wiki/Template_method_pattern">Template Method
     * Pattern</a>
     */
    @LayoutRes
    public abstract int getIdLayout();

    /**
     * Toolbar
     *
     * @return int
     */
    @IdRes
    public abstract int getIdToolbar();

    /**
     * FloatingActionButton
     *
     * @return int
     */
    @IdRes
    public abstract int getIdFab();

    // METHODS

    private void initUI() {
        mIdLayout = getIdLayout();
        mIdToolbar = getIdToolbar();
        mIdFab = getIdFab();

        setContentView(mIdLayout);
        mToolbar = (Toolbar) findViewById(mIdToolbar);
        mFab = (FloatingActionButton) findViewById(mIdFab);

        setSupportActionBar(mToolbar);
    }
}
