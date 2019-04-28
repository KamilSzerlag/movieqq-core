package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.GenreEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Genre;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper GENRE_MAPPER = Mappers.getMapper(GenreMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies", ignore = true)
    GenreEntity toEntity(Genre genre);

    @InheritInverseConfiguration
    Genre genres(GenreEntity entity);


}
