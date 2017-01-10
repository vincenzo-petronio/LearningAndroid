package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private static final int WIP = 1;
    private static final int DONE = 2;
    private List<Todo> todos;
    private Context context;

    /**
     * Attaching Click Handlers using Listeners
     */
    // https://guides.codepath.com/android/using-the-recyclerview#attaching-click-handlers-using-listeners
    private static OnItemClickListener sOnItemClickListener;

    /**
     * Callback OnItemClickListener
     */
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    /**
     * @param listener OnItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        sOnItemClickListener = listener;
    }

    /**
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);

            // listener unico per entrambi i ViewHolder
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (sOnItemClickListener != null) {
                        // chiama la VIEW
                        sOnItemClickListener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * ViewHolder for WIP CardView
     */
    static class WipViewHolder extends ViewHolder {
        private TextView tvTitle;
        private EditText etNote;

        private WipViewHolder(View itemView) {
            super(itemView);

            this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            this.etNote = (EditText) itemView.findViewById(R.id.etNote);
        }
    }

    /**
     * ViewHolder for Done CardView
     */
    static class DoneViewHolder extends ViewHolder {
        private TextView tvTitle;

        private DoneViewHolder(View itemView) {
            super(itemView);

            this.tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    /**
     * @param todos   List<Todo>
     * @param context Context
     */
    public TodosAdapter(List<Todo> todos, Context context) {
        this.todos = todos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView;

        if (viewType == WIP) {
            userView = inflater.inflate(R.layout.activity_recycler_card_wip, parent, false);
            return new WipViewHolder(userView);
        } else {
            userView = inflater.inflate(R.layout.activity_recycler_card_done, parent, false);
            return new DoneViewHolder(userView);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Todo todo = todos.get(position);

        //GUARD-CLAUSE
        if (todo == null) {
            return;
        }

        if (holder.getItemViewType() == WIP) {
            ((WipViewHolder) holder).tvTitle.setText(todo.getTitle());
            ((WipViewHolder) holder).etNote.setText("type here...");
        } else {
            ((DoneViewHolder) holder).tvTitle.setText(todo.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return todos != null ? todos.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        // here logic to choose the view type!
        return todos.get(position).isCompleted() ? DONE : WIP;
    }
}
