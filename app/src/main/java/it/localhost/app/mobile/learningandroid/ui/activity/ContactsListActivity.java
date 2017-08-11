package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Contact;
import it.localhost.app.mobile.learningandroid.ui.adapter.ContactsListAdapter;
import it.localhost.app.mobile.learningandroid.ui.presenter.ContactsListPresenter;

/**
 *
 */

public class ContactsListActivity extends BaseActivity {

    private static final String TAG = ContactsListActivity.class.getSimpleName();

    @Inject
    ContactsListPresenter mContactsListPresenter;
    @Inject
    ContactsListAdapter lvContactsAdapter;
    @BindView(R.id.lvContacts)
    ListView mLvContacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        mLvContacts.setAdapter(lvContactsAdapter);
        mLvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact c = (Contact) adapterView.getAdapter().getItem(i);

                Toast.makeText(ContactsListActivity.this, c.getBirthday(), Toast.LENGTH_SHORT).show();
            }
        });
        mContactsListPresenter.loadData();
    }

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.activity_contactslist;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    public void setContacts(List<Contact> contacts) {
        Log.v(TAG, "setContacts");
        lvContactsAdapter.updateContactsList(contacts);
    }
}
