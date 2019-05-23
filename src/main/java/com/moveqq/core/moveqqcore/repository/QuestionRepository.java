package com.moveqq.core.moveqqcore.repository;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<QuestionEntity, Long>, JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAllByMovie(MovieEntity movie);

    boolean existsByContentContainingIgnoreCase(String content);
}
