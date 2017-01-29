package com.javaweb.controller.commands;

import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.AnswerService;
import com.javaweb.model.services.impl.AnswerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static com.javaweb.controller.CommandRegexAndPatterns.ANSWER_BEGINNING_REGEX;
import static com.javaweb.util.Attributes.USER;
import static com.javaweb.util.Pages.CONCRETE_TEST_PAGE;


/**
 * @author Andrii Chernysh on 29-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class PostAddAnswerCommand implements Command {
    private AnswerService answerService = AnswerServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Person person = (Person) request.getSession().getAttribute(USER);

        Enumeration<String> answerParameters = request.getParameterNames();
        while(answerParameters.hasMoreElements()){
            int answerId = extractAnswerIdFromParameter(answerParameters.nextElement());
            answerService.insertAnswerForPersonHistory(answerId,person.getId());
        }

        return CONCRETE_TEST_PAGE;
    }

    private int extractAnswerIdFromParameter(String answerParameter) {
        answerParameter = answerParameter.replaceAll(ANSWER_BEGINNING_REGEX,"");
        return Integer.parseInt(answerParameter);
    }
}
