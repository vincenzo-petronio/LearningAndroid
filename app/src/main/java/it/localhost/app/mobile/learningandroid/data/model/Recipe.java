package it.localhost.app.mobile.learningandroid.data.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 */
@XStreamAlias("recipe")
public class Recipe {

    @XStreamAlias("title")
    private String title;

    @XStreamAlias("ingredients")
    private String ingredients;

    // URLConverter
    @XStreamAlias("href")
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
