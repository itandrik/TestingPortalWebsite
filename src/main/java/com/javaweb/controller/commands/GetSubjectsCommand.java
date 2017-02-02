package com.javaweb.controller.commands;

import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;
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
    private SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Subject> subjectsList = subjectService.getAll();
        request.setAttribute(Attributes.SUBJECTS, subjectsList);

        return Pages.SUBJECTS_PAGE;
    }
}
