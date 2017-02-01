package com.javaweb.controller.commands;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.AnswerService;
import com.javaweb.model.services.TaskService;
import com.javaweb.model.services.TestService;
import com.javaweb.model.services.impl.AnswerServiceImpl;
import com.javaweb.model.services.impl.TaskServiceImpl;
import com.javaweb.model.services.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.javaweb.controller.CommandRegexAndPatterns.LETTERS_BEFORE_INDEX_REGEX;
import static com.javaweb.util.Attributes.*;
import static com.javaweb.util.Pages.*;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class GetConcreteTestCommand implements Command {
    private final TestService testService = TestServiceImpl.getInstance();
    private final TaskService taskService = TaskServiceImpl.getInstance();
    private final AnswerService answerService = AnswerServiceImpl.getInstance();

    private String pageToGo = HOME_PAGE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestedURI = request.getRequestURI();

        int testId = Integer.parseInt(requestedURI.replaceAll(LETTERS_BEFORE_INDEX_REGEX, ""));
        Optional<Test> optionalTest = testService.getTestById(testId);

        optionalTest.ifPresent((test) -> {
            List<Task> tasks = getTasksWithAnswersForTest(test);
            request.getSession().setAttribute(TASKS,tasks);
            test.setDurationTimeInMinutes(test.getDurationTimeInMinutes() * 60);
            request.getSession().setAttribute(CONCRETE_TEST,test);
            pageToGo = CONCRETE_TEST_PAGE;
        });

        return pageToGo;
    }

    private List<Task> getTasksWithAnswersForTest(Test test) {
        List<Task> tasks = taskService.getAllTasksForTest(test);
        for (Task task : tasks) {
            List<Answer> answers = answerService.getListOfAnswersForTask(task);
            task.setAnswers(answers);
        }
        return tasks;
    }

    private int getTimeDurationInSeconds(Test test) {
        return test.getDurationTimeInMinutes() * 60;
    }
}
