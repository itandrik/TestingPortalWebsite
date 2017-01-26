package com.javaweb.model.entity.task;

public class Task {
    private int id;
    private String question;
    private AnswerType answerType;
    private String explanation;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (getId() != task.getId()) return false;
        if (!getQuestion().equals(task.getQuestion())) return false;
        if (getAnswerType() != task.getAnswerType()) return false;
        return getExplanation() != null ? getExplanation().equals(task.getExplanation()) : task.getExplanation() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getQuestion().hashCode();
        result = 31 * result + getAnswerType().hashCode();
        result = 31 * result + (getExplanation() != null ? getExplanation().hashCode() : 0);
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
