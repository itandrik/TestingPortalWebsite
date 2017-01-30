package com.javaweb.controller.commands;

import com.javaweb.controller.validator.NullChecker;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.AnswerService;
import com.javaweb.model.services.impl.AnswerServiceImpl;
import com.javaweb.util.Attributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.javaweb.controller.CommandRegexAndPatterns.ANSWER_BEGINNING_REGEX;
import static com.javaweb.i18n.ErrorMessageKeys.ERROR_EMPTY_ANSWER;
import static com.javaweb.util.Attributes.DISABLED_TASKS;
import static com.javaweb.util.Attributes.USER;
import static com.javaweb.util.Pages.CONCRETE_TEST_PAGE;
import static com.javaweb.util.Parameters.ANSWER_PARAMETER;
import static com.javaweb.util.Parameters.TASK_PARAMETER;


/**
 * @author Andrii Chernysh on 29-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class PostAddAnswerCommand implements Command {
    private AnswerService answerService = AnswerServiceImpl.getInstance();
    private NullChecker<String[]> nullCheckerStringArray =
            obj -> (obj == null) || (obj.length == 0);
    private NullChecker<List<Integer>> nullCheckerIntegerList =
            obj -> (obj == null) || (obj.size() == 0);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Person person = (Person) request.getSession().getAttribute(USER);
        String[] parameterValues = request.getParameterValues(ANSWER_PARAMETER);

        if(nullCheckerStringArray.isEmpty(parameterValues)){
            request.setAttribute(Attributes.ERROR_MESSAGE,ERROR_EMPTY_ANSWER);

            return CONCRETE_TEST_PAGE;
        }

        for(String value : parameterValues){
            int answerId = extractAnswerIdFromParameter(value);
            answerService.insertAnswerForPersonHistory(answerId,person.getId());
        }

        makeTaskDisabled(request);
        return CONCRETE_TEST_PAGE;
    }

    private int extractAnswerIdFromParameter(String answerParameter) {
        answerParameter = answerParameter.replaceAll(ANSWER_BEGINNING_REGEX,"");
        return Integer.parseInt(answerParameter);
    }

    private void makeTaskDisabled(HttpServletRequest request) {
        List<Integer> disabledTasks = (List<Integer>) request.getSession().getAttribute(DISABLED_TASKS);
        if(nullCheckerIntegerList.isEmpty(disabledTasks)){
            disabledTasks = new ArrayList<>();
        }
        int taskId = Integer.parseInt(request.getParameter(TASK_PARAMETER));
        disabledTasks.add(taskId);
        request.getSession().setAttribute(DISABLED_TASKS,disabledTasks);
    }
}
