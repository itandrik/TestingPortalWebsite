package com.javaweb.model.dao;

import com.javaweb.model.entity.task.Task;
import com.javaweb.model.entity.Test;

import java.util.List;

public interface TaskDao extends GenericDao<Task> {
	List<Task> getListOfTasksForTest(Test test);
}
