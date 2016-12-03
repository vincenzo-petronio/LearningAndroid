package it.localhost.app.mobile.learningandroid.data.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

import java.util.List;

/**
 *
 */
@XStreamAlias("root")
public class RootMovie {

    @XStreamConverter(value = BooleanConverter.class, booleans = {false}, strings = {"True", "False"})
    @XStreamAsAttribute
    private boolean response;

    // Uso questo tag quando non ho un parent ma una collezione implicita di tag
    @XStreamImplicit(itemFieldName = "movie")
    private List<Movie> mMovieList;

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }
}
