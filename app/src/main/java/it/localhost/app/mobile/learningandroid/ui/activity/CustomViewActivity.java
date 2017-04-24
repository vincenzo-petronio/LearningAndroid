package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.weathercard.WeatherCardView;

/**
 *
 */

public class CustomViewActivity extends BaseActivity {

    private static final String TAG = CustomViewActivity.class.getSimpleName();

    @BindView(R.id.wcv)
    WeatherCardView mWeatherCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
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
        return R.layout.activity_customview;
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
