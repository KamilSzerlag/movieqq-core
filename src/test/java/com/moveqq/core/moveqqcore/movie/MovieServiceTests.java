package com.moveqq.core.moveqqcore.movie;

import com.moveqq.core.moveqqcore.model.pojo.external.Genre;
import com.moveqq.core.moveqqcore.model.pojo.external.ProductionCompany;
import com.moveqq.core.moveqqcore.model.pojo.external.SearchMovieIdResult;
import com.moveqq.core.moveqqcore.model.pojo.internal.Movie;
import com.moveqq.core.moveqqcore.service.MovieDbClientService;
import com.moveqq.core.moveqqcore.service.MovieService;
import com.moveqq.core.moveqqcore.service.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTests {

    private static final long TEST_MOVIE_ID = 100L;

    @Mock
    private MovieDbClientService movieDbService;


    private MovieService movieService;

    private SearchMovieIdResult movieIdResult;
    private List<String> genresList;

    @Before
    public void setUp() {
        movieDbService = mock(MovieDbClientService.class);

        movieService = new MovieServiceImpl(movieDbService);

        movieIdResult = new SearchMovieIdResult();
        List<Genre> genres = new ArrayList<>();
        Genre genre = new Genre();
        List<ProductionCompany> productionCompanies = new ArrayList<>();
        ProductionCompany productionCompany = new ProductionCompany();

        genre.setId(1);
        genre.setName("Family");
        genres.add(genre);
        productionCompany.setId(1);
        productionCompany.setName("Summit Ent.");
        productionCompanies.add(productionCompany);

        movieIdResult.setId(100);
        movieIdResult.setTitle("Shrek");
        movieIdResult.setAdult(false);
        movieIdResult.setBackdropPath("/kzeR7BA0htJ7BeI6QEUX3PVp39s.jpg");
        movieIdResult.setBudget(100_000);
        movieIdResult.setGenres(genres);
        movieIdResult.setHomepage("www.brak.pl");
        movieIdResult.setImdbId("1");
        movieIdResult.setOriginalLanguage("en");
        movieIdResult.setOriginalTitle("Shrek");
        movieIdResult.setOverview("This is movie about Shrek");
        movieIdResult.setBelongsToCollection(null);
        movieIdResult.setPopularity(7.000);
        movieIdResult.setPosterPath("/qV7QaSf7f7yC2lc985zfyOJIAIN.jpg");
        movieIdResult.setProductionCompanies(productionCompanies);
        movieIdResult.setReleaseDate("1998-03-05");
        movieIdResult.setRuntime(105);

        String[] stringsWithGenres = {"Family"};
        genresList = Arrays.asList(stringsWithGenres);
    }

    @Test
    public void shouldReturnMovieById() throws Exception {
        when(movieDbService.findMovieById(TEST_MOVIE_ID)).thenReturn(movieIdResult);
        Movie expectedMovie = new Movie.MovieBuilder(TEST_MOVIE_ID, "Shrek")
                .withOverview("This is movie about Shrek")
                .withReleaseDate("1998-03-05")
                .build();
        Movie movie = movieService.getMovieById(TEST_MOVIE_ID);
        assertThat(movie.getId()).isEqualTo(expectedMovie.getId());
        assertThat(movie.getBudget()).isNotEqualTo(expectedMovie.getBudget());
        assertThat(movie.getGenres()).isNotEqualTo(expectedMovie.getGenres());
    }
}