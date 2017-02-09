package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.SubjectDao;
import com.javaweb.model.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcSubjectDao implements SubjectDao{
	private Connection connection;

	private static final String SELECT_ALL_SUBJECTS =
			"SELECT subject_id, name FROM Subject";
	private static final String SELECT_SUBJECT_BY_ID =
			"SELECT subject_id, name FROM Subject WHERE subject_id = ?";
	private static final String INSERT_SUBJECT =
			"INSERT INTO Subject (name) VALUES (?)";
	private static final String UPDATE_SUBJECT =
			"UPDATE Subject SET name = ? WHERE subject_id = ?";
	private static final String DELETE_SUBJECT =
			"DELETE FROM Subject WHERE subject_id = ?";
	private static final String SELECT_LAST_INSERT_ID =
			"SELECT LAST_INSERT_ID() FROM Subject";

	public JdbcSubjectDao(java.sql.Connection connection) {
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
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

	@Override
	public int insert(Subject subject) {
		int lastInsertId = 0;
		try(PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECT)){

			statement.setString(1,subject.getNameOfSubject());
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
			Statement lastInsertIdStatement = connection.createStatement();
			ResultSet rs = lastInsertIdStatement.executeQuery(SELECT_LAST_INSERT_ID);
			rs.next();
			lastInsertId =  rs.getInt(LAST_INSERT_ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastInsertId;
	}

	@Override
	public void update(Subject subject) {
		try(PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT)){
			statement.setString(1,subject.getNameOfSubject());
			statement.setInt(2, subject.getId());
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT)){
			statement.setInt(1, id);
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Subject getSubjectFromResultSet(ResultSet resultSet) throws SQLException{
		return new Subject.Builder()
				.setId(resultSet.getInt(SUBJECT_ID_COLUMN_NAME))
				.setName(resultSet.getString(SUBJECT_NAME_COLUMN_NAME))
				.build();
	}
}
