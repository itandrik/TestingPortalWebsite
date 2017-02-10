package com.javaweb.controller.commands;

import com.javaweb.controller.CommandRegexAndPatterns;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.SubjectService;
import com.javaweb.model.services.impl.PersonTestHistoryServiceImpl;
import com.javaweb.model.services.impl.SubjectServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Andrii Chernysh on 10-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class GetStudentTestsCommand implements Command {
    private PersonTestHistoryService personTestHistoryService =
            PersonTestHistoryServiceImpl.getInstance();
    private SubjectService subjectService = SubjectServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Person student = new Person.Builder()
                .setId(extractStudentIdFromRequest(request))
                .build();

        List<Test> testsPassedByStudent =
                personTestHistoryService.getListOfTestsPassedByStudent(student);
        List<Subject> subjects = subjectService.getAll();

        request.setAttribute(Attributes.TESTS_PASSED_BY_STUDENT, testsPassedByStudent);
        request.setAttribute(Attributes.SUBJECTS, subjects);
        return Pages.STUDENTS_TESTS_LIST_PAGE;
    }

    private int extractStudentIdFromRequest(HttpServletRequest request) {
        String requestedURI = request.getRequestURI();
        int studentId = 0;
        Matcher idMatcher = CommandRegexAndPatterns.INDEX_INSIDE_URI_PATTERN.matcher(requestedURI);
        if (idMatcher.find()) {
            studentId = Integer.parseInt(idMatcher.group(0));
        }
        return studentId;
    }
}
