package it.localhost.app.mobile.learningandroid.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model for http://jsonplaceholder.typicode.com/photos
 */
public class Photo {
    @SerializedName("albumId")
    @Expose
    private int albumId;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;

    /**
     * @return The albumId
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId The albumId
     */
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
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
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * @param thumbnailUrl The thumbnailUrl
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
