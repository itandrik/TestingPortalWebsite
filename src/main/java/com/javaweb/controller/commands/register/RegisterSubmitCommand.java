package com.javaweb.controller.commands.register;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.validator.RegistrationValidator;
import com.javaweb.controller.validator.Validator;
import com.javaweb.model.entity.person.Gender;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.person.PersonRole;
import com.javaweb.model.security.BCryptEncryptor;
import com.javaweb.model.services.PersonService;
import com.javaweb.model.services.impl.PersonServiceImpl;
import com.javaweb.util.Paths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Attributes.*;
import static com.javaweb.util.Pages.REGISTER_PAGE;
import static com.javaweb.util.Parameters.*;
import static com.javaweb.util.Paths.LOGIN;

/**
 * @author Andrii Chernysh on 27-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class RegisterSubmitCommand extends AbstractCommandWrapper<Person> {
    private PersonService personService = PersonServiceImpl.getInstance();
    private Validator<Person> validator = new RegistrationValidator();

    private static final Logger LOGGER = Logger.getLogger(RegisterSubmitCommand.class);
    private static final String USER_SIGNED_UP = "User %s created new account!";

    public RegisterSubmitCommand() {
        super(REGISTER_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Person person = getDataFromRequest(request);
        writePreviousDataToRequest(request,person);

        if(!validator.isValid(person)){
            extractAndWriteErrorMessagesToRequest(request);
            return REGISTER_PAGE;
        }

        encryptPersonPassword(person);
        personService.register(person);

        LOGGER.info(String.format(USER_SIGNED_UP,person.getLogin()));
        response.sendRedirect(LOGIN);
        return Paths.REDIRECTED;
    }

    @Override
    protected Person getDataFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter(FIRST_NAME_PARAMETER);
        String secondName = request.getParameter(SECOND_NAME_PARAMETER);
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        Gender gender = Gender.getGenderFromString(request.getParameter(GENDER_PARAMETER))
                .orElse(null);
        PersonRole role = PersonRole.getRoleFromString(request.getParameter(PERSON_ROLE_PARAMETER))
                .orElse(null);

        return new Person.Builder()
                .setFirstName(firstName)
                .setSecondName(secondName)
                .setGender(gender)
                .setLogin(login)
                .setPassword(password)
                .setRole(role)
                .build();
    }

    @Override
    protected void writePreviousDataToRequest(HttpServletRequest request, Person person) {
        request.setAttribute(PREVIOUS_PERSON, person);
    }

    private void encryptPersonPassword(Person person) {
        BCryptEncryptor encryptor = new BCryptEncryptor();
        person.setPassword(encryptor.encryptPassword(person.getPassword()));
    }

    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {
        List<String> errorMessages = validator.getErrorMessages();
        List<String> errorValidationMessages =
                validator.getErrorValidationMessages();
        request.setAttribute(ERROR_MESSAGE, errorMessages);
        request.setAttribute(ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
