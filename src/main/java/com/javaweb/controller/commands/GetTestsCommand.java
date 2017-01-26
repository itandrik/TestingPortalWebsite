package com.javaweb.controller.commands;

import com.javaweb.jsp.Attributes;
import com.javaweb.jsp.Pages;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;
import com.javaweb.model.services.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Andrii Chernysh on 25-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class GetTestsCommand implements Command {
    private static final String LETTERS_BEFORE_INDEX_REGEX = "\\D+";
    private SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestedURI = request.getRequestURI();

        int subjectId = Integer.parseInt(requestedURI.replaceAll(LETTERS_BEFORE_INDEX_REGEX, ""));
        Subject subject = subjectService.getSubjectById(subjectId);

        setAttributeListOfTests(subject, request);
        return Pages.TESTS_PAGE_WITH_PATH;
    }

    private void setAttributeListOfTests(Subject subject, HttpServletRequest request) {
        List<Test> testsList = subjectService.getAllTestsForSubject(subject);
        request.setAttribute(Attributes.TESTS, testsList);
    }
}