package com.javaweb.controller.commands.post;

import com.javaweb.controller.commands.Command;
import com.javaweb.controller.validator.NullChecker;
import com.javaweb.model.entity.Test;
import com.javaweb.model.services.TestService;
import com.javaweb.model.services.impl.TestServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;
import com.javaweb.util.Parameters;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.controller.CommandRegexAndPatterns.LETTERS_BEFORE_INDEX_REGEX;
import static com.javaweb.i18n.ErrorMessageKeys.ERROR_EMPTY_NAME_FIELD;
import static com.javaweb.util.Attributes.ERROR_VALIDATION_MESSAGE;
import static com.javaweb.util.Paths.REDIRECTED;

/**
 * @author Andrii Chernysh on 04-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class PostAddTestCommand implements Command {
    private Logger logger = Logger.getLogger(PostAddTestCommand.class);
    private TestService testService = TestServiceImpl.getInstance();
    private NullChecker<String> nullChecker =
            (testName) -> (testName == null) || testName.isEmpty();
    public static final String EMPTY_NAME_FIELD_LOG =
            "User leave empty name field while adding test";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String testName = request.getParameter(Parameters.NAME_OF_TEST_PARAMETER);
        String requestedURI = request.getRequestURI();
        int subjectId = Integer.parseInt(requestedURI.replaceAll(LETTERS_BEFORE_INDEX_REGEX, ""));
        int durationTimeInMinutes = Integer.parseInt(request.getParameter(Parameters.DURATION_TIME_PARAMETER));

        if(!isValidNameField(request, testName)){
            setTestsToRequest(subjectId, request);
            return Pages.TESTS_PAGE;
        }
        Test test = new Test.Builder()
                .setName(testName)
                .setSubjectId(subjectId)
                .setDurationTimeInMinutes(durationTimeInMinutes)
                .build();

        testService.addTest(test);
        response.sendRedirect(request.getRequestURI());
        return REDIRECTED;
    }

    private void setTestsToRequest(int subjectId, HttpServletRequest request) {
        List<Test> testsList = testService.getAllTestsForSubjectWithId(subjectId);
        request.setAttribute(Attributes.TESTS, testsList);
    }

    private boolean isValidNameField(HttpServletRequest request, String testName) {
        if(nullChecker.isEmpty(testName)){
            request.setAttribute(ERROR_VALIDATION_MESSAGE, ERROR_EMPTY_NAME_FIELD);
            logger.error(EMPTY_NAME_FIELD_LOG);
            return false;
        }
        return true;
    }
}
