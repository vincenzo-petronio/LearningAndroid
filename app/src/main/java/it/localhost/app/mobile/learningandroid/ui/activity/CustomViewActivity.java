package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @OnClick(R.id.bt_change)
    void onBtChangeClickListener() {
        // rand.nextInt((max - min) + 1) + min
        int randomInt = 1 + new Random().nextInt((9 - 1) + 1);
        switch (randomInt) {
            case 1:
                mWeatherCardView.setWeatherCardType(WeatherCardView.CLOUDY);
                break;
            case 2:
                mWeatherCardView.setWeatherCardType(WeatherCardView.HAIL);
                break;
            case 3:
                mWeatherCardView.setWeatherCardType(WeatherCardView.LIGHTNING);
                break;
            case 4:
                mWeatherCardView.setWeatherCardType(WeatherCardView.NIGHT);
                break;
            case 5:
                mWeatherCardView.setWeatherCardType(WeatherCardView.PARTLYCLOUDY);
                break;
            case 6:
                mWeatherCardView.setWeatherCardType(WeatherCardView.POURING);
                break;
            case 7:
                mWeatherCardView.setWeatherCardType(WeatherCardView.RAINY);
                break;
            case 8:
                mWeatherCardView.setWeatherCardType(WeatherCardView.SNOWY);
                break;
            case 9:
                mWeatherCardView.setWeatherCardType(WeatherCardView.WINDY);
                break;
            default:
                mWeatherCardView.setWeatherCardType(WeatherCardView.CLOUDY);
                break;
        }
    }
}
