package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.User;


public class SimpleUsersAdapter extends RecyclerView.Adapter<SimpleUsersAdapter.ViewHolder> {

    private static final String TAG = SimpleUsersAdapter.class.getSimpleName();
    private List<User> users;

    /**
     * VH
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameSurname, tvEmail, tvPhone, tvWebsite, tvStreet, tvSuite, tvCity, tvZip;
        private ImageView ivInfo, ivFlag;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNameSurname = itemView.findViewById(R.id.tvNameSurname);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvWebsite = itemView.findViewById(R.id.tvWebsite);
            tvStreet = itemView.findViewById(R.id.tvStreet);
            tvSuite = itemView.findViewById(R.id.tvSuite);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvZip = itemView.findViewById(R.id.tvZip);
            ivInfo = itemView.findViewById(R.id.iv_info);
            ivFlag = itemView.findViewById(R.id.iv_flag);
        }
    }

    /**
     *
     */
    public SimpleUsersAdapter() {
//                Collections.emptyList();
        this.users = new ArrayList<>();
    }

    /**
     * @param users List<User>
     */
    public SimpleUsersAdapter(List<User> users) {
        this.users = users;
    }

    /**
     * @param collection List<User>
     */
    public void updateCollection(List<User> collection) {
        this.users = collection;
        notifyDataSetChanged();
    }

    /**
     * Append item to collection
     *
     * @param user User
     */
    public void addToCollection(User user) {
        Log.v(TAG, "addToCollection:" + user.getName());
        this.users.add(user);
        notifyItemChanged(this.users.size());
    }

    /**
     * Inflate the view and its view holder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View userView = inflater.inflate(R.layout.activity_recycler_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    /**
     * Bind data to the view
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user = users.get(position);

        //GUARD-CLAUSE
        if (user == null) {
            return;
        }

        holder.tvNameSurname.setText(user.getName());
        holder.tvEmail.setVisibility(View.GONE);
        holder.tvPhone.setVisibility(View.GONE);
        holder.tvWebsite.setVisibility(View.GONE);
        holder.tvStreet.setVisibility(View.GONE);
        holder.tvSuite.setVisibility(View.GONE);
        holder.tvCity.setVisibility(View.GONE);
        holder.tvZip.setVisibility(View.GONE);
        holder.ivInfo.setVisibility(View.GONE);
        holder.ivFlag.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }
}
