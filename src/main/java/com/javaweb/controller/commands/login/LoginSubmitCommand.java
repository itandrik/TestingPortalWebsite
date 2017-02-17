package com.javaweb.controller.commands.login;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.validation.AuthValidator;
import com.javaweb.controller.validation.Validator;
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

import static com.javaweb.util.Attributes.*;
import static com.javaweb.util.Pages.LOGIN_PAGE;
import static com.javaweb.util.Parameters.LOGIN_PARAMETER;
import static com.javaweb.util.Parameters.PASSWORD_PARAMETER;
import static com.javaweb.util.Paths.REDIRECTED;
import static com.javaweb.util.Paths.SUBJECTS;

public class LoginSubmitCommand extends AbstractCommandWrapper<LoginData> {
    private static final Logger LOGGER = Logger.getLogger(LoginSubmitCommand.class);
    private static final String USER_LOGGED_IN = "User %s logged in!";
    private PersonServiceImpl personService = PersonServiceImpl.getInstance();

    private Validator<LoginData> authValidator = new AuthValidator();
    private RequestAttributeWriter attributeWriter;

    public LoginSubmitCommand() {
        super(LOGIN_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        attributeWriter = new RequestAttributeWriter(request);
        LoginData loginData = getDataFromRequest(request);
        writeSpecificDataToRequest(request,loginData);

        if (!authValidator.isValid(loginData)) {
            extractAndWriteErrorMessages();
            return LOGIN_PAGE;
        }

        Person person = personService.authenticate(loginData);
        attributeWriter.writeToSession(USER, person);
        LOGGER.info(String.format(USER_LOGGED_IN, person.getLogin()));

        response.sendRedirect(SUBJECTS);
        return REDIRECTED;
    }

    @Override
    protected LoginData getDataFromRequest(HttpServletRequest request) {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        return new LoginData(login, password);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, LoginData data) {
        attributeWriter.writeToRequest(LOGIN_DATA, data);
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
