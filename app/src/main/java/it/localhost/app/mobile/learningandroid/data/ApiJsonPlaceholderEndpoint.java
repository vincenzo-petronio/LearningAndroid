package it.localhost.app.mobile.learningandroid.data;

import java.util.List;

import io.reactivex.Observable;
import it.localhost.app.mobile.learningandroid.data.model.Comment;
import it.localhost.app.mobile.learningandroid.data.model.Post;
import it.localhost.app.mobile.learningandroid.data.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 */
public interface ApiJsonPlaceholderEndpoint {

    // N.B. Posso mixare chiamate con adapter diversi, quindi con Call e Observable.

    // **** RETROFIT ****

    @GET("/posts/{postId}/comments")
    Call<List<Comment>> getComments(@Path("postId") String postId);

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/users/{userId}")
    Call<User> getUser(@Path("userId") String userId);

    @GET("/posts")
    Call<List<Post>> getPosts(@Query("userId") String userId);


    // **** RETROFIT + RXJava2 ****

    @GET("/posts")
    Observable<List<Post>> getRxPosts();

    @GET("/posts/{postId}")
    Observable<Post> getRxPostId(@Path("postId") String postId);

    @GET("/posts/{postId}/comments")
    Observable<List<Comment>> getRxPostIdComments(@Path("postId") String postId);
}
