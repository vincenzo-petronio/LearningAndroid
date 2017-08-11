package it.localhost.app.mobile.learningandroid.ui.presenter;

import javax.inject.Inject;

import it.localhost.app.mobile.learningandroid.data.ApiService;
import it.localhost.app.mobile.learningandroid.ui.activity.ContactsListActivity;

public class ContactsListPresenter {

    private ContactsListActivity mContactsListActivity;
    private ApiService mApiService;

    @Inject
    public ContactsListPresenter(ContactsListActivity contactsListActivity, ApiService apiService) {
        this.mContactsListActivity = contactsListActivity;
        this.mApiService = apiService;
    }

    public void loadData() {
        mContactsListActivity.setContacts(mApiService.getContacts());
    }
}
