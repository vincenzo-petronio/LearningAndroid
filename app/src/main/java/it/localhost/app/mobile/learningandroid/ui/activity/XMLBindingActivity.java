package it.localhost.app.mobile.learningandroid.ui.activity;

import com.thoughtworks.xstream.XStream;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Movie;
import it.localhost.app.mobile.learningandroid.data.model.Recipe;
import it.localhost.app.mobile.learningandroid.data.model.RootMovie;
import it.localhost.app.mobile.learningandroid.data.model.RootRecipe;
import it.localhost.app.mobile.learningandroid.ui.adapter.XMLMoviesAdapter;
import it.localhost.app.mobile.learningandroid.ui.adapter.XMLRecipesAdapter;

/**
 *
 */

public class XMLBindingActivity extends BaseActivity {

    private static final String TAG = XMLBindingActivity.class.getSimpleName();
    private XMLMoviesAdapter mMoviesAdapter;
    private XMLRecipesAdapter mRecipesAdapter;
    private List<Movie> mMovieList;
    private List<Recipe> mRecipeList;

    @BindView(R.id.lvItems)
    protected ListView mLvItems;
    @BindView(R.id.swApi)
    protected SwitchCompat mSwitchApi;
    @BindView(R.id.emptyview)
    FrameLayout mFlEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initUI();
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
        return R.layout.activity_xmlbinding;
    }

    @Override
    public int getIdToolbar() {
        return 0;
    }

    @Override
    public int getIdFab() {
        return 0;
    }


    @OnCheckedChanged(R.id.swApi)
    void onSwApiCheckedChanged(boolean isChecked) {
        if (isChecked) {
            // RECIPES
            Log.i(TAG, "onSwApiCheckedChanged: RECIPES");
            loadDataRecipes();
        } else {
            // MOVIES
            Log.i(TAG, "onSwApiCheckedChanged: MOVIES");
            loadDataMovies();
        }
    }

    private void initUI() {
        mLvItems.setEmptyView(mFlEmptyView);

        mMoviesAdapter = new XMLMoviesAdapter(XMLBindingActivity.this);
        mRecipesAdapter = new XMLRecipesAdapter(XMLBindingActivity.this);

        mMovieList = Collections.emptyList();
        mRecipeList = Collections.emptyList();
    }

    private void loadDataMovies() {
        AssetManager assetManager = getAssets();
        InputStream inputStream;
        XStream xstream;

        try {
            inputStream = assetManager.open("omdpapi.xml");
            xstream = new XStream();
            xstream.processAnnotations(RootMovie.class);
            xstream.processAnnotations(Movie.class);
            RootMovie rootMovie = (RootMovie) xstream.fromXML(inputStream);
            if (rootMovie.isResponse()) {
                mMovieList = rootMovie.getMovieList();
                mLvItems.setAdapter(mMoviesAdapter);
                mMoviesAdapter.updateCollection(mMovieList);
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }
    }

    private void loadDataRecipes() {
        AssetManager assetManager = getAssets();
        InputStream inputStream;
        XStream xstream;

        try {
            inputStream = assetManager.open("recipepuppyapi.xml");
            xstream = new XStream();
            xstream.processAnnotations(RootRecipe.class);
            xstream.processAnnotations(Recipe.class);
            RootRecipe rootRecipe = (RootRecipe) xstream.fromXML(inputStream);
            mRecipeList = rootRecipe.getRecipeList();
            mLvItems.setAdapter(mRecipesAdapter);
            mRecipesAdapter.updateCollection(mRecipeList);
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }
    }
}
