package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import com.moveqq.core.moveqqcore.repository.QuestionRepository;

public class QuizServiceImpl implements QuizService {

    private QuestionRepository questionRepository;

    private MovieRepository movieRepository;

    public QuizServiceImpl(QuestionRepository questionRepository, MovieRepository movieRepository) {
        this.questionRepository = questionRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Long createQuestion(Question question) {
        return null;
    }
}
