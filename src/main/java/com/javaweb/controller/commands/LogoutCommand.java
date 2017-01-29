package com.javaweb.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.javaweb.util.Attributes.USER;
import static com.javaweb.util.Paths.HOME;
import static com.javaweb.util.Paths.REDIRECTED;

/**
 * @author Andrii Chernysh on 29-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute(USER,null);

        response.sendRedirect(HOME);
        return REDIRECTED;
    }
}
