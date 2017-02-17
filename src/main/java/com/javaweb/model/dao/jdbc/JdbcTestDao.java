package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.TestDao;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcTestDao extends AbstractDao<Test> implements TestDao{
	private static final String SELECT_LIST_OF_TEST_FOR_SUBJECT =
			"SELECT test_id, name, duration_time_in_minutes, subject_id FROM Test" +
					" WHERE subject_id = ?";
	private static final String SELECT_TEST_BY_ID =
			"SELECT test_id, name, duration_time_in_minutes, subject_id FROM Test WHERE test_id = ?";
	private static final String SELECT_ALL_TESTS =
			"SELECT test_id, name, duration_time_in_minutes, subject_id FROM Test";
	private static final String INSERT_TEST =
			"INSERT INTO Test (name, duration_time_in_minutes, subject_id) VALUES (?,?,?)";
	private static final String UPDATE_TEST_BY_ID =
			"UPDATE Test SET name = ?, duration_time_in_minutes = ? WHERE test_id = ?";
	private static final String DELETE_TEST_BY_ID =
			"DELETE FROM Test WHERE test_id = ?";
	private static final String SELECT_LAST_INSERT_ID =
			"SELECT LAST_INSERT_ID() FROM Test";

	public JdbcTestDao(Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
		return connection.prepareStatement(SELECT_TEST_BY_ID);
	}

	@Override
	protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
		return connection.prepareStatement(SELECT_ALL_TESTS);
	}

	@Override
	protected PreparedStatement getInsertEntityStatement() throws SQLException {
		return connection.prepareStatement(INSERT_TEST);
	}

	@Override
	protected PreparedStatement getLastInsertIdStatement() throws SQLException {
		return connection.prepareStatement(SELECT_LAST_INSERT_ID);
	}

	@Override
	protected PreparedStatement getUpdateStatement() throws SQLException {
		return connection.prepareStatement(UPDATE_TEST_BY_ID);
	}

	@Override
	protected PreparedStatement getDeleteStatement() throws SQLException {
		return connection.prepareStatement(DELETE_TEST_BY_ID);
	}

	@Override
	protected Test getEntityFromResultSet(ResultSet resultSet) throws SQLException {
		return new Test.Builder()
				.setId(resultSet.getInt(TEST_ID_COLUMN_NAME))
				.setName(resultSet.getString(TEST_NAME_COLUMN_NAME))
				.setDurationTimeInMinutes(
						resultSet.getInt(TEST_DURATION_TIME_COLUMN_NAME))
				.setSubjectId(resultSet.getInt(TEST_SUBJECT_ID_NAME))
				.build();
	}

	@Override
	protected int getEntityId(Test entity) {
		return entity.getId();
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Test test) throws SQLException {
		statement.setString(1,test.getNameOfTest());
		statement.setInt(2,test.getDurationTimeInMinutes());
		statement.setInt(3,test.getId());
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Test test) throws SQLException {
		statement.setString(1,test.getNameOfTest());
		statement.setInt(2,test.getDurationTimeInMinutes());
		statement.setInt(3,test.getSubjectId());
	}

	@Override
	public List<Test> getListOfTestsForSubject(Subject subject) {
		return getListOfEntitiesForAnotherEntity(SELECT_LIST_OF_TEST_FOR_SUBJECT,subject.getId());
	}
}
