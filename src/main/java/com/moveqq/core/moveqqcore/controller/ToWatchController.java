package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.mapper.MovieMapper;
import com.moveqq.core.moveqqcore.model.ResponseResult;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.model.dto.internal.User;
import com.moveqq.core.moveqqcore.service.MovieService;
import com.moveqq.core.moveqqcore.service.ToWatchService;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toWatch")
public class ToWatchController {

    private ToWatchService toWatchService;

    public ToWatchController(ToWatchService toWatchService) {
        this.toWatchService = toWatchService;
    }

    /**
     *
     * @param userId
     * @param movieId this id is TMDB ID
     * @return
     */

    @PutMapping("/{userId}/movie-{movieId}")
    public ResponseEntity<ResponseResult> addMovieToUserList(@PathVariable(name = "userId") long userId, @PathVariable(name = "movieId") long movieId) {
        boolean result = toWatchService.addMovieToUserList(userId, movieId);
        if (result)
            return new ResponseEntity<>(new ResponseResult(ResponseResult.ResultType.OK), HttpStatus.CREATED);
        return new ResponseEntity<>(new ResponseResult(ResponseResult.ResultType.FAILED), HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{userId}/movie-{movieId}")
    public ResponseEntity<Movie> getMovieFromUserList(@PathVariable(name = "userId") long userId, @PathVariable(name = "movieId") long movieId) throws TmdbClientException {
        MovieEntity movieEntity = toWatchService.getMovieFromUserList(userId, movieId);
        return new ResponseEntity<>(MovieMapper.MOVIE_MAPPER.fromEntity(movieEntity), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Movie>> getMoviesFromUserList(@PathVariable(name = "userId") long userId) {
        return new ResponseEntity<>(MovieMapper.MOVIE_MAPPER.fromEntities(toWatchService.getMoviesFromUserList(userId)), HttpStatus.OK);
    }

    @PostMapping("/{userId}/movie-{movieId}")
    public ResponseEntity<ResponseResult> updateMovieOnUserList(@PathVariable(name = "userId") long userId, @PathVariable(name = "movieId") long movieId) {
        toWatchService.updateWatchState(userId, movieId);
        return null;
    }

    @DeleteMapping("/user-{userId}/movie-{movieId}")
    public ResponseEntity<ResponseResult> removeMovieFromUserList(@PathVariable(name = "userId") long userId, @PathVariable(name = "movieId") long movieID) {
        return null;
    }

}
