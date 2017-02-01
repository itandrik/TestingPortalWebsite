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

    Grade(int lowBound, int highBound) {
        this.lowBound = lowBound;
        this.highBound = highBound;
    }

    public void setNumberGrade(double numberGrade) {
        this.numberGrade = numberGrade;
    }

    public double getNumberGrade() {
        return numberGrade;
    }

    public static Optional<Grade> getECTSGrade(double numberGrade) {
        Optional<Grade> result = Optional.empty();
        numberGrade = Math.round(numberGrade);
        for (Grade grade : Grade.values()) {
            if ((numberGrade >= grade.lowBound) &&
                    (numberGrade <= grade.highBound)) {
                grade.setNumberGrade(numberGrade);
                result =  Optional.of(grade);
            }
        }
        return result;
    }
}
