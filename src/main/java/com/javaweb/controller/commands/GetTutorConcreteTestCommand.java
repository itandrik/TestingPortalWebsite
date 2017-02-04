package com.javaweb.controller.commands;

import com.javaweb.model.services.TestService;
import com.javaweb.model.services.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.javaweb.controller.CommandRegexAndPatterns.LETTERS_BEFORE_INDEX_REGEX;

/**
 * @author Andrii Chernysh on 04-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class GetTutorConcreteTestCommand implements Command {
    private TestService testService = TestServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestedURI = request.getRequestURI();

        int testId = Integer.parseInt(requestedURI.replaceAll(LETTERS_BEFORE_INDEX_REGEX, ""));
        return null;
    }
}
