package com.moveqq.core.moveqqcore.movie;

import com.moveqq.core.moveqqcore.model.pojo.external.Genre;
import com.moveqq.core.moveqqcore.model.pojo.external.ProductionCompany;
import com.moveqq.core.moveqqcore.model.pojo.external.SearchMovieIdResult;
import com.moveqq.core.moveqqcore.service.MovieDbClientService;
import com.moveqq.core.moveqqcore.service.MovieService;
import com.moveqq.core.moveqqcore.service.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTests {

    @MockBean
    MovieDbClientService movieDbClient;

    @Autowired
    MovieService movieService;

    @TestConfiguration
    static class MovieServiceImplTestContextConfiguration {

        @Bean
        public MovieService movieService() {
            return new MovieServiceImpl();
        }
    }


    @Before
    public void setUp() {
        SearchMovieIdResult movieIdResult = new SearchMovieIdResult();
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

        movieIdResult.setId(1);
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
        List<String> genresList = Arrays.asList(stringsWithGenres);
        try {
            Mockito.when(movieDbClient.findMovieById(100L)).thenReturn(movieIdResult);
        } catch (Exception e) {

        }
    }

    @Test
    public void shouldReturnMovieById() {
        try {
            SearchMovieIdResult fetchedMovie = movieDbClient.findMovieById(100L);
            assertThat(fetchedMovie.getTitle()).isEqualTo("Shrek");
            assertThat(fetchedMovie.getGenres()).contains();
        } catch (Exception e) {

        }

    }
}