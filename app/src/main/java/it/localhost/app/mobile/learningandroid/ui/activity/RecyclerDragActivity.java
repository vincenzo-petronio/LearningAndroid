package it.localhost.app.mobile.learningandroid.ui.activity;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;
import it.localhost.app.mobile.learningandroid.helper.OnStartDragListener;
import it.localhost.app.mobile.learningandroid.helper.SimpleItemTouchHelperCallback;
import it.localhost.app.mobile.learningandroid.ui.adapter.TodosDragAdapter;
import it.localhost.app.mobile.learningandroid.util.Constants;
import it.localhost.app.mobile.learningandroid.util.DataManager;

public class RecyclerDragActivity extends AppCompatActivity implements OnStartDragListener {

    private static final String TAG = RecyclerDragActivity.class.getSimpleName();
    private Context myCtx;
    private TodosDragAdapter mTodosDragAdapter;
    private List<Todo> mTodos;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.rvItems)
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        myCtx = this;

        initUI();
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    private void initUI() {
        setContentView(R.layout.activity_recycler_drag);
        ButterKnife.bind(this);

        // Preparo l'Adapter per la RecyclerView
        mTodosDragAdapter = new TodosDragAdapter(myCtx, this);
        rvItems.setAdapter(mTodosDragAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(myCtx));

        // Creo la callback per ascoltare gli eventi swipe e move.
        // Invece di usare il wrapper fornito da Android (ItemTouchHelper.SimpleCallback), ne creo
        // uno custom.
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mTodosDragAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rvItems);
    }

    private void initPresenter() {
        // Considero l'Adapter come un Presenter!

        try {
            String jsonFakeData = DataManager.getJSONFromAssets(Constants.PATH_JSON_TODOS, myCtx);

            mTodos = new Gson().fromJson(jsonFakeData, new TypeToken<List<Todo>>() {
            }.getType());
        } catch (IOException ioe) {
            Log.e(TAG, "IOException", ioe);
            // TODO notificare
        } catch (JsonSyntaxException jse) {
            Log.e(TAG, "JsonSyntaxException", jse);
            // TODO notificare
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
            // TODO notificare
        }

        mTodosDragAdapter.updateCollection(mTodos);
    }


    // CALLBACK OnStartDragListener

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
