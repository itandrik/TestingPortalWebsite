package com.javaweb.model.dao.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.javaweb.model.dao.TestDao;
import com.javaweb.model.entity.Task;

public class JdbcTestDao implements TestDao{
	private Connection connection;

	public JdbcTestDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<TestDao> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestDao> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(TestDao e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TestDao e) {
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
