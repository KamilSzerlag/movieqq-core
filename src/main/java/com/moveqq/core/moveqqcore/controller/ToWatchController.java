package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.model.ResponseResult;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;
import com.moveqq.core.moveqqcore.service.MovieService;
import com.moveqq.core.moveqqcore.service.ToWatchService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toWatch")
public class ToWatchController {

    private ToWatchService toWatchService;
    private MovieService movieService;

    public ToWatchController(ToWatchService toWatchService, MovieService movieService) {
        this.toWatchService = toWatchService;
        this.movieService = movieService;
    }

    @PutMapping("/addMovie/{movieId}")
    public ResponseResult addMovieToWatch(@PathVariable(name = "movieId") Long movieId){
        Movie movie = movieService.getMovieById(movieId);
        boolean result = toWatchService.addMovieToList(movie);
        if (result)
            return new ResponseResult();
        return new ResponseResult(ResponseResult.ResultType.FAILED);
    }

}
