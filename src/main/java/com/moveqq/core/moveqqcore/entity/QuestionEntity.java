package com.moveqq.core.moveqqcore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity{
    @NotNull
    private String questionContent;

    @NotNull
    @ManyToOne
    private MovieEntity movie;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<AnswerEntity> answers;

    private Integer points = 0;

    private Integer time = 90;

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
