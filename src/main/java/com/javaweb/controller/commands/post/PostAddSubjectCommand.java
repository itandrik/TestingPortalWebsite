package com.javaweb.controller.commands.post;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.services.SubjectService;
import com.javaweb.model.services.impl.SubjectServiceImpl;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.javaweb.util.Parameters.NAME_OF_SUBJECT_PARAMETER;
import static com.javaweb.util.Paths.REDIRECTED;
import static com.javaweb.util.Paths.SUBJECTS;

/**
 * @author Andrii Chernysh on 27-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class PostAddSubjectCommand extends AbstractCommandWrapper<String> {
    private SubjectService subjectService = SubjectServiceImpl.getInstance();

    public PostAddSubjectCommand() {
        super(Pages.SUBJECTS_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameOfSubject = getDataFromRequest(request);
        Objects.requireNonNull(nameOfSubject);

        Subject subject = new Subject(0, nameOfSubject);
        subjectService.addNewSubject(subject);

        response.sendRedirect(SUBJECTS);
        return REDIRECTED;
    }

    @Override
    protected String getDataFromRequest(HttpServletRequest request) {
        return request.getParameter(NAME_OF_SUBJECT_PARAMETER);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, String data) {
        throw new UnsupportedOperationException();
    }
}
