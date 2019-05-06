package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.MovieEntity;

import java.util.Set;

/**
 * Obsluga listy
 * TO-DO "Do ogladniecia"
 */

public interface ToWatchService {
    boolean addMovieToUserList(long userID, long movieID);

    MovieEntity getMovieFromUserList(long userID, long movieID);

    Set<MovieEntity> getMoviesFromUserList(long userID);

    void updateWatchState(long userId, long movieId);

    void deleteMovieFromUserList(long userId, long movieId);
}
