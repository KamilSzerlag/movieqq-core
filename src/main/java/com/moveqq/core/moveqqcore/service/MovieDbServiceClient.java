package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.fault.MovieDbException;
import com.moveqq.core.moveqqcore.model.pojo.external.Result;
import com.moveqq.core.moveqqcore.model.pojo.external.SearchMovieIdResult;

import java.util.List;

public interface MovieDbServiceClient {

    List<Result> findMoviesByQuery(String queryName, String year) throws MovieDbException;
    SearchMovieIdResult findMovieById(Long movieId) throws MovieDbException;
}
