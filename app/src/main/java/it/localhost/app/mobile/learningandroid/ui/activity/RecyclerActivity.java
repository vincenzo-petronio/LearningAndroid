package it.localhost.app.mobile.learningandroid.ui.activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.User;
import it.localhost.app.mobile.learningandroid.ui.adapter.DividerItemDecoration;
import it.localhost.app.mobile.learningandroid.ui.adapter.UsersAdapter;
import it.localhost.app.mobile.learningandroid.util.GsonRequest;
import it.localhost.app.mobile.learningandroid.util.VolleySingleton;

public class RecyclerActivity extends AppCompatActivity {

    private List<User> users;
    private Context myCtx;
    private RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCtx = this;

        // VIEW
        setContentView(R.layout.activity_recycler);
        getSupportActionBar().setTitle(R.string.recyclerview_actionbar_title);
        rvItems = (RecyclerView) findViewById(R.id.rvItems);

        // REST API
        RequestQueue req = VolleySingleton.getInstance(this).getRequestQueue();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        GsonRequest<User[]> gsonReq = new GsonRequest<>(Request.Method.GET,
                "http://jsonplaceholder.typicode.com/users",
                User[].class, params, headers, onSuccLstUsers(), onErrLstUsers());
        req.add(gsonReq);
    }

    private void setView() {
        // create and attach the adapter to the recyclerview
        UsersAdapter adapter = new UsersAdapter(users, myCtx);
        rvItems.setAdapter(adapter);
        // use a built-in layout managers
        rvItems.setLayoutManager(new LinearLayoutManager(myCtx));

        // DECORATIONS
        Drawable drawable = ContextCompat.getDrawable(myCtx, R.drawable.divider_sample);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(drawable, DividerItemDecoration.VERTICAL_LIST);
        rvItems.addItemDecoration(itemDecoration);

        // ANIMATOR
//        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//        rvUsers.setItemAnimator(itemAnimator);
    }

    private Response.Listener<User[]> onSuccLstUsers() {
        return new Response.Listener<User[]>() {
            @Override
            public void onResponse(User[] response) {
                users = Arrays.asList(response);
                Log.d("onResponse", "" + Integer.toString(users.size()));
                setView();
            }
        };
    }

    private Response.ErrorListener onErrLstUsers() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        };
    }
}
