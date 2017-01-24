package com.javaweb.model.dao;

import java.util.List;
import com.javaweb.model.entity.Task;

public interface TestDao extends GenericDao<TestDao>{
	List<Task> getListOfTasksForTest(long id);
}
