package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.AnswerEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Answer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {

    AnswerMapper ANSWER_MAPPER = Mappers.getMapper(AnswerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", ignore = true)
    AnswerEntity toEntity(Answer s);

    @InheritInverseConfiguration
    Answer fromAnswer(AnswerEntity answerEntity);
}
