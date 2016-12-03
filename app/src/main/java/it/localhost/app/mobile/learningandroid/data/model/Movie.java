package it.localhost.app.mobile.learningandroid.data.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.FloatConverter;

/**
 *
 */
@XStreamAlias("movie")
public class Movie {

    @XStreamAlias("actors") // tag superfluo se il nome coincide
    @XStreamAsAttribute // è un attributo, non un tag
    private String actors;

    @XStreamAsAttribute
    private String director;

    @XStreamAsAttribute
    private String country;

    @XStreamAlias("imdbID")
    @XStreamAsAttribute
    private String id;

    @XStreamAlias("imdbRating")
    @XStreamAsAttribute
    private float rating;

    @XStreamAlias("imdbVotes")
    @XStreamAsAttribute
    private String votes;

    @XStreamAsAttribute
    private String plot;

    @XStreamAsAttribute
    private String poster;

    @XStreamAsAttribute
    private String released;

    @XStreamAsAttribute
    private String runtime;

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
}
