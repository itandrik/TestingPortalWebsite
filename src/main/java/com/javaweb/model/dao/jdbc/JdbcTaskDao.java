package com.javaweb.model.dao.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.javaweb.model.dao.TaskDao;
import com.javaweb.model.entity.Answer;

public class JdbcTaskDao implements TaskDao{
	private Connection connection;

	public JdbcTaskDao(java.sql.Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<TaskDao> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskDao> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(TaskDao e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TaskDao e) {
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
