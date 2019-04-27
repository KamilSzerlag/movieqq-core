package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.UserEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import com.moveqq.core.moveqqcore.repository.ToWatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToWatchServiceImpl implements ToWatchService {

    private MovieService movieService;
    private UserService userService;
    private MovieRepository movieRepository;
    private ToWatchRepository toWatchRepository;

    public ToWatchServiceImpl(MovieService movieService, UserService userService, ToWatchRepository toWatchRepository) {
        this.movieService = movieService;
        this.userService = userService;
        this.toWatchRepository = toWatchRepository;
    }

    @Override
    public boolean addMovieToList(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setBudget(movie.getBudget());
        movieEntity.setOverView(movie.getOverView());
        movieEntity.setOriginalLanguage(movie.getOriginalLanguage());
        movieEntity.setPopularity(movie.getPopularity());
        toWatchRepository.save(movieEntity);
        return true;
    }

    @Override
    public List<MovieEntity> moviesOnUserList(UserEntity userEntity) {
        return movieRepository.findMovieEntitiesByUsersIs(userEntity);
    }
}
