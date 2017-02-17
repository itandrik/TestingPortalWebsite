package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoConnection implements DaoConnection {
	private Connection connection;
	private boolean inTransaction = false;
	private static final String CAN_NOT_BEGIN_TRANSACTION = "Can not begin transaction.";
	private static final String CAN_NOT_COMMIT_TRANSACTION = "Can not commit transaction";
	private static final String CAN_NOT_ROLLBACK_TRANSACTION = "Can not rollback transaction";
	private static final String CAN_NOT_CLOSE_CONNECTION = "Can not close connection";

	public JdbcDaoConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void close() {
		if (inTransaction) {
			rollback();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			throw new DaoException()
					.addLogMessage(CAN_NOT_CLOSE_CONNECTION)
					.setClassThrowsException(JdbcDaoConnection.class);
		}
	}

	@Override
	public void begin() {
		try {
			connection.setAutoCommit(false);
			inTransaction = true;
		} catch (SQLException e) {
			throw new DaoException()
					.addLogMessage(CAN_NOT_BEGIN_TRANSACTION)
					.setClassThrowsException(JdbcDaoConnection.class);
		}
	}

	@Override
	public void commit() {
		try {
			connection.commit();
			inTransaction = false;
		} catch (SQLException e) {
			throw new DaoException()
					.addLogMessage(CAN_NOT_COMMIT_TRANSACTION)
					.setClassThrowsException(JdbcDaoConnection.class);
		}
	}

	@Override
	public void rollback() {
		try {
			connection.rollback();
			inTransaction = false;
		} catch (SQLException e) {
			throw new DaoException()
					.addLogMessage(CAN_NOT_ROLLBACK_TRANSACTION)
					.setClassThrowsException(JdbcDaoConnection.class);
		}

	}

	public Connection getConnection() {
		return connection;
	}
}
