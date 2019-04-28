package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.model.dto.external.Genre;
import com.moveqq.core.moveqqcore.model.dto.external.Result;
import com.moveqq.core.moveqqcore.model.dto.external.SearchMovieIdResult;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    private List<com.moveqq.core.moveqqcore.model.dto.internal.Genre> getMovieGenres(List<Genre> genres) {
        List<com.moveqq.core.moveqqcore.model.dto.internal.Genre> genresInternal = new ArrayList<>();
        for (Genre genre : genres) {
            com.moveqq.core.moveqqcore.model.dto.internal.Genre genreInternal = new com.moveqq.core.moveqqcore.model.dto.internal.Genre();
            genreInternal.setName(genre.getName());
        }
        return genresInternal;
    }

}
