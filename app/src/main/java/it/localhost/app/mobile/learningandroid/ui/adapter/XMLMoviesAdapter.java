package it.localhost.app.mobile.learningandroid.ui.adapter;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Movie;
import it.localhost.app.mobile.learningandroid.ui.activity.XMLBindingActivity;

/**
 *
 */

public class XMLMoviesAdapter extends BaseAdapter {

    private static final String TAG = XMLBindingActivity.class.getSimpleName();
    private List<Movie> mCollection;
    private LayoutInflater mInflater;
    private Context mContext;

    public XMLMoviesAdapter(Context ctx) {
        this.mContext = ctx;
        mCollection = Collections.emptyList();
        mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @param collection List<Movie>
     */
    public void updateCollection(List<Movie> collection) {
        this.mCollection = collection;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public Object getItem(int i) {
        return mCollection.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = mCollection.get(position);

        if (movie == null) {
            return null;
        }

        ViewHolderMovies viewHolderMovies;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_xmlbinding_movie, parent, false);
            viewHolderMovies = new ViewHolderMovies(convertView);
            convertView.setTag(viewHolderMovies);
        } else {
            viewHolderMovies = (ViewHolderMovies) convertView.getTag();
        }

        viewHolderMovies.mTvActors.setText(movie.getActors());
        viewHolderMovies.mTvCountry.setText(movie.getCountry());
        viewHolderMovies.mTvDirector.setText(movie.getDirector());
        viewHolderMovies.mTvId.setText(movie.getId());
        viewHolderMovies.mTvRating.setText(String.format(Locale.ITALY, "%.02f", movie.getRating()));
        //String.format(Locale.ITALY, "%.02f", movie.getVotes())
        viewHolderMovies.mTvVotes.setText(movie.getVotes());
        viewHolderMovies.mTvPlot.setText(movie.getPlot());
        viewHolderMovies.mTvReleased.setText(movie.getReleased());
        viewHolderMovies.mTvRuntime.setText(movie.getRuntime());
        Glide.with(mContext).load(movie.getPoster()).into(viewHolderMovies.mIvPoster);

        return convertView;
    }

    /**
     * ViewHolder per Movies
     */
    protected class ViewHolderMovies {

        @BindView(R.id.tvActors)
        TextView mTvActors;
        @BindView(R.id.tvCountry)
        TextView mTvCountry;
        @BindView(R.id.tvDirector)
        TextView mTvDirector;
        @BindView(R.id.tvId)
        TextView mTvId;
        @BindView(R.id.tvRating)
        TextView mTvRating;
        @BindView(R.id.tvVotes)
        TextView mTvVotes;
        @BindView(R.id.tvPlot)
        TextView mTvPlot;
        @BindView(R.id.tvReleased)
        TextView mTvReleased;
        @BindView(R.id.tvRuntime)
        TextView mTvRuntime;
        @BindView(R.id.ivPoster)
        ImageView mIvPoster;

        protected ViewHolderMovies(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
