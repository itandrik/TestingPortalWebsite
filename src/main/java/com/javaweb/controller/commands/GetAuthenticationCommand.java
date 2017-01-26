package com.javaweb.controller.commands;

import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.impl.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static com.javaweb.jsp.Attributes.USER;
import static com.javaweb.jsp.Pages.HOME_PAGE_WITH_PATH;
import static com.javaweb.jsp.Parameters.LOGIN_PARAMETER;
import static com.javaweb.jsp.Parameters.PASSWORD_PARAMETER;
import static com.javaweb.jsp.Paths.REDIRECTED;
import static com.javaweb.jsp.Paths.SUBJECTS;

public class GetAuthenticationCommand implements Command {
    private String pageToGo = HOME_PAGE_WITH_PATH;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        Objects.requireNonNull(login);
        Objects.requireNonNull(password);

        PersonServiceImpl personService = PersonServiceImpl.getInstance();
        Optional<Person> person = personService.login(login, password);

        person.ifPresent(innerPerson -> {
            request.getSession().setAttribute(USER, innerPerson);
            redirectToSubjectsPage(response);
            pageToGo = REDIRECTED;
        });

        return pageToGo;
    }

    private void redirectToSubjectsPage(HttpServletResponse response){
        try {
            response.sendRedirect(SUBJECTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
