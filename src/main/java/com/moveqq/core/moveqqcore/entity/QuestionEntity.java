package com.moveqq.core.moveqqcore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity{

    @ManyToOne
    private MovieEntity movie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<AnswerEntity> answers;

    private Integer points;

    private Integer time;

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
