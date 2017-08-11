package it.localhost.app.mobile.learningandroid.dagger.modules;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import it.localhost.app.mobile.learningandroid.ui.activity.ContactsListActivity;
import it.localhost.app.mobile.learningandroid.ui.dagger.components.ContactsListActivityComponent;

/**
 * Builder dove mappare tutte le Activity
 */
@Module
public abstract class ActivityBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(ContactsListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindContactsListActivity(ContactsListActivityComponent.Builder builder);

//    @Binds
//    @IntoMap
//    @ActivityKey(ContactDetailsActivity.class)
//    abstract AndroidInjector.Factory<? extends Activity> bindContactDetailsActivity(ContactDetailsActivityComponent.Builder builder);

}
