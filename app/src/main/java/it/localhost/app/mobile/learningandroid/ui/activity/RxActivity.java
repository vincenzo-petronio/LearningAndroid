package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.FrameLayout;

import butterknife.BindView;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.fragment.RxMainFragment;

/**
 *
 */

public class RxActivity extends BaseActivity {

    private static final String TAG = RxActivity.class.getSimpleName();

    @BindView(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new RxMainFragment()).commit();
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
        return R.layout.activity_rx;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    /**
     *
     * @param fragment Fragment
     */
    public void navigateTo(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack("").commit();
    }
}
