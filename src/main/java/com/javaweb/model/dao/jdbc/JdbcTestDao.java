package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.TestDao;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcTestDao implements TestDao{
	private Connection connection;
	private static final String SELECT_LIST_OF_TEST_FOR_SUBJECT =
			"SELECT test_id, name, duration_time_in_minutes FROM Test" +
					" WHERE subject_id = ?";
	private static final String SELECT_TEST_BY_ID =
			"SELECT test_id, name, duration_time_in_minutes FROM Test WHERE test_id = ?";
	private static final String SELECT_ALL_TESTS =
			"SELECT test_id, name, duration_time_in_minutes FROM Test";
	private static final String INSERT_TEST =
			"INSERT INTO Test (name, duration_time_in_minutes) VALUES (?,?)";
	private static final String UPDATE_TEST_BY_ID =
			"UPDATE Test SET name = ?, duration_time_in_minutes = ? WHERE test_id = ?";
	private static final String DELETE_TEST_BY_ID =
			"DELETE FROM Test WHERE test_id = ?";

	public JdbcTestDao(Connection connection) {
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Test> getById(int id) {
		Optional<Test> result = Optional.empty();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_TEST_BY_ID)){
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				Test test = getTestFromResultSet(resultSet);
				result = Optional.of(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Test> getAll() {
		List<Test> result = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TESTS)){
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Test test = getTestFromResultSet(resultSet);
				result.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insert(Test test) {
		try(PreparedStatement statement = connection.prepareStatement(INSERT_TEST)){
			statement.setString(1,test.getNameOfTest());
			statement.setInt(2,test.getDurationTimeInMinutes());
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Test test) {
		try(PreparedStatement statement = connection.prepareStatement(UPDATE_TEST_BY_ID)){
			statement.setString(1,test.getNameOfTest());
			statement.setInt(2,test.getDurationTimeInMinutes());
			statement.setInt(3,test.getId());
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_TEST_BY_ID)){
			statement.setInt(1,id);
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Test> getListOfTestsForSubject(Subject subject) {
		List<Test> tests = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_LIST_OF_TEST_FOR_SUBJECT)){
			statement.setLong(1,subject.getId());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Test test = getTestFromResultSet(resultSet);
				tests.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tests;
	}

	private Test getTestFromResultSet(ResultSet resultSet) throws SQLException {
		return new Test.Builder()
				.setId(resultSet.getInt(TEST_ID_COLUMN_NAME))
				.setName(resultSet.getString(TEST_NAME_COLUMN_NAME))
				.setDurationTimeInMinutes(
						resultSet.getInt(TEST_DURATION_TIME_COLUMN_NAME))
				.build();
	}
}
