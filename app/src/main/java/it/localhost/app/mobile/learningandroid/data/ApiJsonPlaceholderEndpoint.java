package it.localhost.app.mobile.learningandroid.data;

import java.util.List;

import io.reactivex.Observable;
import it.localhost.app.mobile.learningandroid.data.model.Comment;
import it.localhost.app.mobile.learningandroid.data.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface ApiJsonPlaceholderEndpoint {

    // N.B. Posso mixare chiamate con adapter diversi, quindi con Call e Observable.

    // **** RETROFIT ****

    @GET("/posts/{postId}/comments")
    Call<List<Comment>> getComments(@Path("postId") String postId);


    // **** RETROFIT + RXJava2 ****

    @GET("/posts")
    Observable<List<Post>> getRxPosts();

    @GET("/posts/{postId}")
    Observable<Post> getRxPostId(@Path("postId") String postId);

    @GET("/posts/{postId}/comments")
    Observable<List<Comment>> getRxPostIdComments(@Path("postId") String postId);
}
