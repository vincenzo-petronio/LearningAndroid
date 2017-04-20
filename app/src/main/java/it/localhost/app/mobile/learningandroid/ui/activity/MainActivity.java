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
    void OnItemClick(int position) {
        Log.d(TAG, "OnItemSelected: " + lvItems.getItemAtPosition(position));

        Intent i;

        switch (position) {
            case 0:
                i = new Intent(MainActivity.this, JsoupActivity.class);
                startActivity(i);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                i = new Intent(MainActivity.this, NestedScrollActivity.class);
                startActivity(i);
                break;
            case 4:
                i = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(MainActivity.this, PercentActivity.class);
                startActivity(i);
                break;
            case 6:
                i = new Intent(MainActivity.this, CollapsingActivity.class);
                startActivity(i);
                break;
            case 7:
                // RETROFIT
                i = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(i);
                break;
            case 8:
                i = new Intent(MainActivity.this, AnnotationActivity.class);
                startActivity(i);
                break;
            case 9:
                //REALM
                i = new Intent(MainActivity.this, RealmActivity.class);
                startActivity(i);
                break;
            case 10:
                i = new Intent(MainActivity.this, DifferentRowActivity.class);
                startActivity(i);
                break;
            case 11:
                // REFLECTION
                i = new Intent(MainActivity.this, ReflectionActivity.class);
                startActivity(i);
                break;
            case 12:
                // CUSTOMVIEW
                break;
            case 13:
                // XML BINDING
                i = new Intent(MainActivity.this, XMLBindingActivity.class);
                startActivity(i);
                break;
            case 14:
                // WIRE
                break;
            case 15:
                // CONSTRAINT
                i = new Intent(MainActivity.this, ConstraintActivity.class);
                startActivity(i);
                break;
            case 16:
                // PARCELER
                break;
            case 17:
                // EVENTBUS
                i = new Intent(MainActivity.this, PubSubActivity.class);
                startActivity(i);
                break;
            case 18:
                // LITHO
                i = new Intent(MainActivity.this, LithoActivity.class);
                startActivity(i);
                break;
            //...
            default:
                break;
        }
    }
}
