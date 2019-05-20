package com.moveqq.core.moveqqcore.movie;

import com.moveqq.core.moveqqcore.entity.GenreEntity;
import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.mapper.GenreMapper;
import com.moveqq.core.moveqqcore.mapper.MovieMapper;
import com.moveqq.core.moveqqcore.model.dto.external.Genre;
import com.moveqq.core.moveqqcore.model.dto.external.ProductionCompany;
import com.moveqq.core.moveqqcore.model.dto.external.SearchMovieIdResult;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.repository.GenresRepository;
import com.moveqq.core.moveqqcore.service.MovieService;
import com.moveqq.core.moveqqcore.service.MovieServiceImpl;
import com.moveqq.core.moveqqcore.service.TmdbClientService;
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
    private static final long TEST_NOT_EXISTING_MOVIE_ID = 1234567890L;
    private static final String TEST_QUERY_TITLE = "UNKNOWN MOVIE TITLE";

    @Mock
    private TmdbClientService tmdbClientService;

    @Mock
    private GenresRepository genresRepository;

    private MovieService movieService;

    private SearchMovieIdResult movieIdResult;
    private List<String> genresList;
    private List<GenreEntity> genreComedy;
    private List<GenreEntity> expectedGenres;

    @Before
    public void setUp() {
        tmdbClientService = mock(TmdbClientService.class);

        movieService = new MovieServiceImpl(tmdbClientService);

        movieIdResult = new SearchMovieIdResult();
        List<Genre> genres = new ArrayList<>();
        List<ProductionCompany> productionCompanies = new ArrayList<>();
        ProductionCompany productionCompany = new ProductionCompany();

        String[] genresTypes = {"Family", "Comedy", "Funny"};
        for (int i = 0; i < genresTypes.length; i++) {
            Genre genre = new Genre();
            genre.setId(i + 1);
            genre.setName(genresTypes[i]);
            genres.add(genre);
        }

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

        String[] stringsWithGenres = {"Family","Comedy","Funny"};
        genresList = Arrays.asList(stringsWithGenres);
        GenreEntity comedy = new GenreEntity();
        comedy.setName("Comedy");
        genreComedy = new ArrayList<>();
        genreComedy.add(comedy);
        expectedGenres = new ArrayList<>();
        for (String genreName: stringsWithGenres) {
            GenreEntity genreEntity = new GenreEntity();
            genreEntity.setName(genreName);
            expectedGenres.add(genreEntity);
        }
    }

    @Test
    public void shouldReturnMovieById() throws Exception {
        when(tmdbClientService.findMovieById(TEST_MOVIE_ID)).thenReturn(movieIdResult);
        Movie expectedMovie = new Movie.MovieBuilder(TEST_MOVIE_ID, "Shrek")
                .withOverview("This is movie about Shrek")
                .withReleaseDate("1998-03-05")
                .build();
        Movie movie = movieService.getMovieById(TEST_MOVIE_ID);
        assertThat(movie.getId()).isEqualTo(expectedMovie.getId());
        assertThat(movie.getBudget()).isNotEqualTo(expectedMovie.getBudget());
        assertThat(movie.getGenres()).isNotEqualTo(expectedMovie.getGenres());
    }

    @Test
    public void shouldReturnNullMovieById() throws Exception {
        when(tmdbClientService.findMovieById(TEST_NOT_EXISTING_MOVIE_ID)).thenReturn(null);
        assertThat(movieService.getMovieById(TEST_NOT_EXISTING_MOVIE_ID)).isEqualTo(null);
    }

    @Test
    public void shouldReturnNullMovieListByQueryTitle() throws Exception {
        when(tmdbClientService.findMoviesByQuery(TEST_QUERY_TITLE, null)).thenReturn(null);
        assertThat(movieService.getMoviesListByTitle(TEST_QUERY_TITLE, null)).isEqualTo(null);
    }

    @Test
    public void shouldReturnListWithGenresTypeOfString() throws Exception {
        when(tmdbClientService.findMovieById(TEST_MOVIE_ID)).thenReturn(movieIdResult);
        assertThat(movieService.getMovieById(TEST_MOVIE_ID).getGenres()).isEqualTo(genresList);
    }

    @Test
    public void shouldBeEqualsByEqualsMethodAndHashCode() throws Exception {
        Movie movieFirst = new Movie.MovieBuilder(1L, "Okon")
                .withBudget(1313)
                .build();
        Movie movieSecond = new Movie.MovieBuilder(1L, "Okon")
                .withBudget(131)
                .withOverview("abcd")
                .build();
        Movie movieThird = new Movie.MovieBuilder(2L, "Pantera")
                .build();
        MovieEntity movieEntityFirst = MovieMapper.MOVIE_MAPPER.toEntity(movieFirst,genresRepository);
        MovieEntity movieEntitySecond = MovieMapper.MOVIE_MAPPER.toEntity(movieSecond, genresRepository);
        MovieEntity movieEntityThird = MovieMapper.MOVIE_MAPPER.toEntity(movieThird, genresRepository);
        movieEntityFirst.setId(100L);
        movieEntitySecond.setId(211L);
        assertThat(movieEntityFirst).isEqualTo(movieEntitySecond);
        assertThat(movieEntityFirst.hashCode()).isEqualTo(movieEntitySecond.hashCode());
        assertThat(movieEntityFirst).isNotEqualTo(movieEntityThird);
    }

    @Test
    public void shouldReturnGenreEntities() throws Exception {
        when(genresRepository.findGenreEntitiesByNameIsIn(genresList)).thenReturn(genreComedy);
        List<GenreEntity> testGenres = GenreMapper.GENRE_MAPPER.toName(genresList, genresRepository);
        assertThat(testGenres).isEqualTo(expectedGenres);
    }
}