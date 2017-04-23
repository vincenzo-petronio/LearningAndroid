package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 *
 */

public class CustomViewActivity extends AppCompatActivity {

    private static final String TAG = CustomViewActivity.class.getSimpleName();

//    @BindView(R.id.wcv)
//    WeatherCardView mWeatherCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

//        ButterKnife.bind(this);

        setContentView(R.layout.layout_weathercard);
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

//    @Override
//    public int getIdLayout() {
//        return R.layout.activity_customview;
//    }
//
//    @Override
//    public int getIdToolbar() {
//        return 0;
//    }
//
//    @Override
//    public int getIdFab() {
//        return 0;
//    }
}
