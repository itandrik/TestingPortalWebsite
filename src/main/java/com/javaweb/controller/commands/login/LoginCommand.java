package com.javaweb.controller.commands.login;

import com.javaweb.controller.commands.Command;
import com.javaweb.model.entity.person.Person;
import com.javaweb.util.Pages;
import com.javaweb.util.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.javaweb.util.Attributes.USER;
import static com.javaweb.util.Paths.REDIRECTED;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Person person = (Person) request.getSession().getAttribute(USER);
        if(person != null){
            response.sendRedirect(Paths.SUBJECTS);
            return REDIRECTED;
        }
        return Pages.LOGIN_PAGE_WITH_PATH;
    }
}
