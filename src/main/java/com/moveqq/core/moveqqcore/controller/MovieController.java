package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.model.restbody.MovieResponse;
import com.moveqq.core.moveqqcore.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:4200", maxAge = 3500)
@RestController
@Controller
@RequestMapping(path = "/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movieList")
    public MovieResponse getMoviesWithQuery(@RequestParam(name = "query") String query,
                                             @RequestParam(name = "year", required = false) String year) {
        MovieResponse response = new MovieResponse();
        response.setMovieList(movieService.getMoviesListByTitle(query, year));
        return response;
    }

    @GetMapping("/movie/{id}")
    public MovieResponse getMovieById(@PathVariable(name = "id") String id) {
        MovieResponse response = new MovieResponse();
        response.setMovie(movieService.getMovieById(Long.valueOf(id)));
        return response;
    }

}
