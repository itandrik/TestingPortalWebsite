package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.Command;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonService;
import com.javaweb.model.services.impl.PersonServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Andrii Chernysh on 10-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class GetStudentsCommand implements Command {
    private PersonService personService = PersonServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> students = personService.getStudents();
        request.setAttribute(Attributes.STUDENTS,students);

        return Pages.STUDENTS_LIST_PAGE;
    }
}
