package com.javaweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.controller.command.Command;


public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String , Command> commands = new HashMap<>();
    
    public MainController() {
        super();
    }
	
    @Override
    public void init() throws ServletException {
    	super.init();
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request,response);
	}
	
	void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
