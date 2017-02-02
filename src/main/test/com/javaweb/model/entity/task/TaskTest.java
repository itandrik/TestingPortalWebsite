package com.javaweb.model.entity.task;

import com.javaweb.model.entity.Answer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Andrii Chernysh on 02-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class TaskTest {
    private Task task;
    private static final String FIRST_QUESTION = "1.To be";
    private static final String SECOND_QUESTION = "2.Not to be";
    private static final String THIRD_QUESTION = "3.To be something that I used to know";
    private static final String EXPECTED_QUESTION = "To be or not to be";
    private static final String EXPECTED_EXPLANATION = "Some explanation";

    @Before
    public void setUp() {
        task = initTask();
    }

    private Task initTask() {
        List<Answer> answers = initAnswers();
        return new Task.Builder()
                .setId(1)
                .setAnswerType(AnswerType.MANY_ANSWERS)
                .setExplanation(EXPECTED_EXPLANATION)
                .setQuestion(EXPECTED_QUESTION)
                .setAnswers(answers)
                .build();
    }

    private List<Answer> initAnswers() {
        return Arrays.asList(
                new Answer.Builder().setAnswerText(FIRST_QUESTION).setId(1).build(),
                new Answer.Builder().setAnswerText(SECOND_QUESTION).setId(2).build(),
                new Answer.Builder().setAnswerText(THIRD_QUESTION).setId(3).build());
    }

    @Test
    public void equalsTest() {
        Task anotherTask = initTask();
        Assert.assertEquals("Equals method is not correct", task, anotherTask);
    }
}
