package com.javaweb.controller.filter;

import com.javaweb.util.Attributes;
import com.javaweb.util.Paths;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

	public AuthFilter() {

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		boolean isUserEmpty = session.getAttribute(Attributes.USER) == null;
		boolean isHomePage = httpRequest.getRequestURI().startsWith(Paths.HOME);
		boolean isLoginPage = httpRequest.getRequestURI().startsWith(Paths.LOGIN);
		boolean isRegistrationPage = httpRequest.getRequestURI().startsWith(Paths.REGISTER);
		if(isUserEmpty && !isHomePage && !isLoginPage && !isRegistrationPage){
			httpResponse.sendRedirect(Paths.HOME);
		}

		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
