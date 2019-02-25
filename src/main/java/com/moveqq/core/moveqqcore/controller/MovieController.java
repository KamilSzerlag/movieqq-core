package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.model.restbody.HelloRequest;
import com.moveqq.core.moveqqcore.model.restbody.HelloResponse;
import com.moveqq.core.moveqqcore.model.restbody.MovieResponse;
import com.moveqq.core.moveqqcore.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(method = RequestMethod.POST, path = "/helloWorld")
    @ResponseBody

    public HelloResponse yellowWorld(@RequestBody HelloRequest requestBody) {
        return new HelloResponse(requestBody.getName(), requestBody.getRestAnswer());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/movieList")
    @ResponseBody
    public MovieResponse findMoviesWithQuery(@RequestParam(name = "query") String query,
                                             @RequestParam(name = "year", required = false) String year) {
        MovieResponse response = new MovieResponse();
        response.setMovieList(movieService.getMoviesListByTitle(query, year));
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/movie/{id}")
    @ResponseBody
    public MovieResponse findMovieById(@PathVariable(name = "id") String id) {
        MovieResponse response = new MovieResponse();
        response.setMovie(movieService.getMovieById(Long.valueOf(id)));
        return response;
    }
}
