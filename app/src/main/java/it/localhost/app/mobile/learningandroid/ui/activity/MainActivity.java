package it.localhost.app.mobile.learningandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import it.localhost.app.mobile.learningandroid.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContex;

    @BindArray(R.array.main_items)
    String[] mStringsItems;
    @BindView(R.id.lvItems)
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        mContex = this;

        initUi();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @OnItemClick(R.id.lvItems)
    void onItemClick(int position) {
        Log.d(TAG, "OnItemSelected: " + lvItems.getItemAtPosition(position));

        Class<? extends AppCompatActivity> activity = null;

        switch (position) {
            case 0:
                activity = JsoupActivity.class;
                break;
            case 1:
                // RX
                activity = RxActivity.class;
                break;
            case 2:
                break;
            case 3:
                activity = NestedScrollActivity.class;
                break;
            case 4:
                activity = RecyclerViewActivity.class;
                break;
            case 5:
                activity = PercentActivity.class;
                break;
            case 6:
                activity = CollapsingActivity.class;
                break;
            case 7:
                // RETROFIT
                activity = RetrofitActivity.class;
                break;
            case 8:
                activity = AnnotationActivity.class;
                break;
            case 9:
                //REALM
                activity = RealmActivity.class;
                break;
            case 10:
                activity = DifferentRowActivity.class;
                break;
            case 11:
                // REFLECTION
                activity = ReflectionActivity.class;
                break;
            case 12:
                // CUSTOMVIEW
                activity = CustomViewActivity.class;
                break;
            case 13:
                // XML BINDING
                activity = XMLBindingActivity.class;
                break;
            case 14:
                // WIRE
                break;
            case 15:
                // CONSTRAINT
                activity = ConstraintActivity.class;
                break;
            case 16:
                // PARCELER
                break;
            case 17:
                // EVENTBUS
                activity = PubSubActivity.class;
                break;
            case 18:
                // LITHO
                activity = LithoActivity.class;
                break;
            case 19:
                // DAGGER
                activity = DaggerActivity.class;
                break;
            case 20:
                activity = ServerSyncActivity.class;
                break;
            case 21:
                // CONCURRENCY
                activity = ConcurrencyActivity.class;
                break;
            case 22:
                // PATTERNS
                activity = PatternsActivity.class;
                break;
            //...
            default:
                break;
        }

        if (activity != null) {
            navigateTo(activity);
        }
    }

    private void initUi() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(
                mContex,
                android.R.layout.simple_list_item_1,
                mStringsItems);
        lvItems.setAdapter(arrayAdapter);
    }

    /**
     * @param activity AppCompatActivity
     */
    private void navigateTo(@NonNull Class<? extends AppCompatActivity> activity) {
        Intent i = new Intent(MainActivity.this, activity);
        startActivity(i);
    }
}
