package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

import static com.javaweb.util.Paths.HOME;
import static com.javaweb.util.Paths.REDIRECTED;

/**
 * @author Andrii Chernysh on 29-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class GetLogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        clearAllSessionAttributes(request);

        response.sendRedirect(HOME);
        return REDIRECTED;
    }

    private void clearAllSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributes = session.getAttributeNames();
        while(sessionAttributes.hasMoreElements()){
            session.setAttribute(sessionAttributes.nextElement(),null);
        }
    }
}
