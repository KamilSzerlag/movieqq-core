package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;

import java.util.List;

/**
 * Obsluga listy
 * TO-DO "Do ogladniecia"
 */

public interface ToWatchService {
    boolean addMovieToUserList(long userID, long movieID);

    MovieEntity getMovieFromUserList(long userID, long movieID);

    List<MovieEntity> getMoviesFromUserList(long userID);

    void updateWatchState(long userId, long movieId);
}
