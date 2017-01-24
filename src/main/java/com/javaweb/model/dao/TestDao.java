package com.javaweb.model.dao;

import com.javaweb.model.entity.Task;
import com.javaweb.model.entity.Test;

import java.util.List;

public interface TestDao extends GenericDao<Test>{
	List<Task> getListOfTasksForTest(long id);
}
