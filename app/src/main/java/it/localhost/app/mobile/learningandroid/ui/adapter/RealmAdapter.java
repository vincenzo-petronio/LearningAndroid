package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import it.localhost.app.mobile.learningandroid.data.model.UserStory;

/**
 *
 */

public class RealmAdapter extends RealmRecyclerViewAdapter<UserStory, RealmAdapter.ViewHolder> {

    private OrderedRealmCollection<UserStory> mCollection;

    /**
     * Costruttore
     *
     * @param data       OrderedRealmCollection<UserStory>
     * @param autoUpdate boolean
     */
    public RealmAdapter(@Nullable OrderedRealmCollection<UserStory> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }


    /**
     * @param data OrderedRealmCollection
     */
    public void updateCollection(@NonNull OrderedRealmCollection<UserStory> data) {
        this.mCollection = data;
    }

    @Override
    public RealmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RealmAdapter.ViewHolder holder, int position) {

    }


    /**
     * View Holder
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
