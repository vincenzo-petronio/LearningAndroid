package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Post;

/**
 * @author vincenzo.petronio on 06/06/2018.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private static final String TAG = PostsAdapter.class.getSimpleName();
    private List<Post> mCollection;

    public PostsAdapter() {
        Log.v(TAG, "PostsAdapter");
        this.mCollection = Collections.emptyList();
    }

    /**
     * @param collection List<Post>
     */
    public void updateCollection(@NonNull List<Post> collection) {
        Log.v(TAG, "updateCollection");
        this.mCollection = collection;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View userView = inflater.inflate(R.layout.row_post, parent, false);

        // Return a new holder instance
        return new PostsAdapter.ViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {

        Post post = mCollection.get(position);

        //GUARD-CLAUSE
        if (post == null) {
            return;
        }

        holder.tvId.setText(String.format(Locale.ITALY, "%1$d", post.getId()));
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return mCollection != null ? mCollection.size() : 0;
    }

    /**
     * VH
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId, tvTitle, tvBody;

        ViewHolder(View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }
}
