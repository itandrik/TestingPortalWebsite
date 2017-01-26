package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.AnswerDao;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.task.Task;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcAnswerDao implements AnswerDao{
	private Connection connection;

	public JdbcAnswerDao(Connection connection) {
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Answer> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Answer e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Answer
								   e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Answer> getListOfAnswersForTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

}
