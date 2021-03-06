package com.javaweb.model.services.impl;

import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.TaskDao;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.TaskService;

import java.util.List;

/**
 * @author Andrii Chernysh on 26-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class TaskServiceImpl implements TaskService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder{
        static final TaskServiceImpl INSTANCE = new TaskServiceImpl();
    }

    public static TaskServiceImpl getInstance() {
        return TaskServiceImpl.Holder.INSTANCE;
    }

    private TaskServiceImpl() {
    }

    @Override
    public List<Task> getAllTasksForTest(Test test) {
        List<Task> result;

        try(DaoConnection connection = daoFactory.getConnection()){
            TaskDao taskDao = daoFactory.createTaskDao(connection);
            result = taskDao.getListOfTasksForTest(test);
        }
        return result;
    }

    @Override
    public int addTask(Task task) {
        try(DaoConnection connection = daoFactory.getConnection()){
            TaskDao dao = daoFactory.createTaskDao(connection);
            return dao.insert(task);
        }
    }

    @Override
    public void assignTaskToTest(int taskId, int testId) {
        try(DaoConnection connection = daoFactory.getConnection()){
            TaskDao dao = daoFactory.createTaskDao(connection);
            dao.assignTaskToTest(taskId,testId);
        }
    }

    @Override
    public int updateTask(Task task) {
        try(DaoConnection connection = daoFactory.getConnection()){
            TaskDao dao = daoFactory.createTaskDao(connection);
            return dao.update(task);
        }
    }
}
