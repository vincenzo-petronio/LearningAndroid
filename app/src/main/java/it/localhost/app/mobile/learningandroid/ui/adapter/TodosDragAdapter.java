package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;
import it.localhost.app.mobile.learningandroid.helper.ItemTouchHelperAdapter;
import it.localhost.app.mobile.learningandroid.helper.ItemTouchHelperViewHolder;
import it.localhost.app.mobile.learningandroid.helper.OnStartDragListener;

/**
 * Adapter per CardView con supporto al Drag & Swipe
 */
public class TodosDragAdapter extends RecyclerView.Adapter<TodosDragAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private static final String TAG = TodosDragAdapter.class.getSimpleName();
    private static final int WIP = 1, DONE = 2;
    private final OnStartDragListener mDragStartListener;
    private Context ctx;
    private List<Todo> mTodos;

    /**
     * Costruttore
     *
     * @param ctx Context
     * @deprecated
     */
    public TodosDragAdapter(Context ctx) {
        this.ctx = ctx;
        this.mTodos = Collections.emptyList();
        this.mDragStartListener = null;
    }

    /**
     * Costruttore
     *
     * @param ctx                 Context
     * @param onStartDragListener OnStartDragListener
     */
    public TodosDragAdapter(Context ctx, OnStartDragListener onStartDragListener) {
        this.ctx = ctx;
        this.mDragStartListener = onStartDragListener;
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

    /**
     * Effettua il clear della collection
     */
    public void deleteCollection() {
        Log.v(TAG, "deleteCollection");
        mTodos.clear();
    }

    /**
     * Rimuove un elemento dalla collection nella posizione indicata.
     *
     * @param position int
     */
    public void deleteItem(int position) {
        mTodos.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Aggiunge un elemento alla collection nella posizione indicata
     *
     * @param item     Todo
     * @param position int index 0-based
     */
    public void addItem(Todo item, int position) {
        Log.v(TAG, "addItem");
        mTodos.add(position, item);
        notifyItemInserted(position);
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
    public void onBindViewHolder(final TodosDragAdapter.ItemViewHolder holder, int position) {
        Todo todo = mTodos.get(position);

        //GUARD-CLAUSE
        if (todo == null) {
            Log.e(TAG, "onBindViewHolder item NULL!");
            return;
        }

        holder.tvTitle.setText(todo.getTitle());
        holder.tvId.setText(String.format(Locale.ITALY, "%1$d", todo.getId()));

        // OnTouchListener per catturare il drag
        holder.ivDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) ==
                        MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
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
    public boolean onItemMove(int fromPosition, int toPosition) {
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(mTodos, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(mTodos, i, i - 1);
//            }
//        }

        Collections.swap(mTodos, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mTodos.remove(position);
        notifyItemRemoved(position);
    }


    // INNER-CLASS

    public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        @BindView(R.id.cvRoot)
        CardView cvRoot;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.ivDrag)
        ImageView ivDrag;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        // CALLBACK ItemTouchHelperViewHolder

        @Override
        public void onItemSelected() {
            cvRoot.setCardBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            cvRoot.setCardBackgroundColor(Color.WHITE);
        }
    }
}
