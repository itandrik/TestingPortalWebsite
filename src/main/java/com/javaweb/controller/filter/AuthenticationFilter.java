package com.javaweb.controller.filter;

import com.javaweb.controller.commands.Command;
import com.javaweb.controller.commands.login.LoginSubmitCommand;
import com.javaweb.util.Paths;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.person.PersonRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.javaweb.util.Attributes.USER;

public class AuthenticationFilter implements Filter {

	public AuthenticationFilter() {

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		Command command = new LoginSubmitCommand();
		command.execute(httpRequest, httpResponse);

		Person person = (Person) session.getAttribute(USER);

		if (person.getRole().equals(PersonRole.STUDENT)) {
			//httpResponse.sendRedirect(Paths.SUBJECTS);
			request.getRequestDispatcher(Paths.SUBJECTS).forward(request,response);
		} else if (person.getRole().equals(PersonRole.TUTOR)) {
			//httpResponse.sendRedirect(TUTOR);
		}

		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
