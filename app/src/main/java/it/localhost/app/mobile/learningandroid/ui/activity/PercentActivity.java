package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 * PercentLayout example
 */
public class PercentActivity extends BaseActivity {

    private static final String TAG = PercentActivity.class.getSimpleName();

    @BindView(R.id.vsYellow)
    ViewStubCompat mVsYellow;
    @BindView(R.id.vsRed)
    ViewStubCompat mVsRed;
    @BindView(R.id.vsBlu)
    ViewStubCompat mVsBlu;
    @BindView(R.id.vsGreen)
    ViewStubCompat mVsGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        // VIEWSTUB
        View containerYellow = mVsYellow.inflate();
        View containerRed = mVsRed.inflate();
        View containerBlu = mVsBlu.inflate();
        View containerGreen = mVsGreen.inflate();

        containerYellow.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        containerRed.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        containerBlu.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
        containerGreen.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_percent;
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
