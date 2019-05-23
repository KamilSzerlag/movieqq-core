package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import com.moveqq.core.moveqqcore.repository.QuestionRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.NoSuchElementException;

@Mapper(uses = {AnswerMapper.class, MovieMapper.class})
public interface QuestionMapper {

    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "movie", source = "movieId")
    @Mapping(target = "answers", source = "answers")
    @Mapping(target = "id", ignore = true)
    QuestionEntity toEntity(Question s, @Context MovieRepository movieRepository);

    @Mapping(target = "questionId", source = "id" )
    @Mapping(target = "movieId", source = "movie")
    Question fromEntity(QuestionEntity questionEntity);

    List<Question> fromEntitiesList(List<QuestionEntity> questionList);

    default QuestionEntity fromLong(Long questionId, @Context QuestionRepository questionRepository, @Context CyclingAvoidingAnswerQuestion cyclingAvoidingAnswerQuestion) {
        if (questionId != null)
           return questionRepository.findById(questionId).orElseThrow(NoSuchElementException::new);
        else return null;
    }

    default Long IdfromEntity(QuestionEntity questionEntity, @Context CyclingAvoidingAnswerQuestion cyclingAvoidingAnswerQuestion) {
        if (questionEntity != null)
            return questionEntity.getId();
        return null;
    }
}