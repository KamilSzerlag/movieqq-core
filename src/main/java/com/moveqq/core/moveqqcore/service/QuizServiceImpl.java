package com.moveqq.core.moveqqcore.service;

import com.moveqq.core.moveqqcore.entity.AnswerEntity;
import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.mapper.AnswerMapper;
import com.moveqq.core.moveqqcore.mapper.QuestionMapper;
import com.moveqq.core.moveqqcore.model.dto.internal.Answer;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import com.moveqq.core.moveqqcore.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private QuestionRepository questionRepository;

    private MovieRepository movieRepository;

    public QuizServiceImpl(QuestionRepository questionRepository, MovieRepository movieRepository) {
        this.questionRepository = questionRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Question> getAllQuestionsForMovie(long movieId) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(movieId);
        MovieEntity movieEntity = movieEntityOptional.orElseThrow(NoSuchElementException::new);
        List<QuestionEntity> questionEntities = questionRepository.findAllByMovie(movieEntity);
        List<Question> questions = QuestionMapper.QUESTION_MAPPER.fromEntitiesList(questionEntities);
        return questions;
    }

    @Override
    public boolean createQuestion(Question question) {
        QuestionEntity questionEntity = QuestionMapper.QUESTION_MAPPER.toEntity(question, movieRepository);
        questionRepository.save(questionEntity);
        return true;
    }

    @Override
    public boolean deleteQuestion(long id) {
        Optional<QuestionEntity> questionEntityOptional = questionRepository.findById(id);
        QuestionEntity questionEntity = questionEntityOptional.orElseThrow(NoSuchElementException::new);
        questionRepository.delete(questionEntity);
        return true;
    }

    @Override
    public boolean addAnswersToQuestion(long questionId, Set<Answer> answers) {
        Optional<QuestionEntity> questionEntityOptional = questionRepository.findById(questionId);
        QuestionEntity questionEntity = questionEntityOptional.orElseThrow(NoSuchElementException::new);
        for (Answer answer : answers) {
            boolean isNewAnswer = questionEntity.getAnswers().stream()
                    .noneMatch(ans -> ans.getAnswerContent().toLowerCase().contains(answer.getAnswerContent().toLowerCase()));
            if (isNewAnswer) {
                AnswerEntity answerEntity = AnswerMapper.ANSWER_MAPPER.toEntity(answer);
                questionEntity.getAnswers().add(answerEntity);
            }
        }
        questionRepository.save(questionEntity);
        return true;
    }

    @Override
    public boolean removeAnswersFromQuestion(long questionId, List<Long> answersIds) {
        Optional<QuestionEntity> questionEntityOptional = questionRepository.findById(questionId);
        QuestionEntity questionEntity = questionEntityOptional.orElseThrow(NoSuchElementException::new);
        List<AnswerEntity> answerEntitiesToDelete = new ArrayList<>();
        for (Long id : answersIds) {
            List<AnswerEntity> filteredAnswers = questionEntity.getAnswers().stream()
                    .filter(ans -> ans.getId().equals(id)).collect(Collectors.toList());
            answerEntitiesToDelete.addAll(filteredAnswers);
        }
        questionEntity.getAnswers().removeAll(answerEntitiesToDelete);
        return true;
    }


}
