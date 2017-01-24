package com.javaweb.model.dao.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.javaweb.model.dao.AnswerDao;

public class JdbcAnswerDao implements AnswerDao{
	private Connection connection;

	public JdbcAnswerDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<AnswerDao> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerDao> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(AnswerDao e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AnswerDao e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
