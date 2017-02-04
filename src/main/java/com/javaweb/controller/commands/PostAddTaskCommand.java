package com.javaweb.controller.commands;

import com.javaweb.model.services.TaskService;
import com.javaweb.model.services.impl.TaskServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrii Chernysh on 04-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class PostAddTaskCommand implements Command {
    private TaskService taskService = TaskServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(PostAddTaskCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        return null;
    }
}
