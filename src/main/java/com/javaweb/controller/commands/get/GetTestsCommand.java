package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.commands.helper.IndexExtractor;
import com.javaweb.model.entity.Test;
import com.javaweb.model.services.TestService;
import com.javaweb.model.services.impl.TestServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Pages.INTERNAL_SERVER_ERROR_PAGE;

/**
 * @author Andrii Chernysh on 25-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetTestsCommand extends AbstractCommandWrapper<List<Test>> {
    private TestService testService = TestServiceImpl.getInstance();
    private IndexExtractor indexExtractor = IndexExtractor.getInstance();

    public GetTestsCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Test> testsList = getDataFromRequest(request);
        writeSpecificDataToRequest(request,testsList);

        return Pages.TESTS_PAGE;
    }

    @Override
    protected List<Test> getDataFromRequest(HttpServletRequest request) {
        int subjectId = indexExtractor.extractLastPartUriIndexFromRequest(request);

        return testService.getAllTestsForSubjectWithId(subjectId);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, List<Test> testsList) {
        request.setAttribute(Attributes.TESTS, testsList);
    }
}
