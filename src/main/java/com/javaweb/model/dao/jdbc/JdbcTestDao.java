package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.TestDao;
import com.javaweb.model.entity.Task;
import com.javaweb.model.entity.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcTestDao implements TestDao{
	private Connection connection;

	public JdbcTestDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Test> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Test e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Test e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> getListOfTasksForTest(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
