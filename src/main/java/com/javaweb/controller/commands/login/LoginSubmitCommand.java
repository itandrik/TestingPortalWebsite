package com.javaweb.controller.commands.login;

import com.javaweb.controller.commands.Command;
import com.javaweb.controller.validator.AuthValidator;
import com.javaweb.controller.validator.Validator;
import com.javaweb.controller.writer.RequestAttributeWriter;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.util.LoginData;
import com.javaweb.model.services.impl.PersonServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Attributes.ERROR_MESSAGE;
import static com.javaweb.util.Attributes.ERROR_VALIDATION_MESSAGE;
import static com.javaweb.util.Attributes.USER;
import static com.javaweb.util.Pages.HOME_PAGE_WITH_PATH;
import static com.javaweb.util.Pages.LOGIN_PAGE_WITH_PATH;
import static com.javaweb.util.Parameters.LOGIN_PARAMETER;
import static com.javaweb.util.Parameters.PASSWORD_PARAMETER;
import static com.javaweb.util.Paths.REDIRECTED;
import static com.javaweb.util.Paths.SUBJECTS;

public class LoginSubmitCommand implements Command {
    private String pageToGo = HOME_PAGE_WITH_PATH;

    private Logger logger = Logger.getLogger(LoginSubmitCommand.class);
    private static final String USER_LOGGED_IN = "User %s logged in!";
    PersonServiceImpl personService = PersonServiceImpl.getInstance();

    private Validator<LoginData> authValidator = new AuthValidator();
    private RequestAttributeWriter attributeWriter;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        attributeWriter = new RequestAttributeWriter(request);
        LoginData loginData = getLoginDataFromRequest(request);

        if (!authValidator.isValid(loginData)) {
            extractAndWriteErrorMessages();
            return LOGIN_PAGE_WITH_PATH;
        }

        Person person = personService.authenticate(loginData);

        attributeWriter.writeToSession(USER, person);
        response.sendRedirect(SUBJECTS);

        logger.info(String.format(USER_LOGGED_IN, person.getLogin()));
        return REDIRECTED;
    }

    private LoginData getLoginDataFromRequest(HttpServletRequest request) {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        return new LoginData(login, password);
    }

    private void extractAndWriteErrorMessages() {
        List<String> errorMessages = authValidator.getErrorMessages();
        List<String> errorValidationMessages =
                authValidator.getErrorValidationMessages();
        attributeWriter.writeToRequest(ERROR_MESSAGE, errorMessages);
        attributeWriter.writeToRequest(
                ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
