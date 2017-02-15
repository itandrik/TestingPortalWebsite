package com.javaweb.controller.commands.post;

import com.javaweb.controller.commands.Command;
import com.javaweb.controller.validator.NullChecker;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.impl.PersonTestHistoryServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Parameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static com.javaweb.controller.CommandRegexAndPatterns.PARAMETER_INDEX_ENDING_PATTERN;
import static com.javaweb.i18n.ErrorMessageKeys.ERROR_EMPTY_ANSWER;
import static com.javaweb.util.Attributes.*;
import static com.javaweb.util.Pages.CONCRETE_STUDENT_TEST_PAGE;
import static com.javaweb.util.Parameters.ANSWER_PARAMETER;
import static com.javaweb.util.Parameters.TASK_PARAMETER;


/**
 * @author Andrii Chernysh on 29-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class PostAddAnswerCommand implements Command {
    private PersonTestHistoryService personTestHistoryService =
            PersonTestHistoryServiceImpl.getInstance();
    private NullChecker<String[]> nullCheckerStringArray =
            obj -> (obj == null) || (obj.length == 0);
    private NullChecker<List<Integer>> nullCheckerIntegerList =
            obj -> (obj == null) || (obj.size() == 0);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) request.getSession().getAttribute(USER);

        int taskId = Integer.parseInt(request.getParameter(TASK_PARAMETER));
        makeTaskDisabled(session, taskId);

        String[] parameterValues = request.getParameterValues(ANSWER_PARAMETER);

        if (nullCheckerStringArray.isEmpty(parameterValues)) {
            request.setAttribute(Attributes.ERROR_MESSAGE, ERROR_EMPTY_ANSWER);
            return CONCRETE_STUDENT_TEST_PAGE;
        }

        for (String value : parameterValues) {
            int answerId = extractAnswerIdFromParameter(value);
            saveAnswerId(session, answerId);
            personTestHistoryService.insertAnswerForPersonHistory(answerId, person.getId());
        }

        sendTimeRemaining(request);
        return CONCRETE_STUDENT_TEST_PAGE;
    }


    private void sendTimeRemaining(HttpServletRequest request) {
        int timeRemaining = Integer.parseInt(request.getParameter(Parameters.TIME_REMAINING));
        Test currentTest = (Test) request.getSession().getAttribute(CONCRETE_TEST);
        currentTest.setDurationTimeInMinutes(timeRemaining);
        request.getSession().setAttribute(CONCRETE_TEST, currentTest);
    }

    private int extractAnswerIdFromParameter(String answerParameter) {
        int answerId = 0;
        Matcher idMatcher = PARAMETER_INDEX_ENDING_PATTERN.matcher(answerParameter);
        if (idMatcher.find()) {
            answerId = Integer.parseInt(idMatcher.group(0));
        }
        return answerId;
    }

    private void saveAnswerId(HttpSession session, int answerId){
        List<Integer> savedAnswers = (List<Integer>) session.getAttribute(SAVED_ANSWERS);
        if (nullCheckerIntegerList.isEmpty(savedAnswers)) {
            savedAnswers = new ArrayList<>();
        }
        savedAnswers.add(answerId);
        session.setAttribute(SAVED_ANSWERS, savedAnswers);
    }

    private void makeTaskDisabled(HttpSession session, int taskId) {
        List<Integer> disabledTasks = (List<Integer>) session.getAttribute(DISABLED_TASKS);
        if (nullCheckerIntegerList.isEmpty(disabledTasks)) {
            disabledTasks = new ArrayList<>();
        }
        disabledTasks.add(taskId);
        session.setAttribute(DISABLED_TASKS, disabledTasks);
    }


}
