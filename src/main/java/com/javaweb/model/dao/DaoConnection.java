package com.javaweb.model.dao;

import java.sql.Connection;

public interface DaoConnection extends AutoCloseable{
	void begin();
	void commit();
	void rollback();
	void close();
	Connection getConnection();
}
