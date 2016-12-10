package it.localhost.app.mobile.learningandroid.data.model;

/**
 *
 */

public class ComplexEventMessage {

    private String mMessage;
    private Movie mMovie;

    /**
     * @param message String
     * @param movie   Movie
     */
    public ComplexEventMessage(String message, Movie movie) {
        mMessage = message;
        mMovie = movie;
    }


    // GETTER & SETTER

    public Movie getMovie() {
        return mMovie;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
