package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.fault.TmdbClientErrors;
import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.model.dto.external.Result;
import com.moveqq.core.moveqqcore.model.dto.external.SearchMovieIdResult;
import com.moveqq.core.moveqqcore.model.dto.external.SearchQueryResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TmdbClientServiceImpl implements TmdbClientService {

    private RestTemplate restTemplate;
    @Value("${moviedb.apikey}")
    private String apiKey;

    public TmdbClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SearchMovieIdResult findMovieById(Long movieId) throws TmdbClientException {
        String urlWithId;
        urlWithId = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;
        SearchMovieIdResult result = restTemplate.getForObject(urlWithId, SearchMovieIdResult.class);
        if (result == null)
            throw new TmdbClientException(TmdbClientErrors.MOVIE_DB_NOT_FOUND);
        return result;
    }

    @Override
    public List<Result> findMoviesByQuery(String queryName, String year) throws TmdbClientException {
        if (queryName == null)
            throw new TmdbClientException(TmdbClientErrors.MOVIE_DB_BAD_PARAMETERS);
        String urlWithQuery;
        if (year == null)
            urlWithQuery = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + queryName;
        else
            urlWithQuery = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + queryName + "&year=" + year;
        SearchQueryResult result = restTemplate.getForObject(urlWithQuery, SearchQueryResult.class);
        if (result == null)
            throw new TmdbClientException(TmdbClientErrors.MOVIE_DB_NOT_FOUND);
        return result.getResults();
    }
}