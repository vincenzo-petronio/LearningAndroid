package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.util.Log;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.fragment.PatternsFragment;

/**
 * Lista dei pattern
 */
public class PatternsActivity extends BaseActivity {

    private static final String TAG = PatternsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        initUI();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.layout_container_toolbar;
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
    public void onBackPressed() {
        Log.v(TAG, "onBackPressed");
        super.onBackPressed();
    }

    private void initUI() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new PatternsFragment()).commit();
    }
}
