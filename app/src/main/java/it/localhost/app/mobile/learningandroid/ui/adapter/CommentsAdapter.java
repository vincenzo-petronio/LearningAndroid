package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Comment;

/**
 *
 */
public class CommentsAdapter extends BaseAdapter {

    private static final String TAG = CommentsAdapter.class.getSimpleName();
    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<Comment> mCommentList;

    public CommentsAdapter(Context ctx) {
        mContext = ctx;
        mCommentList = Collections.emptyList();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @param collection List<Comment>
     */
    public void updateCollection(List<Comment> collection) {
        Log.v(TAG, "updateCollection");

        if (collection == null) {
            throw new IllegalArgumentException("collection NULL!!!");
        }

        mCommentList = collection;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCommentList == null ? 0 : mCommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = mCommentList.get(position);

        if (comment == null) {
            return null;
        }

        ViewHolderComments viewHolderComments;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_xmlbinding_recipe, parent, false);
            viewHolderComments = new ViewHolderComments(convertView);
            convertView.setTag(viewHolderComments);
        } else {
            viewHolderComments = (ViewHolderComments) convertView.getTag();
        }

        viewHolderComments.mTvTitle.setText(comment.getName());
        viewHolderComments.mTvIngredients.setText(comment.getBody());
        viewHolderComments.mTvId.setText(String.format(Locale.ITALY, "PostID:%1$s - ID:%2$s", comment.getPostId(), comment.getId()));

        return convertView;
    }

    /**
     * ViewHolder
     */
    static class ViewHolderComments {

        @BindView(R.id.tvTitle)
        TextView mTvTitle;
        @BindView(R.id.tvIngredients)
        TextView mTvIngredients;
        @BindView(R.id.tvId)
        TextView mTvId;

        ViewHolderComments(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
