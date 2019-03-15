package com.moveqq.core.moveqqcore.movie;

import com.moveqq.core.moveqqcore.controller.MovieController;
import com.moveqq.core.moveqqcore.fault.MovieDbErrors;
import com.moveqq.core.moveqqcore.fault.MovieDbException;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;
import com.moveqq.core.moveqqcore.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
@AutoConfigureWebClient
public class MovieControllerTests {

    private static final int TEST_MOVIE_ID = 100;
    private static final String TEST_QUERY_NAME = "Superman";
    private static final String TEST_QUERY_YEAR = "2000";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private Movie testMovieOne;
    private List<Movie> movieList;

    @Before
    public void initMovie() throws MovieDbException {
        Movie testMovieTwo;
        Movie testMovieThree;
        Movie testMovieFour;
        try {

            testMovieOne = new Movie.MovieBuilder(100L, "Lock, Stock and Two Smoking Barrels")
                    .withOverview("Movie about magic.")
                    .withReleaseDate("1998-03-05")
                    .withGenres(Stream.of("Comedy", "Crime").collect(Collectors.toList()))
                    .withBudget(1350000)
                    .withPopularity(5.878)
                    .withPosterPath("/qV7QaSf7f7yC2lc985zfyOJIAIN.jpg")
                    .build();

            testMovieTwo = new Movie.MovieBuilder(101L, "Superman vs. Batman")
                    .withOverview("Movie about Superman and Batman")
                    .withReleaseDate("2000-03-05")
                    .withGenres(Stream.of("Comedy", "Fantasy").collect(Collectors.toList()))
                    .withBudget(2150000)
                    .withPopularity(1.000)
                    .withPosterPath("/qV7QaSf7f7yC2lc985zfyOJIAIN.jpg")
                    .build();

            testMovieThree = new Movie.MovieBuilder(102L, "Superman and Shrek")
                    .withOverview("Movie about Superman and Shrek")
                    .withReleaseDate("2000-03-06")
                    .withGenres(Stream.of("Fantasy").collect(Collectors.toList()))
                    .withBudget(2250000)
                    .withPopularity(2.000)
                    .withPosterPath("/qV7QaSf7f7yC2lc985zfyOJIAIN.jpg")
                    .build();

            testMovieFour = new Movie.MovieBuilder(103L, "Superman is not a Hero")
                    .withOverview("Movie about not a Hero")
                    .withReleaseDate("2000-03-05")
                    .withGenres(Stream.of("Document").collect(Collectors.toList()))
                    .withBudget(50000)
                    .withPopularity(9.500)
                    .withPosterPath("/qV7QaSf7f7yC2lc985zfyOJIAIN.jpg")
                    .build();

            movieList = new ArrayList<>();
            movieList.add(testMovieTwo);
            movieList.add(testMovieThree);
            movieList.add(testMovieFour);

        } catch (MovieDbException e) {
            throw new MovieDbException(MovieDbErrors.MOVIE_DB_BAD_PARAMETERS);
        }
    }

    @Test
    public void shouldReturnMovieByIdUsingGet() throws Exception {
        given(this.movieService.getMovieById(100L)).willReturn(testMovieOne);
        this.mockMvc.perform(get("/movies/movie/{id}", TEST_MOVIE_ID, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("OK"))
                .andExpect(jsonPath("$.movie.id").value(100))
                .andExpect(jsonPath("$.movie.title").value("Lock, Stock and Two Smoking Barrels"))
                .andExpect(jsonPath("$.movie.overView").value("Movie about magic."))
                .andExpect(jsonPath("$.movie.releaseDate").value("1998-03-05"))
                .andExpect(jsonPath("$.movie.genres.[0]").value("Comedy"))
                .andExpect(jsonPath("$.movie.genres.[1]").value("Crime"))
                .andExpect(jsonPath("$.movie.budget").value("1350000"))
                .andExpect(jsonPath("$.movie.popularity").value("5.878"))
                .andExpect(jsonPath("$.movie.posterPath").value("/qV7QaSf7f7yC2lc985zfyOJIAIN.jpg"));
    }

    @Test
    public void shouldReturnMovieListByQueryTitle() throws Exception {
        given(this.movieService.getMoviesListByTitle(TEST_QUERY_NAME, null)).willReturn(movieList);
        this.mockMvc.perform(get("/movies/movieList?query={name}", TEST_QUERY_NAME, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.result").value("OK"))
                .andExpect(jsonPath("$.movieList.[0].title").value("Superman vs. Batman"))
                .andExpect(jsonPath("$.movieList.[1].title").value("Superman and Shrek"));
    }

    @Test
    public void shouldReturnMovieListByQueryTitleAndYear() throws Exception {
        given(this.movieService.getMoviesListByTitle(TEST_QUERY_NAME, TEST_QUERY_YEAR)).willReturn(movieList);
        this.mockMvc.perform(get("/movies/movieList?query={name}&year={year}", TEST_QUERY_NAME,                                         TEST_QUERY_YEAR, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.result").value("OK"))
                .andExpect(jsonPath("$.movieList.[0].title").value("Superman vs. Batman"))
                .andExpect(jsonPath("$.movieList.[1].title").value("Superman and Shrek"));
    }
}