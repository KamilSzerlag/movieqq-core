package com.moveqq.core.moveqqcore.service;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.moveqq.core.moveqqcore.entity.AnswerEntity;
import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Answer;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import com.moveqq.core.moveqqcore.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import static com.googlecode.jmapper.api.JMapperAPI.*;

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
