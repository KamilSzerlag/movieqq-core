package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;

import java.util.List;

/**
 * Obsluga listy
 * TO-DO "Do ogladniecia"
 */

public interface ToWatchService {
    boolean addMovieToList(Movie movie);
    List<MovieEntity> moviesOnUserList(UserEntity userEntity);
}
