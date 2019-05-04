package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.GenreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper GENRE_MAPPER = Mappers.getMapper(GenreMapper.class);

    default List<GenreEntity> toName(List<String> genres){
        List<GenreEntity> list = new ArrayList<>();
        for (String g: genres) {
            GenreEntity entity = new GenreEntity();
            entity.setName(g);
            list.add(entity);
        }
        return list;
    }

    default List<String> fromGenre(List<GenreEntity> genreEntities) {
        List<String> genres = new ArrayList<>();
        for (GenreEntity genreEntity : genreEntities) {
            genres.add(genreEntity.getName());
        }
        return genres;
    }


}
