package com.javaweb.controller.commands;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.AnswerService;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.TaskService;
import com.javaweb.model.services.TestService;
import com.javaweb.model.services.impl.AnswerServiceImpl;
import com.javaweb.model.services.impl.PersonTestHistoryServiceImpl;
import com.javaweb.model.services.impl.TaskServiceImpl;
import com.javaweb.model.services.impl.TestServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
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
        Test test = testService.getTestById(testId);
        Person person = (Person) request.getSession().getAttribute(USER);

        List<Task> tasksWithAllAnswers = getAllTasksWithAnswersForTest(
                test, task -> answerService.getListOfAnswersForTask(task));

        List<Task> studentTaskAnswersForTest = getAllTasksWithAnswersForTest(
                test, task -> personTestHistoryService.getListOfAnswersByPersonForTask(person, task));

        request.setAttribute(Attributes.TASKS, tasksWithAllAnswers);
        request.setAttribute(Attributes.STUDENT_TASKS, studentTaskAnswersForTest);

        return Pages.TEST_RESULTS_PAGE;
    }

    private int getTestIdFromURI(HttpServletRequest request) {
        int testId = 0;
        String requestURI = request.getRequestURI();
        Matcher idMatcher = INDEX_INSIDE_URI_PATTERN.matcher(requestURI);
        if(idMatcher.find()) {
            testId = Integer.parseInt(idMatcher.group(0));
        }
        return testId;
    }

    private List<Task> getAllTasksWithAnswersForTest(
            Test test, Function<Task, List<Answer>> getAnswersFunction) {
        List<Task> tasks = taskService.getAllTasksForTest(test);

        for (Task task : tasks) {
            List<Answer> answers = getAnswersFunction.apply(task);
            task.setAnswers(answers);
        }
        return tasks;
    }

}
