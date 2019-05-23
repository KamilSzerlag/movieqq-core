package com.moveqq.core.moveqqcore.entity;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answers")
public class AnswerEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private QuestionEntity question;

    @NotNull
    private String content;

    private Boolean isCorrect = false;

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnswerEntity that = (AnswerEntity) o;
        return Objects.equal(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), content);
    }
}
