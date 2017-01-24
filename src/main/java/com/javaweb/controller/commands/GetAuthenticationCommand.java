package com.javaweb.controller.commands;

import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.impl.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.javaweb.jsp.Attributes.USER;
import static com.javaweb.jsp.Pages.LOGIN_PAGE_WITH_PATH;
import static com.javaweb.jsp.Parameters.LOGIN_PARAMETER;
import static com.javaweb.jsp.Parameters.PASSWORD_PARAMETER;
import static com.javaweb.jsp.Paths.LOGIN;

public class GetAuthenticationCommand implements Command {
	private String pageToGo = LOGIN_PAGE_WITH_PATH;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter(LOGIN_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);

		if (login != null && password != null) {
			PersonServiceImpl personService = PersonServiceImpl.getInstance();
			Optional<Person> person = personService.login(login, password);

			person.ifPresent(innerPerson -> {
				request.getSession().setAttribute(USER, innerPerson);
				pageToGo = LOGIN;
			});
		}
		return pageToGo;
	}

}
