package it.localhost.app.mobile.learningandroid.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

public class JsoupActivity extends AppCompatActivity {

    private static final String TAG = JsoupActivity.class.getSimpleName();
    private Context mContex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        //VIEW
        setContentView(R.layout.activity_jsoup);
        mContex = this;

        initUi();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    private void initUi() {
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(R.string.actionbar_title);
    }
}
