package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.SubjectDao;
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

public class JdbcSubjectDao implements SubjectDao{
	private Connection connection;

	private static final String SELECT_ALL_SUBJECTS =
			"SELECT subject_id, name FROM Subjects";
	private static final String SELECT_SUBJECT_BY_ID =
			"SELECT subject_id, name FROM Subject WHERE subject_id = ?";
	private static final String INSERT_SUBJECT =
			"INSERT INTO Subject (subject_id, name) VALUES (?,?)";
	private static final String UPDATE_SUBJECT =
			"UPDATE Subject SET name = ? WHERE subject_id = ?";
	private static final String DELETE_SUBJECT =
			"DELETE FROM Subject WHERE subject_id = ?";
	private static final String SELECT_LIST_OF_TEST_FOR_SUBJECT =
			"SELECT test_id, name, duration_time_in_minutes FROM Test" +
					" WHERE subject_id = ?";

	public JdbcSubjectDao(java.sql.Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Subject> getById(int id) {
		Optional<Subject> result = Optional.empty();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_SUBJECT_BY_ID)){
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				Subject subject = getSubjectFromResultSet(resultSet);
				result = Optional.of(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Subject> getAll() {
		List<Subject> subjects = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SUBJECTS)){
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				 Subject subject = getSubjectFromResultSet(resultSet);
				 subjects.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}

	private Subject getSubjectFromResultSet(ResultSet resultSet) throws SQLException{
		return new Subject.Builder()
				.setId(resultSet.getLong(SUBJECT_ID_COLUMN_NAME))
				.setName(resultSet.getString(SUBJECT_NAME_COLUMN_NAME))
				.build();
	}

	@Override
	public void insert(Subject subject) {
		try(PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT)){
			statement.setString(2,subject.getNameOfSubject());
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Subject subject) {
		try(PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT)){
			statement.setString(1,subject.getNameOfSubject());
			statement.setLong(2, subject.getId());
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try(PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT)){
			statement.setLong(1, id);
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Test> getListOfTestsForSubject(long id) {
		List<Test> tests = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_LIST_OF_TEST_FOR_SUBJECT)){
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Test subject = getTestFromResultSet(resultSet);
				tests.add(subject);
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
