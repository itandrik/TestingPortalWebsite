package com.javaweb.controller.commands;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.*;
import com.javaweb.model.services.impl.*;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static com.javaweb.controller.CommandRegexAndPatterns.INDEX_INSIDE_URI_PATTERN;
import static com.javaweb.util.Attributes.USER;

/**
 * @author Andrii Chernysh on 01-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class TestResultCommand implements Command {
    private TestService testService = TestServiceImpl.getInstance();
    private TaskService taskService = TaskServiceImpl.getInstance();
    private AnswerService answerService = AnswerServiceImpl.getInstance();
    private PersonTestHistoryService personTestHistoryService =
            PersonTestHistoryServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int testId = getTestIdFromURI(request);
        Person person = (Person) request.getSession().getAttribute(USER);

        Optional<Test> optionalTest = testService.getTestById(testId);

        optionalTest.ifPresent(test -> {
            List<Task> tasksWithAllAnswers = getTasksWithAnswersForTest(test);
            List<Task> studentTaskAnswersForTest = getStudentTaskAnswersForTest(test);

            request.setAttribute(Attributes.TASKS, tasksWithAllAnswers);
            request.setAttribute(Attributes.STUDENT_TASKS, studentTaskAnswersForTest);


        });
        return Pages.TEST_RESULTS_PAGE;
    }

    private int getTestIdFromURI(HttpServletRequest request) {
        int testId = 0;
        String requestURI = request.getRequestURI();
        Matcher idMatcher = INDEX_INSIDE_URI_PATTERN.matcher(requestURI);
        while (idMatcher.find()) {
            testId = Integer.parseInt(idMatcher.group(1));
        }
        return testId;
    }

    private List<Task> getTasksWithAnswersForTest(Test test) {
        List<Task> tasks = taskService.getAllTasksForTest(test);
        for (Task task : tasks) {
            List<Answer> answers = answerService.getListOfAnswersForTask(task);
            task.setAnswers(answers);
        }
        return tasks;
    }

    private List<Task> getStudentTaskAnswersForTest(Test test) {
        //TODO
        return null;
    }
}
