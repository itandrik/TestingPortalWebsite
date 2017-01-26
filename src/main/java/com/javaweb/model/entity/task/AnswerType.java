package com.javaweb.model.entity.task;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Andrii Chernysh on 26-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public enum AnswerType {
    ONE_ANSWER,
    MANY_ANSWERS;

    public static Optional<AnswerType> getAnswerTypeFromString(String answerTypeString){
        Optional<AnswerType> type = Arrays.stream(AnswerType.values())
                .filter(elem -> elem.toString().equals(answerTypeString)).findAny();
        return type;
    }
}
