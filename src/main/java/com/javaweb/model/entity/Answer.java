package com.javaweb.model.entity;

public class Answer {
    private int id;
    private String answerText;
    private boolean isCorrect;

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (isCorrect != answer.isCorrect) return false;
        return answerText.equals(answer.answerText);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + answerText.hashCode();
        result = 31 * result + (isCorrect ? 1 : 0);
        return result;
    }

    public static class Builder{
        private Answer answer = new Answer();

        public Builder setId(int id) {
            answer.setId(id);
            return this;
        }

        public Builder setAnswerText(String answerText) {
            answer.setAnswerText(answerText);
            return this;
        }

        public Builder setIsCorrect(boolean isCorrect) {
            answer.setIsCorrect(isCorrect);
            return this;
        }

        public Answer build(){
            return answer;
        }
    }
}
