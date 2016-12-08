package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 * Exmple for ConstraintLayout
 */
public class ConstraintActivity extends BaseActivity {

    private static final String TAG = ConstraintActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

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
        return R.layout.activity_constraint;
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
