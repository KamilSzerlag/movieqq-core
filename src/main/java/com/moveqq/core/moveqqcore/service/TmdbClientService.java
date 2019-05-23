package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.model.dto.external.Result;
import com.moveqq.core.moveqqcore.model.dto.external.SearchMovieIdResult;

import java.util.List;

public interface TmdbClientService {
    List<Result> findMoviesByQuery(String queryName, String year) throws TmdbClientException;

    SearchMovieIdResult findMovieById(Long movieId) throws TmdbClientException;
}
