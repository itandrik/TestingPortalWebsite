package com.javaweb.model.dao;

import java.util.List;
import com.javaweb.model.entity.Answer;

public interface TaskDao extends GenericDao<TaskDao> {
	List<Answer> getListOfAnswersForTask(long id);
}
