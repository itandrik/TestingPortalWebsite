package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonService;
import com.javaweb.model.services.impl.PersonServiceImpl;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Attributes.STUDENTS;

/**
 * @author Andrii Chernysh on 10-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetStudentsCommand extends AbstractCommandWrapper {
    private PersonService personService = PersonServiceImpl.getInstance();

    public GetStudentsCommand() {
        super(Pages.INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> students = personService.getStudents();
        request.setAttribute(STUDENTS,students);

        return Pages.STUDENTS_LIST_PAGE;
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
