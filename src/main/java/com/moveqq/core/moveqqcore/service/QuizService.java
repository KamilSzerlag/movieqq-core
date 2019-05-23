package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.model.dto.internal.Answer;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;

import java.util.List;
import java.util.Set;

public interface QuizService {
    List<Question> getAllQuestionsForMovie(long movieId);

    boolean createQuestion(Question question);

    boolean deleteQuestion(long id);

    boolean addAnswersToQuestion(long questionId, Set<Answer> answers);

    boolean removeAnswersFromQuestion(long questionId, List<Long> answersIds);
}
