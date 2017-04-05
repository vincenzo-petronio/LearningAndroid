package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Todo;

/**
 * Adapter
 */
public class DifferentRowAdapter extends BaseAdapter {

    private static final String TAG = DifferentRowAdapter.class.getSimpleName();
    private List<Todo> mCollection = Collections.emptyList();
    private static final int TYPE_COMPLETED = 0;
    private static final int TYPE_NOTCOMPLETED = 1;
    private LayoutInflater mInflater;
    private Context mContext;
    private boolean changed;

    /**
     *
     */
    public DifferentRowAdapter(Context ctx) {
        this.mContext = ctx;
        mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @param collection List<Todo>
     */
    public void updateCollection(List<Todo> collection) {
        this.mCollection = collection;
        notifyDataSetChanged();
    }

    public void changeCollection(boolean change) {
        this.changed = change;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.v(TAG, "getCount");
        return mCollection.size();
    }

    @Override
    public Object getItem(int i) {
        Log.v(TAG, "getItem");
        return mCollection.get(i);
    }

    @Override
    public long getItemId(int i) {
        Log.v(TAG, "getItemId");
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.v(TAG, "getView");
        ViewHolderCompleted viewHolderCompleted = null;
        ViewHolderNotCompleted viewHolderNotCompleted = null;
        int type = getItemViewType(position);

        if (convertView == null) {
            if (type == 0) {
                convertView = mInflater.inflate(R.layout.row_differentrow_completed, parent, false);
                viewHolderCompleted = new ViewHolderCompleted(convertView);
                convertView.setTag(viewHolderCompleted);
            } else {
                convertView = mInflater.inflate(R.layout.row_differentrow_notcompleted, parent, false);
                viewHolderNotCompleted = new ViewHolderNotCompleted(convertView);
                convertView.setTag(viewHolderNotCompleted);
            }
        } else {
            if (type == 0) {
                viewHolderCompleted = (ViewHolderCompleted) convertView.getTag();
            } else {
                viewHolderNotCompleted = (ViewHolderNotCompleted) convertView.getTag();
            }
        }

        if (type == 0) {
            viewHolderCompleted.tvId.setText(String.format(Locale.ITALY, "%d", mCollection.get(position).getId()));
            viewHolderCompleted.tvTitle.setText(mCollection.get(position).getTitle().toUpperCase(Locale.ITALY));
        } else {
            viewHolderNotCompleted.tvId.setText(String.format(Locale.ITALY, "%d", mCollection.get(position).getId()));
            viewHolderNotCompleted.tvTitle.setText(mCollection.get(position).getTitle().toUpperCase(Locale.ITALY));
            viewHolderNotCompleted.rb.setChecked(!changed);
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
//        Log.v(TAG, "getItemViewType");
        return mCollection.get(position).isCompleted() ? TYPE_COMPLETED : TYPE_NOTCOMPLETED;
    }

    @Override
    public int getViewTypeCount() {
        Log.v(TAG, "getViewTypeCount");
        // ho solo 2 tipi di righe
        return 2;
    }

    static class ViewHolderCompleted {
        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        ViewHolderCompleted(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderNotCompleted {
        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.rb)
        RadioButton rb;

        ViewHolderNotCompleted(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
