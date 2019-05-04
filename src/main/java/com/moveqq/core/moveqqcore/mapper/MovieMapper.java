package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.fault.TmdbClientException;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = GenreMapper.class)
public interface MovieMapper {

    MovieMapper MOVIE_MAPPER = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "tmdbId", source = "id")
    MovieEntity toEntity(Movie s);

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
            return repo.getOne(id);
        else
            return null;
    }

    List<Movie> fromEntities(List<MovieEntity> entities);

}
