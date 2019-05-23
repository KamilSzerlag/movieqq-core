package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.repository.GenresRepository;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = GenreMapper.class)
public interface MovieMapper {

    MovieMapper MOVIE_MAPPER = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "tmdbId", source = "id")
    MovieEntity toEntity(Movie s, @Context GenresRepository genresRepository);

    default Movie fromEntity(MovieEntity movieEntity) throws TmdbClientException {
        return new Movie.MovieBuilder(movieEntity.getTmdbId(), movieEntity.getTitle())
                .withOverview(movieEntity.getOverView())
                .withReleaseDate(movieEntity.getReleaseDate())
                .withBudget(movieEntity.getBudget())
                .withGenres(GenreMapper.GENRE_MAPPER.fromGenre(movieEntity.getGenres()))
                .withPopularity(movieEntity.getPopularity())
                .withPosterPath(movieEntity.getPosterPath())
                .withOriginalTitle(movieEntity.getOriginalTitle())
                .build();
    }

    default MovieEntity fromLong(Long id, @Context MovieRepository repo) {
        if (id != null)
            return repo.findMovieEntityByTmdbId(id);
        else
            return null;
    }

    List<Movie> fromEntities(Set<MovieEntity> entities);

    default Long toMovieId(MovieEntity movieEntity) {
        if (movieEntity != null)
            return movieEntity.getTmdbId();
        return null;
    }

}
