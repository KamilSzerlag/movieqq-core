package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.model.ResponseResult;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.model.restbody.MovieResponse;
import com.moveqq.core.moveqqcore.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:4200", maxAge = 3500)
@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movieList")
    public MovieResponse getMoviesWithQuery(@RequestParam(name = "title") String title,
                                            @RequestParam(name = "year", required = false) String year) {
        MovieResponse response = new MovieResponse();
        List<Movie> movieList = movieService.getMoviesListByTitle(title, year);
        if (movieList == null)
            response.setResult(ResponseResult.ResultType.FAILED);
        response.setMovieList(movieList);
        return response;
    }

    @GetMapping("/movie/{id}")
    public MovieResponse getMovieById(@PathVariable(name = "id") String id) {
        MovieResponse response = new MovieResponse();
        response.setMovie(movieService.getMovieById(Long.valueOf(id)));
        return response;
    }

}
