package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import it.localhost.app.mobile.learningandroid.R;

public class NestedScrollActivity extends AppCompatActivity {

    private static final String TAG = NestedScrollActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // VIEW
        setContentView(R.layout.activity_nestedscroll);

        initUi();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    private void initUi() {
        getSupportActionBar().setTitle(R.string.nestedscroll_actionbar_title);
    }
}
