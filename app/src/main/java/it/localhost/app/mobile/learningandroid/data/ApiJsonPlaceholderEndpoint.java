package it.localhost.app.mobile.learningandroid.data;

import java.util.List;

import io.reactivex.Observable;
import it.localhost.app.mobile.learningandroid.data.model.Comment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface ApiJsonPlaceholderEndpoint {

    // N.B. Posso mixare chiamate con adapter diversi, quindi con Call e Observable.

    // RETROFIT
    @GET("/posts/{postId}/comments")
    Call<List<Comment>> getComments(@Path("postId") String postId);


    // RETROFIT + RXJava2
    @GET("/posts/{postId}/comments")
    Observable<List<Comment>> getRxComments(@Path("postId") String postId);
}
