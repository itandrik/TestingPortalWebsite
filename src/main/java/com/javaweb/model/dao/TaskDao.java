package com.javaweb.model.dao;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Task;

import java.util.List;

public interface TaskDao extends GenericDao<Task> {
	List<Answer> getListOfAnswersForTask(long id);
}
