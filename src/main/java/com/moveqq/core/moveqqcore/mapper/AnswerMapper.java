package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.AnswerEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Answer;
import com.moveqq.core.moveqqcore.repository.QuestionRepository;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = QuestionMapper.class)
public interface AnswerMapper {

    AnswerMapper ANSWER_MAPPER = Mappers.getMapper(AnswerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", source = "questionId")
    AnswerEntity toEntity(Answer s, @Context QuestionRepository questionRepository, @Context CyclingAvoidingAnswerQuestion cyclingAvoidingAnswerQuestion);

    @Mapping(target = "answerId", source = "id")
    @Mapping(target = "questionId", source = "question")
    Answer fromAnswer(AnswerEntity answerEntity, @Context CyclingAvoidingAnswerQuestion cyclingAvoidingAnswerQuestion);
}
