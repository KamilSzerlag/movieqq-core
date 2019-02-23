package com.moveqq.core.moveqqcore.controller;

import com.moveqq.core.moveqqcore.model.pojo.external.Result;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;
import com.moveqq.core.moveqqcore.model.restbody.HelloRequest;
import com.moveqq.core.moveqqcore.model.restbody.HelloResponse;
import com.moveqq.core.moveqqcore.model.restbody.MovieResponse;
import com.moveqq.core.moveqqcore.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

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
        response.setMovieList(movieService.getMoviesListByTitle(query,year));
        return response;
    }
}
