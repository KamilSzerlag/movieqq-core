package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMoviesListByTitle(String title, String year);
    Movie getMovieById(Long movieId);
}
