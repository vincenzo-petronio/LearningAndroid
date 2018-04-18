package it.localhost.app.mobile.learningandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    private void initUi() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(
                mContex,
                android.R.layout.simple_list_item_1,
                mStringsItems);
        lvItems.setAdapter(arrayAdapter);
    }

    @OnItemClick(R.id.lvItems)
    void onItemClick(int position) {
        Log.d(TAG, "OnItemSelected: " + lvItems.getItemAtPosition(position));

        Intent i = null;

        switch (position) {
            case 0:
                i = new Intent(MainActivity.this, JsoupActivity.class);
                break;
            case 1:
                // RX
                i = new Intent(MainActivity.this, RxActivity.class);
                break;
            case 2:
                break;
            case 3:
                i = new Intent(MainActivity.this, NestedScrollActivity.class);
                break;
            case 4:
                i = new Intent(MainActivity.this, RecyclerViewActivity.class);
                break;
            case 5:
                i = new Intent(MainActivity.this, PercentActivity.class);
                break;
            case 6:
                i = new Intent(MainActivity.this, CollapsingActivity.class);
                break;
            case 7:
                // RETROFIT
                i = new Intent(MainActivity.this, RetrofitActivity.class);
                break;
            case 8:
                i = new Intent(MainActivity.this, AnnotationActivity.class);
                break;
            case 9:
                //REALM
                i = new Intent(MainActivity.this, RealmActivity.class);
                break;
            case 10:
                i = new Intent(MainActivity.this, DifferentRowActivity.class);
                break;
            case 11:
                // REFLECTION
                i = new Intent(MainActivity.this, ReflectionActivity.class);
                break;
            case 12:
                // CUSTOMVIEW
                i = new Intent(MainActivity.this, CustomViewActivity.class);
                break;
            case 13:
                // XML BINDING
                i = new Intent(MainActivity.this, XMLBindingActivity.class);
                break;
            case 14:
                // PROTO BUFFER - WIRE
                i = new Intent(MainActivity.this, ProtoBufActivity.class);
                break;
            case 15:
                // CONSTRAINT
                i = new Intent(MainActivity.this, ConstraintActivity.class);
                break;
            case 16:
                // PARCELER
                break;
            case 17:
                // EVENTBUS
                i = new Intent(MainActivity.this, PubSubActivity.class);
                break;
            case 18:
                // LITHO
                i = new Intent(MainActivity.this, LithoActivity.class);
                break;
            case 19:
                // DAGGER
                i = new Intent(MainActivity.this, DaggerActivity.class);
                break;
            case 20:
                i = new Intent(MainActivity.this, ServerSyncActivity.class);
                break;
            case 21:
                // CONCURRENCY
                i = new Intent(MainActivity.this, ConcurrencyActivity.class);
                break;
            case 22:
                // PATTERNS
                i = new Intent(MainActivity.this, PatternsActivity.class);
                break;
            //...
            default:
                break;
        }

        if (i != null) {
            startActivity(i);
        }
    }
}
