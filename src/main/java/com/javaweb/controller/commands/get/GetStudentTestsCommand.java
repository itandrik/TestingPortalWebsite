package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.commands.helper.IndexExtractor;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.SubjectService;
import com.javaweb.model.services.impl.PersonTestHistoryServiceImpl;
import com.javaweb.model.services.impl.SubjectServiceImpl;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Attributes.*;

/**
 * @author Andrii Chernysh on 10-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetStudentTestsCommand extends AbstractCommandWrapper {
    private PersonTestHistoryService personTestHistoryService =
            PersonTestHistoryServiceImpl.getInstance();
    private SubjectService subjectService = SubjectServiceImpl.getInstance();
    private IndexExtractor indexExtractor = IndexExtractor.getInstance();

    public GetStudentTestsCommand(){
        super(Pages.INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person student = new Person.Builder()
                .setId(indexExtractor.extractIndexInsideUriFromRequest(request))
                .build();

        List<Test> testsPassedByStudent =
                personTestHistoryService.getListOfTestsPassedByStudent(student);
        List<Subject> subjects = subjectService.getAll();

        request.setAttribute(TESTS_PASSED_BY_STUDENT, testsPassedByStudent);
        request.setAttribute(SUBJECTS, subjects);
        request.setAttribute(CONCRETE_STUDENT, student);
        return Pages.STUDENTS_TESTS_LIST_PAGE;
    }

    @Override
    protected Object getDataFromRequest(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Object data) {
        throw new UnsupportedOperationException();
    }
}
