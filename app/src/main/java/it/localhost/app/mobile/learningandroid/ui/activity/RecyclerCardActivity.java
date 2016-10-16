package it.localhost.app.mobile.learningandroid.ui.activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;
import it.localhost.app.mobile.learningandroid.ui.adapter.TodosAdapter;
import it.localhost.app.mobile.learningandroid.util.GsonRequest;
import it.localhost.app.mobile.learningandroid.util.VolleySingleton;

public class RecyclerCardActivity extends AppCompatActivity {

    private Context myCtx;
    private RecyclerView rvItems;
    private List<Todo> todos;

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
        //headers.put("Content-Type", "application/json; charset=utf-8");
        GsonRequest<Todo[]> gsonReq = new GsonRequest<>(Request.Method.GET,
                "http://jsonplaceholder.typicode.com/todos",
                Todo[].class, params, headers, onSuccLstTodos(), onErrLstTodos());
        req.add(gsonReq);
    }

    private void setView() {
        TodosAdapter adapter = new TodosAdapter(todos, myCtx);
        adapter.setOnItemClickListener(new TodosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Todo todo = todos.get(position);
                Toast.makeText(RecyclerCardActivity.this, Integer.toString(todo.getId()), Toast.LENGTH_SHORT).show();
            }
        });
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(myCtx));
    }

    private Response.Listener<Todo[]> onSuccLstTodos() {
        return new Response.Listener<Todo[]>() {

            @Override
            public void onResponse(Todo[] response) {
                todos = Arrays.asList(response);
                Log.d("onResponse", "" + Integer.toString(todos.size()));
                setView();
            }
        };
    }

    private Response.ErrorListener onErrLstTodos() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        };
    }
}
