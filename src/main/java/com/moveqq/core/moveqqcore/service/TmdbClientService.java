package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.model.pojo.external.Result;
import com.moveqq.core.moveqqcore.model.pojo.external.SearchMovieIdResult;

import java.util.List;

public interface TmdbClientService {

    List<Result> findMoviesByQuery(String queryName, String year) throws TmdbClientException;
    SearchMovieIdResult findMovieById(Long movieId) throws TmdbClientException;
}
