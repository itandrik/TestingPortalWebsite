package com.javaweb.controller.commands;

import com.javaweb.jsp.Attributes;
import com.javaweb.jsp.Pages;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.services.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Andrii Chernysh on 25-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetSubjectsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAttributeListOfSubjects(request);

        return Pages.SUBJECTS_PAGE_WITH_PATH;
    }

    private void setAttributeListOfSubjects(HttpServletRequest request) {
        SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();
        List<Subject> subjectsList = subjectService.getAll();

        request.setAttribute(Attributes.SUBJECTS, subjectsList);
    }
}
