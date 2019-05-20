package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.GenreEntity;
import com.moveqq.core.moveqqcore.repository.GenresRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper GENRE_MAPPER = Mappers.getMapper(GenreMapper.class);

    default List<GenreEntity> toName(List<String> genres, @Context GenresRepository genresRepository) {
        if (genres != null && !genres.isEmpty()) {
            List<GenreEntity> genresInRepo = genresRepository.findGenreEntitiesByNameIsIn(genres);
            if (genresInRepo == null)
                genresInRepo = new ArrayList<>();
            for (String genreName : genres) {
               boolean isRepoContains  = genresInRepo.stream().anyMatch(g -> g.getName().equals(genreName));
               if (!isRepoContains) {
                   GenreEntity entity = new GenreEntity();
                   entity.setName(genreName);
                   genresInRepo.add(entity);
               }
            }
            genresRepository.saveAll(genresInRepo);
            return genresInRepo;
        }
        return null;
    }

    default List<String> fromGenre(List<GenreEntity> genreEntities) {
        if (genreEntities != null && !genreEntities.isEmpty()) {
            List<String> genres = new ArrayList<>();
            for (GenreEntity genreEntity : genreEntities) {
                genres.add(genreEntity.getName());
            }
            return genres;
        }
        return null;
    }


}
