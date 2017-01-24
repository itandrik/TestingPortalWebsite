package com.javaweb.model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.javaweb.model.dao.DaoConnection;

public class JdbcDaoConnection implements DaoConnection {
	private Connection connection;
	private boolean inTransaction = false;

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
			throw new RuntimeException(e);
		}
	}

	@Override
	public void begin() {
		try {
			connection.setAutoCommit(false);
			inTransaction = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void commit() {
		try {
			connection.commit();
			inTransaction = false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void rollback() {
		try {
			connection.rollback();
			inTransaction = false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Connection getConnection() {
		return connection;
	}

}
