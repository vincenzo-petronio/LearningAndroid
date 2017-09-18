package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.doist.recyclerviewext.sticky_headers.StickyHeaders;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.User;


public class UsersStickyAdapter extends RecyclerView.Adapter<UsersStickyAdapter.ViewHolder> implements StickyHeaders, StickyHeaders.ViewSetup {

    private static final String TAG = UsersStickyAdapter.class.getSimpleName();
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private List<User> mUsers;
    private Context mContext;
    private final float mStuckHeaderElevation;

    /**
     * ViewHolder generico
     */
    static abstract class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * ViewHolder per l'item con i dati
     */
    private static class ItemViewHolder extends ViewHolder {

        private TextView tvNameSurname, tvEmail, tvPhone, tvWebsite, tvStreet, tvSuite, tvCity, tvZip;

        ItemViewHolder(View itemView) {
            super(itemView);

            tvNameSurname = (TextView) itemView.findViewById(R.id.tvNameSurname);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            tvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
            tvWebsite = (TextView) itemView.findViewById(R.id.tvWebsite);
            tvStreet = (TextView) itemView.findViewById(R.id.tvStreet);
            tvSuite = (TextView) itemView.findViewById(R.id.tvSuite);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvZip = (TextView) itemView.findViewById(R.id.tvZip);
        }
    }

    /**
     * ViewHolder per l'header
     */
    private static class HeaderViewHolder extends ViewHolder {

        private TextView tvTitle;

        HeaderViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(android.R.id.text1);
            tvTitle.setBackgroundColor(Color.YELLOW);
        }
    }

    /**
     * @param users   List<User>
     * @param context Context
     */
    public UsersStickyAdapter(List<User> users, Context context) {
        Log.v(TAG, "UsersAdapter");
        this.mUsers = users;
        this.mContext = context;
        mStuckHeaderElevation = context.getResources().getDimension(R.dimen.card_elevation);
    }

    private Context getContext() {
        return mContext;
    }

    /**
     * Inflate the view and its view holder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case VIEW_TYPE_HEADER:
                View headerView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                return new HeaderViewHolder(headerView);
            case VIEW_TYPE_ITEM:
                View itemView = inflater.inflate(R.layout.activity_recycler_row, parent, false);
                return new ItemViewHolder(itemView);
            default:
                break;

        }
        return null;
    }

    /**
     * Bind data to the view
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);

        //GUARD-CLAUSE
        if (user == null) {
            return;
        }

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
                ((HeaderViewHolder) holder).tvTitle.setText(user.getName());
                break;
            case VIEW_TYPE_ITEM:
                ((ItemViewHolder) holder).tvNameSurname.setText(user.getName());
                ((ItemViewHolder) holder).tvEmail.setText(user.getEmail());
                ((ItemViewHolder) holder).tvPhone.setText(user.getPhone());
                ((ItemViewHolder) holder).tvWebsite.setText(user.getWebsite());
                ((ItemViewHolder) holder).tvStreet.setText(user.getAddress().getStreet());
                ((ItemViewHolder) holder).tvSuite.setText(user.getAddress().getSuite());
                ((ItemViewHolder) holder).tvCity.setText(user.getAddress().getCity());
                ((ItemViewHolder) holder).tvZip.setText(user.getAddress().getZipcode());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        User u = mUsers.get(position);

//        if (u.getName().length() == 1) {
//            return u.getCompany();
//        }

        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        User u = mUsers.get(position);

        // Se ho un char allora è un header
        if (u.getName().length() == 1) {
            return VIEW_TYPE_HEADER;
        }
        if (u.getName().length() > 1) {
            return VIEW_TYPE_ITEM;
        }

        return RecyclerView.INVALID_TYPE;
    }

    @Override
    public boolean isStickyHeader(int position) {
        return getItemViewType(position) == VIEW_TYPE_HEADER;
    }

    @Override
    public void setupStickyHeaderView(View view) {
        ViewCompat.setTranslationZ(view, mStuckHeaderElevation);
//        view.setTranslationZ(stuckHeaderElevation);
    }

    @Override
    public void teardownStickyHeaderView(View view) {
        ViewCompat.setTranslationZ(view, 0f);
//        view.setTranslationZ(0f);
    }

//        private static class StickyHeaderType {
//            private String company;
//
//            public StickyHeaderType(String company) {
//                this.company = company;
//            }
//        }
//
//        private static class ItemType {
//
//        }
}
