package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.services.impl.SubjectServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Pages.INTERNAL_SERVER_ERROR_PAGE;

/**
 * @author Andrii Chernysh on 25-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetSubjectsCommand extends AbstractCommandWrapper {
    private SubjectServiceImpl subjectService = SubjectServiceImpl.getInstance();

    public GetSubjectsCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Subject> subjectsList = subjectService.getAll();
        request.setAttribute(Attributes.SUBJECTS, subjectsList);

        return Pages.SUBJECTS_PAGE;    }

    @Override
    protected Object getDataFromRequest(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Object data) {
        throw new UnsupportedOperationException();
    }


}
