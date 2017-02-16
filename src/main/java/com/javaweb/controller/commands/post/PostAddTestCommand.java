package com.javaweb.controller.commands.post;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.commands.helper.IndexExtractor;
import com.javaweb.controller.validation.NullChecker;
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

import static com.javaweb.i18n.ErrorMessageKeys.ERROR_EMPTY_NAME_FIELD;
import static com.javaweb.util.Attributes.ERROR_VALIDATION_MESSAGE;
import static com.javaweb.util.Parameters.DURATION_TIME_PARAMETER;
import static com.javaweb.util.Paths.REDIRECTED;

/**
 * @author Andrii Chernysh on 04-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class PostAddTestCommand extends AbstractCommandWrapper<Test> {
    private Logger logger = Logger.getLogger(PostAddTestCommand.class);
    private IndexExtractor indexExtractor = IndexExtractor.getInstance();
    private TestService testService = TestServiceImpl.getInstance();
    private NullChecker<String> nullChecker =
            (testName) -> (testName == null) || testName.isEmpty();
    private static final String EMPTY_NAME_FIELD_LOG =
            "User leave empty name field while adding test";

    public PostAddTestCommand() {
        super(Pages.INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Test test = getDataFromRequest(request);

        if (nullChecker.isEmpty(test.getNameOfTest())) {
            writeSpecificDataToRequest(request, test);
            return Pages.TESTS_PAGE;
        }

        testService.addTest(test);
        response.sendRedirect(request.getRequestURI());
        return REDIRECTED;
    }

    @Override
    protected Test getDataFromRequest(HttpServletRequest request) {
        String testName = request.getParameter(Parameters.NAME_OF_TEST_PARAMETER);
        int subjectId = indexExtractor.extractLastPartUriIndexFromRequest(request);
        int durationTimeInMinutes = indexExtractor
                .extractIndexParameterFromRequest(request, DURATION_TIME_PARAMETER);

        return new Test.Builder()
                .setName(testName)
                .setSubjectId(subjectId)
                .setDurationTimeInMinutes(durationTimeInMinutes)
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Test test) {
        request.setAttribute(ERROR_VALIDATION_MESSAGE, ERROR_EMPTY_NAME_FIELD);
        logger.error(EMPTY_NAME_FIELD_LOG);
        List<Test> testsList = testService.getAllTestsForSubjectWithId(test.getSubjectId());
        request.setAttribute(Attributes.TESTS, testsList);
    }

}
