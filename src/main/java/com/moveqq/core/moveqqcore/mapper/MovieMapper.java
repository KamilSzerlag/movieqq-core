package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = GenreMapper.class)
public interface MovieMapper {

    MovieMapper MOVIE_MAPPER = Mappers.getMapper(MovieMapper.class);

    MovieEntity toEntity(Movie s);

    default MovieEntity fromLong(Long id, @Context MovieRepository repo) {
        return repo.getOne(id);
    }

    

}
