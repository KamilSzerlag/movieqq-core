package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.fault.TmdbClientErrors;
import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.model.pojo.external.Genre;
import com.moveqq.core.moveqqcore.model.pojo.external.Result;
import com.moveqq.core.moveqqcore.model.pojo.external.SearchMovieIdResult;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private TmdbClientService movieDbService;

    public MovieServiceImpl(TmdbClientService movieDbService) {
        this.movieDbService = movieDbService;
    }

    @Override
    public List<Movie> getMoviesListByTitle(String title, String year) {
        List<Movie> movies = new ArrayList<>();
        try {
            List<Result> searchResult = movieDbService.findMoviesByQuery(title, year);
            if (searchResult == null)
                return null;
            for (Result result : searchResult) {
                Movie movie = new Movie.MovieBuilder(Long.valueOf(result.getId()), result.getTitle())
                        .withOverview(result.getOverview())
                        .withReleaseDate(result.getReleaseDate())
                        .withOriginalLanguage(result.getOriginalLanguage())
                        .build();
                movies.add(movie);
            }
        } catch (TmdbClientException e) {
            //TODO zastanowic sie nad prawidlowa obsluga
        }
        return movies;
    }

    @Override
    public Movie getMovieById(Long movieId) {
        Movie movie = null;
        try {
            SearchMovieIdResult result = movieDbService.findMovieById(movieId);
            if (result == null)
                return null;
            movie = new Movie.MovieBuilder(Long.valueOf(result.getId()), result.getTitle())
                    .withOverview(result.getOverview())
                    .withReleaseDate(result.getReleaseDate())
                    .withPopularity(result.getPopularity())
                    .withGenres(getMovieGenres(result.getGenres()))
                    .withBudget(result.getBudget())
                    .withPosterPath(result.getPosterPath())
                    .build();

        } catch (TmdbClientException e) {
            //TODO obsluga wyjatku
        }
        return movie;
    }

    private List<String> getMovieGenres(List<Genre> genres) {
        List<String> genresString = new ArrayList<>();
        for (Genre genre : genres)
            genresString.add(genre.getName());
        return genresString;
    }
}
