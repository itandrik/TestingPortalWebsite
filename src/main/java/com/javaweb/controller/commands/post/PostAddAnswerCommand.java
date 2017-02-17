package com.javaweb.controller.commands.post;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.commands.helper.IndexExtractor;
import com.javaweb.controller.validation.NullChecker;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.impl.PersonTestHistoryServiceImpl;
import com.javaweb.util.Attributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.javaweb.i18n.ErrorMessageKeys.ERROR_EMPTY_ANSWER;
import static com.javaweb.util.Attributes.*;
import static com.javaweb.util.Pages.CONCRETE_STUDENT_TEST_PAGE;
import static com.javaweb.util.Parameters.*;


/**
 * @author Andrii Chernysh on 29-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class PostAddAnswerCommand extends AbstractCommandWrapper<Integer> {
    private IndexExtractor indexExtractor = IndexExtractor.getInstance();
    private PersonTestHistoryService personTestHistoryService =
            PersonTestHistoryServiceImpl.getInstance();
    private NullChecker<String[]> nullCheckerStringArray =
            obj -> (obj == null) || (obj.length == 0);
    private NullChecker<List<Integer>> nullCheckerIntegerList =
            obj -> (obj == null) || (obj.size() == 0);

    public PostAddAnswerCommand() {
        super(CONCRETE_STUDENT_TEST_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) request.getSession().getAttribute(USER);
        String[] parameterValues = request.getParameterValues(ANSWER_PARAMETER);
        int taskId = indexExtractor.extractIndexParameterFromRequest(request, TASK_PARAMETER);

        // Validation
        if (nullCheckerStringArray.isEmpty(parameterValues)) {
            request.setAttribute(Attributes.ERROR_MESSAGE, ERROR_EMPTY_ANSWER);
            return CONCRETE_STUDENT_TEST_PAGE;
        }

        for (String value : parameterValues) {
            int answerId = indexExtractor.extractIndexFromParameter(value);
            saveAnswerId(session, answerId);
            personTestHistoryService.insertAnswerForPersonHistory(answerId, person.getId());
        }

        makeTaskDisabled(session, taskId);
        sendTimeRemaining(request);
        return CONCRETE_STUDENT_TEST_PAGE;
    }

    private void sendTimeRemaining(HttpServletRequest request) {
        int timeRemaining = indexExtractor
                .extractIndexParameterFromRequest(request, TIME_REMAINING);
        Test currentTest = (Test) request.getSession().getAttribute(CONCRETE_TEST);
        currentTest.setDurationTimeInMinutes(timeRemaining);
        request.getSession().setAttribute(CONCRETE_TEST, currentTest);
    }

    private void saveAnswerId(HttpSession session, int answerId) {
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

    @Override
    protected Integer getDataFromRequest(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Integer data) {
        throw new UnsupportedOperationException();
    }

}
