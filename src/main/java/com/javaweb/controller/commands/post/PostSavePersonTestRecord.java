package com.javaweb.controller.commands.post;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.impl.PersonTestHistoryServiceImpl;
import com.javaweb.util.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static com.javaweb.util.Attributes.CONCRETE_TEST;
import static com.javaweb.util.Attributes.USER;
import static com.javaweb.util.Pages.INTERNAL_SERVER_ERROR_PAGE;
import static com.javaweb.util.Paths.REDIRECTED;

/**
 * @author Andrii Chernysh on 29-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class PostSavePersonTestRecord extends AbstractCommandWrapper{
    private PersonTestHistoryService personTestHistoryService =
            PersonTestHistoryServiceImpl.getInstance();

    public PostSavePersonTestRecord() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = (Person) request.getSession().getAttribute(USER);
        Test test = (Test) request.getSession().getAttribute(CONCRETE_TEST);
        personTestHistoryService.insertTestHistoryPassedByPerson(test,person);

        response.sendRedirect(Paths.TESTS + Paths.STUDENT + "/" +
                test.getId() + Paths.RESULTS + "/" + person.getId());
        setAllAttributesInSessionNull(request);
        return REDIRECTED;
    }

    private void setAllAttributesInSessionNull(HttpServletRequest request){
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        Object userAttribute = request.getSession().getAttribute(USER);
        while(attributeNames.hasMoreElements()){
            request.getSession().setAttribute(attributeNames.nextElement(),null);
        }
        request.getSession().setAttribute(USER,userAttribute);
    }

    @Override
    protected Object getDataFromRequest(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Object data) {
        throw new UnsupportedOperationException();
    }
}
