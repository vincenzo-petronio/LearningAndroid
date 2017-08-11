package it.localhost.app.mobile.learningandroid.ui.dagger.components;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import it.localhost.app.mobile.learningandroid.ui.activity.ContactsListActivity;
import it.localhost.app.mobile.learningandroid.ui.dagger.modules.ContactsListModule;

/**
 *
 */
@Subcomponent(modules = ContactsListModule.class)
public interface ContactsListActivityComponent extends AndroidInjector<ContactsListActivity> {

    // Aggiungo il Builder, ma non serve inject() e build() perché è un subcomponent e tali metodi
    // sono già presenti nel component
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ContactsListActivity> {
    }
}
