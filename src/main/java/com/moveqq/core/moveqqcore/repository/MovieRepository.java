package com.moveqq.core.moveqqcore.repository;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    MovieEntity findMovieEntityByUsersAndTmdbId(UserEntity userEntity, long movieId);
    List<MovieEntity> findMovieEntitiesByUsers(UserEntity userEntity);
    Optional<MovieEntity> findMovieEntitiesByTmdbId(long movieId);
}
