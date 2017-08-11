package it.localhost.app.mobile.learningandroid.ui.dagger.modules;

import dagger.Module;
import dagger.Provides;
import it.localhost.app.mobile.learningandroid.data.ApiService;
import it.localhost.app.mobile.learningandroid.ui.activity.ContactsListActivity;
import it.localhost.app.mobile.learningandroid.ui.adapter.ContactsListAdapter;
import it.localhost.app.mobile.learningandroid.ui.presenter.ContactsListPresenter;

/**
 *
 */
@Module
public class ContactsListModule {

    @Provides
    ApiService provideApiService() {
        return new ApiService();
    }

    @Provides
    ContactsListPresenter provideContactsListPresenter(ContactsListActivity contactsListActivity, ApiService apiService) {
        return new ContactsListPresenter(contactsListActivity, apiService);
    }

    @Provides
    ContactsListAdapter provideContactsListAdapter() {
        return new ContactsListAdapter();
    }
}
