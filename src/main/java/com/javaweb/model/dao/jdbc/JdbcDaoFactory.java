package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.*;
import com.javaweb.model.dao.exception.DaoException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory{
	private DataSource dataSource;
	private static final String CAN_NOT_GET_CONNECTION = "Can not get connection";
	private static final String CAN_INIT_DATA_SOURCE_CONNECTION = "Can init data source";

	public JdbcDaoFactory() {
		try {
			InitialContext initialContext = new InitialContext();
			dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/testing_portal");
		} catch (NamingException e) {
			throw new DaoException()
					.addLogMessage(CAN_NOT_GET_CONNECTION)
					.setClassThrowsException(JdbcDaoFactory.class);
		}
	}

	@Override
	public DaoConnection getConnection() {
		try {
			return new JdbcDaoConnection(dataSource.getConnection());
		} catch (SQLException e) {
			throw new DaoException()
					.addLogMessage(CAN_NOT_GET_CONNECTION)
					.setClassThrowsException(JdbcDaoFactory.class);
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

	@Override
	public PersonTestHistoryDao createPersonTestHistoryDao(DaoConnection connection) {
		Connection sqlConnection = getCastedSqlConnection(connection);
		return new JdbcPersonTestHistoryDao(sqlConnection);
	}

}
