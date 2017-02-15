package com.javaweb.controller.commands.post;

import com.javaweb.controller.commands.Command;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.services.SubjectService;
import com.javaweb.model.services.impl.SubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.javaweb.util.Parameters.NAME_OF_SUBJECT_PARAMETER;
import static com.javaweb.util.Paths.REDIRECTED;
import static com.javaweb.util.Paths.SUBJECTS;

/**
 * @author Andrii Chernysh on 27-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class PostAddSubjectCommand implements Command {
    private SubjectService subjectService = SubjectServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameOfSubject = request.getParameter(NAME_OF_SUBJECT_PARAMETER);

        Objects.requireNonNull(nameOfSubject);
        Subject subject = new Subject(0,nameOfSubject);
        subjectService.addNewSubject(subject);

        response.sendRedirect(SUBJECTS);
        return REDIRECTED;
    }
}
