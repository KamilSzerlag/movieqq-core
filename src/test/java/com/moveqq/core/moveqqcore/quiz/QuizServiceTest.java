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
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
public class QuizServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private QuestionRepository questionRepository;

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
        modelMapper = new ModelMapper();
        movieEntity = new MovieEntity();
        movieEntity.setTitle("Silicon Valley");
        movieEntity.setOriginalLanguage("English");
    }

    @Test
    public void should_Mapping_Quiz_To_QuizEntity() {
        when(movieRepository.findMovieEntityById(questionSource.getMovieId())).thenReturn(movieEntity);
        TypeMap<Question, QuestionEntity> typeMap = modelMapper.createTypeMap(Question.class, QuestionEntity.class);
        typeMap.addMappings(mapper -> mapper.map(src -> src.getMovieId(), QuestionEntity::setMovie));
        QuestionEntity questionEntity = modelMapper.map(questionSource, QuestionEntity.class);
        assertThat(questionEntity.getMovie()).isEqualTo(movieEntity);
    }

    @Test
    public void should_Mapping_Quiz_To_QuizEntity_Using_Map_Struct() {
        when(movieRepository.findMovieEntityById(questionSource.getMovieId())).thenReturn(movieEntity);
        QuestionEntity questionEntity = QuestionMapper.QUESTION_MAPPER.toEntity(questionSource, questionRepository);
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
    public void should_Mapping_Movie_To_MovieEnitity_Using_Map_Struct() throws Exception{
        List<String> genres = Arrays.asList("comedy", "horror");
        Movie movieTest = new Movie.MovieBuilder(1L,"TestMovie")
                .withBudget(1000000)
                .withOverview("Dramatic movie about developer using new library")
                .withGenres(genres)
                .build();
        MovieEntity movieEntityTest = MovieMapper.MOVIE_MAPPER.toEntity(movieTest, movieRepository);
        assertThat(movieEntityTest.getTitle()).isEqualTo("TestMovie");
        assertThat(movieEntityTest.getGenres().get(1).getName()).isEqualTo(genres.get(1));

    }
}