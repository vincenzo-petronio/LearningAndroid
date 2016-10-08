package it.localhost.app.mobile.learningandroid.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo {
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("completed")
    @Expose
    private boolean completed;

    /**
     * @return The userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId The userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @param completed The completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
