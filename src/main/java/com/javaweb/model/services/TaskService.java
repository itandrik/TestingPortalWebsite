package com.javaweb.model.services;

import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.Task;

import java.util.List;

/**
 * @author Andrii Chernysh on 26-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public interface TaskService {
    List<Task> getAllTasksForTest(Test test);
    int addTask(Task task);
}
