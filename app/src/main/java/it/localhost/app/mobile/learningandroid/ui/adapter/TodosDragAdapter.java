package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;
import it.localhost.app.mobile.learningandroid.helper.ItemTouchHelperAdapter;

import static android.media.CamcorderProfile.get;
import static it.localhost.app.mobile.learningandroid.R.id.tvTitle;

/**
 * Adapter per CardView con supporto al Drag & Swipe
 */
public class TodosDragAdapter extends RecyclerView.Adapter<TodosDragAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private static final String TAG = TodosDragAdapter.class.getSimpleName();
    private static final int WIP = 1, DONE = 2;
    private Context ctx;
    private List<Todo> mTodos;

    public TodosDragAdapter(Context ctx) {
        this.ctx = ctx;
        this.mTodos = Collections.emptyList();
    }

    /**
     * Aggiorna la vista con una nuova collezione di dati
     *
     * @param collection List<Todo>
     */
    public void updateCollection(List<Todo> collection) {
        Log.v(TAG, "updateCollection");

        if (collection == null) {
            throw new IllegalArgumentException("collection NULL!!!");
        }

        mTodos = collection;
        notifyDataSetChanged();
    }


    // EXTENDS

    @Override
    public TodosDragAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // TODO inserire qui il codice per differenziare la vista a seconda del viewType

        View view = inflater.inflate(R.layout.activity_recycler_drag_row, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TodosDragAdapter.ItemViewHolder holder, int position) {
        Todo todo = mTodos.get(position);

        //GUARD-CLAUSE
        if (todo == null) {
            return;
        }

        holder.tvTitle.setText(todo.getTitle());
    }

    @Override
    public int getItemCount() {
        return mTodos != null ? mTodos.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        // here logic to choose the view type!
        return mTodos.get(position).isCompleted() ? DONE : WIP;
    }


    // CALLBACK ItemTouchHelperAdapter

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position) {

    }


    // INNER-CLASS

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.ivDrag)
        ImageView ivDrag;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
