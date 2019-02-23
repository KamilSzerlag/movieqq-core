package com.moveqq.core.moveqqcore.model.restbody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moveqq.core.moveqqcore.model.ResponseResult;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MovieResponse extends ResponseResult {

    private Movie movie;
    private List<Movie> movieList;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
