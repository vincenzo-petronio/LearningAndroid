package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Contact;

/**
 *
 */

public class ContactsListAdapter extends BaseAdapter {

    private static final String TAG = ContactsListAdapter.class.getSimpleName();
    private final List<Contact> collection;

    public ContactsListAdapter() {
        Log.v(TAG, "ContactsListAdapter");
        this.collection = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public Object getItem(int i) {
        return collection.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_activity_contactslist, viewGroup, false);
        }

        Contact item = (Contact) getItem(i);

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        tvNome.setText(item.getName());

        Log.v(TAG, "tvName: " + tvNome.getText().toString());

        return view;
    }

    /**
     * Aggiorna il contenuto della collezione
     *
     * @param contacts List<Contact>
     */
    public void updateContactsList(List<Contact> contacts) {
        if (contacts != null && contacts.size() > 0) {
            this.collection.clear();
            this.collection.addAll(contacts);
            notifyDataSetChanged();
        }
    }
}
