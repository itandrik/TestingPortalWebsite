package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.Command;
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

/**
 * @author Andrii Chernysh on 25-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetTestsCommand implements Command {
    private TestService testService = TestServiceImpl.getInstance();
    private IndexExtractor indexExtractor = IndexExtractor.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int subjectId = indexExtractor.extractLastPartUriIndexFromRequest(request);

        List<Test> testsList = testService.getAllTestsForSubjectWithId(subjectId);
        request.setAttribute(Attributes.TESTS, testsList);
        return Pages.TESTS_PAGE;
    }
}
