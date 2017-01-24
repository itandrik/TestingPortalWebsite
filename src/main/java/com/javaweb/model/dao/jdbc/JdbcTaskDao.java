package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.TaskDao;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Task;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcTaskDao implements TaskDao{
	private Connection connection;

	public JdbcTaskDao(java.sql.Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Task> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Task e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Task e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Answer> getListOfAnswersForTask(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
