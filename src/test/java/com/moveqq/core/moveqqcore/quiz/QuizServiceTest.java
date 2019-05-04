package com.moveqq.core.moveqqcore.quiz;

import com.moveqq.core.moveqqcore.entity.AnswerEntity;
import com.moveqq.core.moveqqcore.entity.MovieEntity;
import com.moveqq.core.moveqqcore.entity.QuestionEntity;
import com.moveqq.core.moveqqcore.mapper.AnswerMapper;
import com.moveqq.core.moveqqcore.mapper.MovieMapper;
import com.moveqq.core.moveqqcore.mapper.QuestionMapper;
import com.moveqq.core.moveqqcore.model.dto.internal.Answer;
import com.moveqq.core.moveqqcore.model.dto.internal.Movie;
import com.moveqq.core.moveqqcore.model.dto.internal.Question;
import com.moveqq.core.moveqqcore.repository.MovieRepository;
import com.moveqq.core.moveqqcore.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private QuestionRepository questionRepository;

    private Question questionSource;
    private List<Answer> answers;
    private MovieEntity movieEntity;

    @Before
    public void setUp() throws Exception {
        this.questionSource = new Question();
        this.questionSource.setMovieId(60573L);
        this.questionSource.setQuestionContent("Developerow jakich technologii wymienil " +
                                          "Dinesh podczas rozmowy z Richardem?");
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
        questionSource.setAnswers(Arrays.asList(answerA, answerB, answerC, answerD));
        movieEntity = new MovieEntity();
        movieEntity.setTitle("Silicon Valley");
        movieEntity.setOriginalLanguage("English");
    }

    @Test
    public void should_Mapping_Quiz_To_QuizEntity_Using_Map_Struct() {
        when(movieRepository.getOne(questionSource.getMovieId())).thenReturn(movieEntity);
        QuestionEntity questionEntity = QuestionMapper.QUESTION_MAPPER.toEntity(questionSource, movieRepository);
        assertThat(questionEntity).isNotNull();
    }

    @Test
    public void should_Mapping_Answer_To_AnswerEntity_Using_Map_Struct() {
        Answer answerA = new Answer();
        answerA.setAnswerContent("Java, iOS, Android");
        AnswerEntity answerEntity = AnswerMapper.ANSWER_MAPPER.toEntity(answerA);
        assertThat(answerEntity.getAnswerContent()).isEqualTo(answerA.getAnswerContent());
    }

    @Test
    public void should_Mapping_Movie_To_MovieEntity_Using_Map_Struct() throws Exception {
        List<String> genres = Arrays.asList("comedy", "horror");
        Movie movieTest = new Movie.MovieBuilder(1L,"TestMovie")
                .withBudget(1000000)
                .withOverview("Dramatic movie about developer using new library")
                .withGenres(genres)
                .build();
        MovieEntity movieEntityTest = MovieMapper.MOVIE_MAPPER.toEntity(movieTest);
        assertThat(movieEntityTest.getTitle()).isEqualTo("TestMovie");
        assertThat(movieEntityTest.getGenres().get(1).getName()).isEqualTo(genres.get(1));

    }

    @Test
    public void should_Mapping_Question_To_QuestionEntity_Using_Map_Struct() throws Exception {
        when(movieRepository.getOne(questionSource.getMovieId())).thenReturn(movieEntity);
        QuestionEntity questionEntity = QuestionMapper.QUESTION_MAPPER.toEntity(questionSource, movieRepository);
        assertThat(questionEntity.getMovie()).isNotNull();

    }
}