package it.localhost.app.mobile.learningandroid.ui.adapter;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Photo;


public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private List<Photo> photos;
    private Context mContext;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvTitle;
        private ImageView ivPhoto;

        ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);

            // https://guides.codepath.com/android/using-the-recyclerview#simple-click-handler-within-viewholder
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Photo photo = photos.get(position);

            Toast.makeText(mContext, Integer.toString(photo.getId()), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param photos List<Photo>
     * @param context Context
     */
    public PhotosAdapter(List<Photo> photos, Context context) {
        this.photos = photos;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View photoView = inflater.inflate(R.layout.activity_recycler_photo_row, parent, false);

        // Return a new holder instance
        return new ViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo photo = photos.get(position);

        //GUARD-CLAUSE
        if (photo == null) {
            return;
        }

        holder.tvTitle.setText(photo.getTitle());

        // FIXME Picasso bug! https://github.com/square/picasso/issues/463
//        Picasso.with(context)
//                //.load(Uri.parse(photo.getThumbnailUrl()))
//                .load(photo.getThumbnailUrl())
//                .error(R.drawable.information)
//                //.tag(context)
//                //.placeholder(R.drawable.information)
//                //.resize(120, 60)
//                .into(holder.ivPhoto);

        Glide.with(mContext).load(photo.getThumbnailUrl()).into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }
}
