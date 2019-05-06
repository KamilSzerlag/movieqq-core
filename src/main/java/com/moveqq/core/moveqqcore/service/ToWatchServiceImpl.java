package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.mapper.MovieMapper;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import com.moveqq.core.moveqqcore.repository.ToWatchRepository;
import com.moveqq.core.moveqqcore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ToWatchServiceImpl implements ToWatchService {

    private UserService userService;
    private UserRepository userRepository;
    private MovieService movieService;
    private MovieRepository movieRepository;
    private ToWatchRepository toWatchRepository;

    public ToWatchServiceImpl(MovieService movieService, UserService userService, UserRepository userRepository, ToWatchRepository toWatchRepository, MovieRepository movieRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.movieService = movieService;
        this.toWatchRepository = toWatchRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean addMovieToUserList(long userId, long movieId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(NoSuchElementException::new);
        Movie movie = movieService.getMovieById(movieId);
        if (movie != null)
            userEntity.getMovies().add(MovieMapper.MOVIE_MAPPER.toEntity(movie));
        return true;
    }

    @Override
    public MovieEntity getMovieFromUserList(long userId, long movieId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(NoSuchElementException::new);
        return userEntity.getMovies()
                .stream()
                .filter(m -> m.getTmdbId().equals(movieId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<MovieEntity> getMoviesFromUserList(long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(NoSuchElementException::new);
        return userEntity.getMovies();
    }

    @Override
    public void updateWatchState(long userId, long movieId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(NoSuchElementException::new);
        userEntity.getMovies()
                .stream()
                .filter(m -> m.getTmdbId()
                        .equals(movieId))
                .forEach(m -> m.setWatched(true));
    }

    @Override
    public void deleteMovieFromUserList(long userId, long movieId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElseThrow(NoSuchElementException::new);
        Set<MovieEntity> movieEntitiesSet = userEntity.getMovies();
        MovieEntity movieEntityToDelete = null;
        if (movieEntitiesSet != null && !movieEntitiesSet.isEmpty()) {
            for (MovieEntity m: userEntity.getMovies()) {
                if (m.getTmdbId().equals(movieId))
                    movieEntityToDelete = m;
            }
            userEntity.getMovies().remove(movieEntityToDelete);
        }
    }
}
