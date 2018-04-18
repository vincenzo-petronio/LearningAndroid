package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.fragment.BaseFragment;
import it.localhost.app.mobile.learningandroid.ui.fragment.ProtoBufMainFragment;

/**
 * @author vincenzo.petronio on 13/04/2018.
 */
public class ProtoBufActivity extends BaseActivity {

    private static final String TAG = ProtoBufActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        super.onBackPressed();
    }

    private void initUI() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new ProtoBufMainFragment()).commit();
    }

    /**
     * @param toFragment BaseFragment
     */
    public void navigateTo(BaseFragment toFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, toFragment).addToBackStack(TAG).commit();
    }
}
