package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.mapstruct.factory.Mappers;

import javax.persistence.Entity;

@Mapper(uses = GenreMapper.class)
public interface MovieMapper {

    MovieMapper MOVIE_MAPPER = Mappers.getMapper(MovieMapper.class);

    MovieEntity toEntity(Movie s, @Context MovieRepository repo);

    @ObjectFactory
    default <T extends Entity> T findMovieEntityById(Movie m, @Context MovieRepository repo,
                                                     @TargetType Class<T> targetType) {
        MovieEntity entity = repo.findMovieEntityById(m.getId());
        if (entity == null) {
            entity = new MovieEntity();
        }
        return (T) entity;
    }

}
