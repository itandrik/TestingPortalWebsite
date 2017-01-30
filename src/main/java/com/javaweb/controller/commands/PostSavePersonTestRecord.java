package com.javaweb.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static com.javaweb.util.Attributes.DISABLED_TASKS;
import static com.javaweb.util.Attributes.USER;
import static com.javaweb.util.Paths.REDIRECTED;
import static com.javaweb.util.Paths.TESTS;

/**
 * @author Andrii Chernysh on 29-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class PostSavePersonTestRecord implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO create command for inserting User history data
        request.getSession().setAttribute(DISABLED_TASKS,null);

        response.sendRedirect(TESTS);
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
}
