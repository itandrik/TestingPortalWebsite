package com.javaweb.controller.commands.register;

import com.javaweb.controller.commands.Command;
import com.javaweb.util.Paths;
import com.javaweb.model.entity.person.Gender;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.person.PersonRole;
import com.javaweb.model.services.PersonService;
import com.javaweb.model.services.impl.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.javaweb.util.Parameters.*;
import static com.javaweb.util.Paths.LOGIN;

/**
 * @author Andrii Chernysh on 27-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class RegisterSubmitCommand implements Command {
    private PersonService personService = PersonServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Person person = createPersonFromRequest(request);
        personService.register(person);
        response.sendRedirect(LOGIN);
        return Paths.REDIRECTED;
    }

    private Person createPersonFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter(FIRST_NAME_PARAMETER);
        String secondName = request.getParameter(SECOND_NAME_PARAMETER);
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        Gender gender = Gender.getGenderFromString(request.getParameter(GENDER_PARAMETER))
                .orElseThrow(RuntimeException::new);
        PersonRole role = PersonRole.getRoleFromString(request.getParameter(PERSON_ROLE_PARAMETER))
                .orElseThrow(RuntimeException::new);

        return new Person.Builder()
                .setFirstName(firstName)
                .setSecondName(secondName)
                .setGender(gender)
                .setLogin(login)
                .setPassword(password)
                .setRole(role)
                .build();
    }
}
