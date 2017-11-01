package it.localhost.app.mobile.learningandroid.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import io.realm.RealmList;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Task;
import it.localhost.app.mobile.learningandroid.data.model.UserStory;
import it.localhost.app.mobile.learningandroid.ui.fragment.RealmDetailsFragment;
import it.localhost.app.mobile.learningandroid.ui.fragment.RealmListFragment;

/**
 *
 */

public class RealmActivity extends BaseActivity {

    private static final String TAG = RealmActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        initUI();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public int getIdLayout() {
        return R.layout.layout_container_toolbar;
    }

    @Override
    public int getIdToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int getIdFab() {
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_realm, menu);

        onCreateOptionsMenuSearch(menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_search:
                return true;
            case R.id.toolbar_sortid:
//                mRealmAdapter.sortItems();
                return true;
            case R.id.toolbar_searchneeded:
//                mRealmAdapter.searchItem(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initUI() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new RealmListFragment()).commit();
    }

    /**
     * Naviga alla pagina con i dettagli dell'oggetto passato.
     *
     * @param userStory {@link UserStory}
     */
    public void navToDetails(UserStory userStory) {
        Bundle b = new Bundle();
        b.putParcelable("BUNDLE_USERSTORY", userStory);
        RealmDetailsFragment fragment = new RealmDetailsFragment();
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(null).commit();
    }

    /**
     * Gestione del menu Search
     *
     * @param menu Menu
     */
    private void onCreateOptionsMenuSearch(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.toolbar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                mRealmAdapter.searchItem(newText);
                return false;
            }
        });
    }
}
