package com.javaweb.controller.commands.register;

import com.javaweb.controller.commands.Command;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrii Chernysh on 27-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class RegisterCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return Pages.REGISTER_PAGE;
    }
}
