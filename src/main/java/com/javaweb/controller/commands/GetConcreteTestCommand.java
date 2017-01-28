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
import static com.javaweb.util.Attributes.TASKS;
import static com.javaweb.util.Attributes.TEST_TIME_DURATION;
import static com.javaweb.util.Pages.CONCRETE_TEST_PAGE_WITH_PATH;
import static com.javaweb.util.Pages.HOME_PAGE_WITH_PATH;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class GetConcreteTestCommand implements Command {
    private final TestService testService = TestServiceImpl.getInstance();
    private final TaskService taskService = TaskServiceImpl.getInstance();
    private final AnswerService answerService = AnswerServiceImpl.getInstance();

    private String pageToGo = HOME_PAGE_WITH_PATH;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestedURI = request.getRequestURI();

        int testId = Integer.parseInt(requestedURI.replaceAll(LETTERS_BEFORE_INDEX_REGEX, ""));
        Optional<Test> optionalTest = testService.getTestById(testId);

        optionalTest.ifPresent((test) -> {
            List<Task> tasks = getTasksWithAnswersForTest(test);
            request.setAttribute(TASKS,tasks);
            request.setAttribute(TEST_TIME_DURATION,test.getDurationTimeInMinutes());
            pageToGo = CONCRETE_TEST_PAGE_WITH_PATH;
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
}
