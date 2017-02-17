package com.javaweb.model.dao.jdbc;

import com.javaweb.i18n.ErrorMessageKeys;
import com.javaweb.model.dao.TaskDao;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.AnswerType;
import com.javaweb.model.entity.task.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcTaskDao extends AbstractDao<Task> implements TaskDao{
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
	private static final String ASSIGN_TASK_TO_TEST =
			"INSERT INTO m2m_tests_tasks  (test_id, task_id) VALUES (?,?)";
	private static final String UPDATE_TASK_BY_ID =
			"UPDATE Task SET question = ?, answers_type = ?, explanation = ?" +
					" WHERE task_id = ?";
	private static final String DELETE_TASK_BY_ID =
			"DELETE FROM Task WHERE task_id = ?";
	private static final String SELECT_LAST_INSERT_ID =
			"SELECT LAST_INSERT_ID() FROM Task";
	private static final String CANNOT_ASSIGN_TASK_TO_TEST_LOG_MSG =
			"Error while assigning task to test";

	public JdbcTaskDao(java.sql.Connection connection) {
		super(connection);
	}

	@Override
	protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
		return connection.prepareStatement(SELECT_TASK_BY_ID);
	}

	@Override
	protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
		return connection.prepareStatement(SELECT_ALL_TASKS);
	}

	@Override
	protected PreparedStatement getInsertEntityStatement() throws SQLException {
		return connection.prepareStatement(
				INSERT_TASK,PreparedStatement.RETURN_GENERATED_KEYS);
	}

	@Override
	protected PreparedStatement getLastInsertIdStatement() throws SQLException {
		return connection.prepareStatement(SELECT_LAST_INSERT_ID);
	}

	@Override
	protected PreparedStatement getUpdateStatement() throws SQLException {
		return connection.prepareStatement(UPDATE_TASK_BY_ID);
	}

	@Override
	protected PreparedStatement getDeleteStatement() throws SQLException {
		return connection.prepareStatement(DELETE_TASK_BY_ID);
	}

	@Override
	protected Task getEntityFromResultSet(ResultSet resultSet) throws SQLException {
		return new Task.Builder()
				.setId(resultSet.getInt(TASK_ID_COLUMN_NAME))
				.setQuestion(resultSet.getString(TASK_QUESTION_COLUMN_NAME))
				.setAnswerType(AnswerType.getAnswerTypeFromString(
						resultSet.getString(TASK_ANSWERS_TYPE_COLUMN_NAME))
						.orElseThrow(RuntimeException::new))
				.setExplanation(resultSet.getString(TASK_EXPLANATION_COLUMN_NAME))
				.build();
	}

	@Override
	protected int getEntityId(Task entity) {
		return entity.getId();
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Task task) throws SQLException {
		statement.setString(1,task.getQuestion());
		statement.setString(2, task.getAnswerType().toString());
		statement.setString(3, task.getExplanation());
		statement.setInt(4, task.getId());
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Task task) throws SQLException {
		statement.setString(1,task.getQuestion());
		statement.setString(2,task.getAnswerType().toString());
		statement.setString(3,task.getExplanation());
	}

	@Override
	public List<Task> getListOfTasksForTest(Test test) {
		return getListOfEntitiesForAnotherEntity(SELECT_LIST_OF_TASKS_FOR_TEST,test.getId());
	}

	@Override
	public void assignTaskToTest(int taskId, int testId) {
		try(PreparedStatement statement = connection.prepareStatement(ASSIGN_TASK_TO_TEST)) {
			statement.setInt(1,testId);
			statement.setInt(2,taskId);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw generateException(ErrorMessageKeys.ERROR_UNKNOWN_EXCEPTION,
					CANNOT_ASSIGN_TASK_TO_TEST_LOG_MSG,JdbcTaskDao.class);
		}
	}
}
