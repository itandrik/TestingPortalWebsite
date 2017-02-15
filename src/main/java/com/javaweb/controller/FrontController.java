package com.javaweb.controller;

import com.javaweb.controller.commands.Command;
import com.javaweb.controller.commands.CommandHolder;
import com.javaweb.exception.ApplicationException;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;
import com.javaweb.util.Paths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.javaweb.controller.CommandRegexAndPatterns.INDEX_ENDING_URI_REGEX;
import static com.javaweb.controller.CommandRegexAndPatterns.INDEX_INSIDE_URI_REGEX;
import static com.javaweb.controller.CommandRegexAndPatterns.USER_ACCOUNT_PAGE_REGEX;
import static com.javaweb.util.Paths.USER_INFO_USERNAME;


public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DELIMITER = ":";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final Logger LOGGER = Logger.getLogger(FrontController.class);
    private CommandHolder commands;

    public FrontController() {
        commands = new CommandHolder();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String viewPage = executeAndCatchExceptions(request,response);
        if(!isRedirected(viewPage)) {
            request.getRequestDispatcher(viewPage)
                    .forward(request, response);
        }
    }

    private String executeAndCatchExceptions(HttpServletRequest request, HttpServletResponse response){
        String viewPage = Pages.INTERNAL_SERVER_ERROR_PAGE;
        try{
            Command command = getCommandFromRequest(request);
            viewPage =  command.execute(request, response);
        } catch (ApplicationException e) {
            request.setAttribute(Attributes.ERROR_MESSAGE,e.getMessage());
            Logger.getLogger(e.getClassThrowsException())
                    .error(e.getLogMessage());
        } catch (IOException | ServletException e) {
            request.setAttribute(Attributes.ERROR_MESSAGE,e.getMessage());
            LOGGER.error(e.getMessage());
        }
        return viewPage;
    }

    private Command getCommandFromRequest(HttpServletRequest request) {
        String key = getKeyForCommand(request);
        return commands.getCommandByKey(key);
    }

    private String getKeyForCommand(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        path = path.replaceAll(INDEX_ENDING_URI_REGEX, ID);
        path = path.replaceAll(USER_ACCOUNT_PAGE_REGEX,USER_INFO_USERNAME);
        path = path.replaceAll(INDEX_INSIDE_URI_REGEX,ID);
        return method + DELIMITER + path;
    }

    private boolean isRedirected(String viewPage){
        return viewPage.equals(Paths.REDIRECTED);
    }
}
