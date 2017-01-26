package com.javaweb.model.entity.task;

import com.javaweb.model.entity.Answer;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private int id;
    private String question;
    private AnswerType answerType;
    private String explanation;
    private List<Answer> answers;

    public Task() {
        answers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public String getExplanation() {
        return explanation;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Task setId(int id) {
        this.id = id;
        return this;
    }

    public Task setQuestion(String question) {
        this.question = question;
        return this;
    }

    public Task setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
        return this;
    }

    public Task setExplanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (!question.equals(task.question)) return false;
        if (answerType != task.answerType) return false;
        if (explanation != null ? !explanation.equals(task.explanation) : task.explanation != null) return false;
        return answers.equals(task.answers);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + question.hashCode();
        result = 31 * result + answerType.hashCode();
        result = 31 * result + (explanation != null ? explanation.hashCode() : 0);
        result = 31 * result + answers.hashCode();
        return result;
    }

    public static class Builder {
        private Task task = new Task();

        public Builder setId(int id) {
            task.setId(id);
            return this;
        }

        public Builder setQuestion(String question) {
            task.setQuestion(question);
            return this;
        }

        public Builder setAnswerType(AnswerType answerType) {
            task.setAnswerType(answerType);
            return this;
        }

        public Builder setExplanation(String explanation) {
            task.setExplanation(explanation);
            return this;
        }

        public Task build() {
            return task;
        }
    }
}
