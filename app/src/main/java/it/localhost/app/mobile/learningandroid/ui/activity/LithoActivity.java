package it.localhost.app.mobile.learningandroid.ui.activity;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.Text;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindString;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 *
 */

public class LithoActivity extends AppCompatActivity {

    private static final String TAG = LithoActivity.class.getSimpleName();

    @BindString(R.string.litho_actionbar_title)
    String mStringTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initUI(new ComponentContext(this));
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

    private void initUI(ComponentContext componentContext) {
        final Component componentTitle = Text.create(componentContext)
                .text(mStringTitle)
                .textSizeDip(50)
                .build();

        LithoView lithoView = LithoView.create(componentContext, componentTitle);
        setContentView(lithoView);
    }
}
