package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 *
 */
public class RetrofitActivity extends BaseActivity {

    private static final String TAG = RetrofitActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.framelayout_container;
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
