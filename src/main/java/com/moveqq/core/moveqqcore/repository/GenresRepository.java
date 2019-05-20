package com.moveqq.core.moveqqcore.repository;

import com.moveqq.core.moveqqcore.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface GenresRepository extends JpaRepository<GenreEntity, Long> {
    List<GenreEntity> findGenreEntitiesByNameIsIn(List<String> genres);
}
