package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.Command;
import com.javaweb.controller.exception.ControllerException;
import com.javaweb.i18n.ErrorMessageKeys;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.history.PersonHistory;
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
import java.util.function.Function;
import java.util.regex.Matcher;

import static com.javaweb.controller.CommandRegexAndPatterns.INDEX_INSIDE_URI_PATTERN;

/**
 * @author Andrii Chernysh on 01-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class GetTestResultCommand implements Command {
    private TestService testService = TestServiceImpl.getInstance();
    private TaskService taskService = TaskServiceImpl.getInstance();
    private AnswerService answerService = AnswerServiceImpl.getInstance();
    private PersonTestHistoryService personTestHistoryService =
            PersonTestHistoryServiceImpl.getInstance();
    private PersonService personService = PersonServiceImpl.getInstance();
    private static final String LOGGER_INCORRECT_URI =
            "URI %s is incorrect[" + GetTestResultCommand.class.getName() + "]";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        Matcher idMatcher = INDEX_INSIDE_URI_PATTERN.matcher(requestURI);
        int testId = extractIdFromRequest(idMatcher,requestURI);
        int personId = extractIdFromRequest(idMatcher,requestURI);

        Test test = testService.getTestById(testId);
        Person person = personService.getPersonById(personId);

        List<Task> tasksWithAllAnswers = getAllTasksWithAnswersForTest(
                test, task -> answerService.getListOfAnswersForTask(task));

        List<Task> studentTaskAnswersForTest = getAllTasksWithAnswersForTest(
                test, task -> personTestHistoryService.getListOfAnswersByPersonForTask(person, task));
        PersonHistory personHistory = personTestHistoryService.getPersonHistoryForTest(person,test);

        request.setAttribute(Attributes.PERSON_HISTORY,personHistory);
        request.setAttribute(Attributes.TASKS, tasksWithAllAnswers);
        request.setAttribute(Attributes.STUDENT_TASKS, studentTaskAnswersForTest);

        return Pages.TEST_RESULTS_PAGE;
    }

    private int extractIdFromRequest(Matcher matcher, String requestURI){
        int result;
        if(matcher.find()) {
            result = Integer.parseInt(matcher.group(0));
        } else {
            throw new ControllerException()
                    .addMessage(ErrorMessageKeys.ERROR_INCORRECT_URI)
                    .addLogMessage(String.format(LOGGER_INCORRECT_URI,requestURI));
        }
        return result;
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
