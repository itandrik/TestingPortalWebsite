package com.javaweb.controller;

import com.javaweb.controller.commands.Command;
import com.javaweb.controller.commands.CommandHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DELIMITER = ":";

    private CommandHolder commands;

    public MainController() {
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
        Command command = getCommandFromRequest(request);
        String viewPage = command.execute(request, response);
        request.getRequestDispatcher(viewPage)
                .forward(request, response);
    }

    private Command getCommandFromRequest(HttpServletRequest request) {
        String key = getKeyForCommand(request);
        return commands.getCommandByKey(key);
    }

    private String getKeyForCommand(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        //path = path.replaceAll(".*/rest", "");
        return method + DELIMITER + path;
    }

}
