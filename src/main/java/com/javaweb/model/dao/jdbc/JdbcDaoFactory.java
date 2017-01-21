package com.javaweb.model.dao.jdbc;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.javaweb.model.dao.AnswerDao;
import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.PersonDao;
import com.javaweb.model.dao.SubjectDao;
import com.javaweb.model.dao.TaskDao;
import com.javaweb.model.dao.TestDao;

public class JdbcDaoFactory extends DaoFactory{
	
	private DataSource dataSource;
	
	public JdbcDaoFactory() {
		try {
			InitialContext initialContext = new InitialContext();
			dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/training_portal");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DaoConnection getConnecion() {
		try {
			return new JdbcDaoConnection(dataSource.getConnection());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PersonDao createPersonDao(DaoConnection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectDao createSubjectDao(DaoConnection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TestDao createTestDao(DaoConnection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskDao createTaskDao(DaoConnection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnswerDao createAnswerDao(DaoConnection connection) {
		// TODO Auto-generated method stub
		return null;
	}

}
