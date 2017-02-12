package com.javaweb.model.entity.history;

import java.util.Optional;

/**
 * @author Andrii Chernysh on 01-Feb-17. E-Mail : itcherry97@gmail.com
 */
public enum Grade {
    A(95, 100),
    B(85, 94),
    C(75, 84),
    D(65, 74),
    E(60, 64),
    FX(0, 59);

    private int lowBound;
    private int highBound;
    private double numberGrade;
    private int countOfAllCorrectAnswers;
    private int countOfPassedCorrectAnswers;

    Grade(int lowBound, int highBound) {
        this.lowBound = lowBound;
        this.highBound = highBound;
    }

    private void setNumberGrade(double numberGrade) {
        this.numberGrade = numberGrade;
    }

    public double getNumberGrade() {
        return numberGrade;
    }

    public int getCountOfAllCorrectAnswers() {
        return countOfAllCorrectAnswers;
    }

    public void setCountOfAllCorrectAnswers(int countOfAllCorrectAnswers) {
        this.countOfAllCorrectAnswers = countOfAllCorrectAnswers;
    }

    public int getCountOfPassedCorrectAnswers() {
        return countOfPassedCorrectAnswers;
    }

    public void setCountOfPassedCorrectAnswers(int countOfPassedCorrectAnswers) {
        this.countOfPassedCorrectAnswers = countOfPassedCorrectAnswers;
    }

    public static Optional<Grade> getECTSGrade(double numberGrade) {
        Optional<Grade> result = Optional.empty();
        numberGrade = Math.round(numberGrade);
        for (Grade grade : Grade.values()) {
            if ((numberGrade >= grade.lowBound) &&
                    (numberGrade <= grade.highBound)) {
                grade.setNumberGrade(numberGrade);
                result =  Optional.of(grade);
                break;
            }
        }
        return result;
    }

    public static Optional<Grade> getECTSGrade(double numberGrade,
                                               int countOfAllCorrectAnswers,
                                               int countOfPassedCorrectAnswers) {
        Optional<Grade> grade = getECTSGrade(numberGrade);
        return grade.map(innerGrade -> {
            innerGrade.setCountOfPassedCorrectAnswers(countOfPassedCorrectAnswers);
            innerGrade.setCountOfAllCorrectAnswers(countOfAllCorrectAnswers);
            return innerGrade;
        });
    }
}
