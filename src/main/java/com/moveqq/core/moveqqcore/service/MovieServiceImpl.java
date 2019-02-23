package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.fault.MovieDbException;
import com.moveqq.core.moveqqcore.model.pojo.external.Result;
import com.moveqq.core.moveqqcore.model.pojo.external.SearchMovieIdResult;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDbServiceClient movieDbService;

    @Override
    public List<Movie> getMoviesListByTitle(String name, String year) {
        List<Movie> movies = new ArrayList<>();
        try {
            List<Result> searchResult = movieDbService.findMoviesByQuery(name,year);
            for (Result result : searchResult){
                Movie movie = new Movie();
                movie.setId(Long.valueOf(result.getId()));
                movie.setTitle(result.getTitle());
                movie.setOverView(result.getOverview());
                movie.setReleaseDate(result.getReleaseDate());
                movies.add(movie);
            }
        } catch (MovieDbException e) {
            //TODO oblsuga wyjatku;
        }
        //TODO przeniesienie return do try
        return movies;
    }

    @Override
    public Movie getMovieById(Long movieId) {
        try {
            Movie movie = new Movie();
            SearchMovieIdResult result = movieDbService.findMovieById(movieId);
            movie.setId(Long.valueOf(result.getId()));
            movie.setTitle(result.getTitle());
            movie.set
        } catch (MovieDbException e) {
            //TODO obsluga wyjatku
        }
    }
}
