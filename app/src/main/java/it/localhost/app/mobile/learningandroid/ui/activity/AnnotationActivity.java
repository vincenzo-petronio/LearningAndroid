package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import it.localhost.app.mobile.learningandroid.R;

public class AnnotationActivity extends BaseActivity {

    private static final String TAG = AnnotationActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        initUI();
    }


    // EXTENDS

    @Override
    public int getIdLayout() {
        return R.layout.activity_annotation;
    }

    @Override
    public int getIdToolbar() {
        return R.id.toolbar;
    }


    // METHODS

    private void initUI() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    protected void OnFabClick() {
        // TODO new fragment
    }

}
