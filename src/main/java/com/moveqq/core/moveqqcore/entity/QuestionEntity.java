package com.moveqq.core.moveqqcore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity{
    @NotNull
    private String content;

    @NotNull
    @ManyToOne
    private MovieEntity movie;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
    private Set<AnswerEntity> answers;

    private Integer points = 0;

    private Integer time = 90;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public Set<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerEntity> answers) {
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
