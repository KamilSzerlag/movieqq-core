package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AnswerMapper.class, MovieMapper.class})
public interface QuestionMapper{

    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "movie", source = "movieId")
    @Mapping(target = "answers", source = "answers")
    @Mapping(target = "id", ignore = true)
    QuestionEntity toEntity(Question s, @Context MovieRepository movieRepository);

    Question fromEntity(QuestionEntity questionEntity);

    List<Question> fromEntitiesList(List<QuestionEntity> questionList);

}