package com.moveqq.core.moveqqcore.quiz;

import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.model.dto.internal.Answer;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {

    @Mock
    private MovieRepository movieRepository;

    private ModelMapper modelMapper;

    private Question questionSource;
    private List<Answer> answers;
    private MovieEntity movieEntity;

    @Before
    public void setUp() throws Exception {
        questionSource = new Question();
        questionSource.setMovieId(60573L);
        questionSource.setQuestionContent("Developerow jakich technologii wymienil " +
                                          "Dinesh podczas rozmowy z Richardem?");
        answers = new ArrayList<>();
        Answer answerA = new Answer();
        Answer answerB = new Answer();
        Answer answerC = new Answer();
        Answer answerD = new Answer();
        answerA.setAnswerContent("Java, iOS, Android");
        answerA.setCorrect(false);
        answerB.setAnswerContent("JavaScript, iOS, Android");
        answerB.setCorrect(true);
        answerC.setAnswerContent("JavaScript, Ruby, Python");
        answerC.setCorrect(false);
        answerD.setAnswerContent("C++, C#, Android, Lisp");
        answerD.setCorrect(false);
        answers.add(answerA);
        answers.add(answerB);
        answers.add(answerC);
        answers.add(answerD);
        questionSource.setAnswers(answers);
        modelMapper = new ModelMapper();
        movieEntity = new MovieEntity();
        movieEntity.setTitle("Silicon Valley");
        movieEntity.setOriginalLanguage("English");
    }

    @Test
    public void should_Mapping_Quiz_To_QuizEntity() {
        when(movieRepository.findMovieEntitiesById(questionSource.getMovieId())).thenReturn(movieEntity);
        ModelMapper modelMapper = modelMapper.addMappings(mapper ->
           mapper.map(src -> movieRepository.findMovieEntitiesById(src.getMovieId()), QuestionEntity::setMovie));
        QuestionEntity questionEntity = modelMapper.map(questionSource, QuestionEntity.class);
        assertThat(questionEntity.getMovie()).isEqualTo(movieEntity);
    }

}