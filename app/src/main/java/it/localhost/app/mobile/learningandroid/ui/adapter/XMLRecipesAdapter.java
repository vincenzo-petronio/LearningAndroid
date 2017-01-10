package it.localhost.app.mobile.learningandroid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Recipe;
import it.localhost.app.mobile.learningandroid.ui.activity.XMLBindingActivity;

/**
 *
 */

public class XMLRecipesAdapter extends BaseAdapter {

    private static final String TAG = XMLBindingActivity.class.getSimpleName();
    private static final int TYPE_MOVIES = 0;
    private static final int TYPE_RECIPIES = 1;
    private List<Recipe> mCollection;
    private LayoutInflater mInflater;
    private Context mContext;

    public XMLRecipesAdapter(Context ctx) {
        this.mContext = ctx;
        mCollection = Collections.emptyList();
        mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @param collection List<Recipe>
     */
    public void updateCollection(List<Recipe> collection) {
        this.mCollection = collection;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public Object getItem(int i) {
        return mCollection.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe recipe = mCollection.get(position);

        if (recipe == null) {
            return null;
        }

        ViewHolderRecipes viewHolderRecipes;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_xmlbinding_recipe, parent, false);
            viewHolderRecipes = new ViewHolderRecipes(convertView);
            convertView.setTag(viewHolderRecipes);
        } else {
            viewHolderRecipes = (ViewHolderRecipes) convertView.getTag();
        }

        viewHolderRecipes.mTvTitle.setText(recipe.getTitle());
        viewHolderRecipes.mTvIngredients.setText(recipe.getIngredients());
        viewHolderRecipes.mTvLink.setText(recipe.getLink());

        return convertView;
    }

    /**
     * ViewHolder per Recipes
     */
    static class ViewHolderRecipes {

        @BindView(R.id.tvTitle)
        TextView mTvTitle;
        @BindView(R.id.tvIngredients)
        TextView mTvIngredients;
        @BindView(R.id.tvLink)
        TextView mTvLink;

        ViewHolderRecipes(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
