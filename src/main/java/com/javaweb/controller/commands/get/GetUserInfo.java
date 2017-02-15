package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.javaweb.util.Pages.USER_ACCOUNT_PAGE;

/**
 * @author Andrii Chernysh on 29-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetUserInfo implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        return USER_ACCOUNT_PAGE;
    }
}
