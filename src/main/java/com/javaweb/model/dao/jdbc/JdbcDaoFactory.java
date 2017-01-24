package com.javaweb.model.dao.jdbc;

import java.sql.Connection;
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
			dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/testing_portal");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DaoConnection getConnection() {
		try {
			return new JdbcDaoConnection(dataSource.getConnection());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Connection getCastedSqlConnection(DaoConnection connection) {
		JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcDaoConnection.getConnection();
		return sqlConnection;
	}
	
	@Override
	public PersonDao createPersonDao(DaoConnection connection) {
		Connection sqlConnection = getCastedSqlConnection(connection);
		return new JdbcPersonDao(sqlConnection);
	}

	@Override
	public SubjectDao createSubjectDao(DaoConnection connection) {
		Connection sqlConnection = getCastedSqlConnection(connection);
		return new JdbcSubjectDao(sqlConnection);
	}

	@Override
	public TestDao createTestDao(DaoConnection connection) {
		Connection sqlConnection = getCastedSqlConnection(connection);
		return new JdbcTestDao(sqlConnection);
	}

	@Override
	public TaskDao createTaskDao(DaoConnection connection) {
		Connection sqlConnection = getCastedSqlConnection(connection);
		return new JdbcTaskDao(sqlConnection);
	}

	@Override
	public AnswerDao createAnswerDao(DaoConnection connection) {
		Connection sqlConnection = getCastedSqlConnection(connection);
		return new JdbcAnswerDao(sqlConnection);
	}

}
