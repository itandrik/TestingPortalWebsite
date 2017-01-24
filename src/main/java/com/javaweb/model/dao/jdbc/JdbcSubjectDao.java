package com.javaweb.model.dao.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.javaweb.model.dao.SubjectDao;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;

public class JdbcSubjectDao implements SubjectDao{
	private Connection connection;

	
	public JdbcSubjectDao(java.sql.Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<SubjectDao> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubjectDao> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(SubjectDao e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SubjectDao e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Subject> getSubjectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> getListOfTestsForSubject(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
