package it.localhost.app.mobile.learningandroid.data;

import java.util.List;

import it.localhost.app.mobile.learningandroid.data.model.Comment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface ApiJsonPlaceholderEndpoint {

    @GET("/posts/{postId}/comments")
    Call<List<Comment>> getComments(@Path("postId") String postId);
}
