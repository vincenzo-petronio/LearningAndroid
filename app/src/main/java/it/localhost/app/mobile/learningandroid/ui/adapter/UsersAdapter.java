package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.User;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> users;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameSurname, tvEmail, tvPhone, tvWebsite, tvStreet, tvSuite, tvCity, tvZip;

        public ViewHolder(View itemView) {
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
     * @param users   List<User>
     * @param context Context
     */
    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    private Context getContext() {
        return context;
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
        holder.tvEmail.setText(user.getEmail());
        holder.tvPhone.setText(user.getPhone());
        holder.tvWebsite.setText(user.getWebsite());
        holder.tvStreet.setText(user.getAddress().getStreet());
        holder.tvSuite.setText(user.getAddress().getSuite());
        holder.tvCity.setText(user.getAddress().getCity());
        holder.tvZip.setText(user.getAddress().getZipcode());
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }
}
