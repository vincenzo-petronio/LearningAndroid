package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 *
 */

public class ReflectionActivity extends BaseActivity {

    private static final String TAG = ReflectionActivity.class.getSimpleName();
    private List<String> stringsItems;

    @BindView(R.id.lv_items)
    ListView mLvItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initData();
        initUI();
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

    @Override
    public int getIdLayout() {
        return R.layout.activity_reflection;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    private void initUI() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                stringsItems);
        mLvItems.setAdapter(arrayAdapter);
    }

    private void initData() {
        stringsItems = new ArrayList<>();

        String sClasseName = "it.localhost.app.mobile.learningandroid.ui.activity.MainActivity";
        try {
//            Class classRunTime = App.class;
            Class classComTime = Class.forName(sClasseName);

            stringsItems.add("getSimpleName: " + classComTime.getSimpleName());
            stringsItems.add("getModifiers: " + Integer.toString(classComTime.getModifiers()));
            stringsItems.add("getSuperclass: " + classComTime.getSuperclass().getSimpleName());

            Method[] methods = classComTime.getMethods();
            for (Method m : methods) {
                if (m.getDeclaringClass().equals(classComTime)) {
                    stringsItems.add("getMethod: " + m.getName());
                }
            }

//            Field[] fields = classComTime.getDeclaredFields();
//            for (Field f : fields) {
//                if (f.getName().equalsIgnoreCase("mStringsItems")) {
//                }
//            }
        } catch (ClassNotFoundException cnfe) {
            Log.e(TAG, "ClassNotFoundException", cnfe);
        }
//        catch (NoSuchFieldException nsfe) {
//            Log.e(TAG, "NoSuchFieldException", nsfe);
//        }
    }

    private void addMethods() {

    }
}
