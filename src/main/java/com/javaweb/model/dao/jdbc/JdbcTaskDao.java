package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.TaskDao;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.AnswerType;
import com.javaweb.model.entity.task.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcTaskDao implements TaskDao{
	private Connection connection;

	private static final String SELECT_LIST_OF_TASKS_FOR_TEST =
			"SELECT task_id, question, answers_type, explanation" +
					" FROM Task JOIN M2m_tests_tasks USING (task_id)" +
					" WHERE test_id = ?";
	private static final String SELECT_TASK_BY_ID =
			"SELECT task_id, question, answers_type, explanation" +
					" FROM Task WHERE task_id = ?";
	private static final String SELECT_ALL_TASKS =
			"SELECT task_id, question, answers_type, explanation FROM Task";
	private static final String INSERT_TASK =
			"INSERT INTO Task (question, answers_type, explanation) VALUES (?,?,?)";
	private static final String UPDATE_TASK_BY_ID =
			"UPDATE Task SET question = ?, answers_type = ?, explanation = ?" +
					" WHERE task_id = ?";
	private static final String DELETE_TASK_BY_ID =
			"DELETE FROM Task WHERE task_id = ?";
	private static final String SELECT_LAST_INSERT_ID =
			"SELECT LAST_INSERT_ID() FROM Task";

	public JdbcTaskDao(java.sql.Connection connection) {
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Task> getById(int id) {
		Optional<Task> result = Optional.empty();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_TASK_BY_ID)){
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				Task task = getTaskFromResultSet(resultSet);
				result = Optional.of(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Task> getAll() {
		List<Task> tasks = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TASKS)){
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Task task = getTaskFromResultSet(resultSet);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	@Override
	public int insert(Task task) {
		int lastInsertId = 0;
		try(PreparedStatement statement = connection.prepareStatement(
				INSERT_TASK,PreparedStatement.RETURN_GENERATED_KEYS)){
			statement.setString(1,task.getQuestion());
			statement.setString(2,task.getAnswerType().toString());
			statement.setString(3,task.getExplanation());

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
	public void update(Task task) {
		try(PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_BY_ID)){
			statement.setString(1,task.getQuestion());
			statement.setString(2, task.getAnswerType().toString());
			statement.setString(3, task.getExplanation());
			statement.setInt(4, task.getId());

			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_TASK_BY_ID)){
			statement.setInt(1, id);
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Task> getListOfTasksForTest(Test test) {
		List<Task> tasks = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_LIST_OF_TASKS_FOR_TEST)){
			statement.setInt(1,test.getId());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Task task = getTaskFromResultSet(resultSet);
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	private Task getTaskFromResultSet(ResultSet resultSet) throws SQLException {
		return new Task.Builder()
				.setId(resultSet.getInt(TASK_ID_COLUMN_NAME))
				.setQuestion(resultSet.getString(TASK_QUESTION_COLUMN_NAME))
				.setAnswerType(AnswerType.getAnswerTypeFromString(
						resultSet.getString(TASK_ANSWERS_TYPE_COLUMN_NAME))
						.orElseThrow(RuntimeException::new))
				.setExplanation(resultSet.getString(TASK_EXPLANATION_COLUMN_NAME))
				.build();
	}

}
