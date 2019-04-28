package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;

public interface QuizService {
    Long createQuestion(Question question);

}
