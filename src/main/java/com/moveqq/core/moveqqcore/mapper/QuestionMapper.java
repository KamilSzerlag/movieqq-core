package com.moveqq.core.moveqqcore.mapper;

import com.moveqq.core.moveqqcore.entity.AnswerEntity;
import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.QuestionRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AnswerMapper.class, MovieMapper.class})
public interface QuestionMapper{

    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "questionContent", source = "questionContent")
    @Mapping(target = "answers", source = "answers")
    @Mapping(target = "id", ignore = true)
    QuestionEntity toEntity(Question s, @Context QuestionRepository repo);


}