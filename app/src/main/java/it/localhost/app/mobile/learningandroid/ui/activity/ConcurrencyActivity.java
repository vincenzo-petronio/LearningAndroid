package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.ui.fragment.ConcurrencyFragment;

/**
 * About Future, FutureTask, ExecutorService, CountDownLatch.
 *
 * @author vincenzo.petronio on 10/12/2017.
 * @since JDK 1.5
 */

public class ConcurrencyActivity extends BaseActivity {

    private static final String TAG = ConcurrencyActivity.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new ConcurrencyFragment()).commit();
    }

    @Override
    public int getIdLayout() {
        return R.layout.layout_container;
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
