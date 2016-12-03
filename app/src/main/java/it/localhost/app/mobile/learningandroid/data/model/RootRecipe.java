package it.localhost.app.mobile.learningandroid.data.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 *
 */
@XStreamAlias("data")
public class RootRecipe {

    @XStreamAlias("recipes")
    private List<Recipe> mRecipeList;

    public List<Recipe> getRecipeList() {
        return mRecipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }
}
