package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.AnswerDao;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.task.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcAnswerDao implements AnswerDao{
	private Connection connection;

	private static final String SELECT_LIST_OF_ANSWERS_FOR_TASK =
			"SELECT answer_id, text, is_correct" +
					" FROM Answer WHERE task_id = ?";
	private static final String SELECT_ANSWER_BY_ID =
			"SELECT answer_id, text, is_correct FROM Answer WHERE answer_id = ?";
	private static final String SELECT_ALL_ANSWERS =
			"SELECT answer_id, text, is_correct FROM Answer";
	private static final String INSERT_ANSWER =
			"INSERT INTO Answer (text, is_correct) VALUES (?,?)";
	private static final String UPDATE_ANSWER_BY_ID =
			"UPDATE Answer SET text = ?, is_correct = ? WHERE answer_id = ?";
	private static final String DELETE_ANSWER_BY_ID =
			"DELETE FROM Answer WHERE answer_id = ?";
	private static final String INSERT_ANSWER_FOR_PERSON =
			"INSERT INTO person_has_answer (person_id, answer_id) VALUES(?,?)";

	public JdbcAnswerDao(Connection connection) {
		this.connection = connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Optional<Answer> getById(int id) {
		Optional<Answer> result = Optional.empty();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_ANSWER_BY_ID)){
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				Answer answer = getAnswerFromResultSet(resultSet);
				result = Optional.of(answer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Answer> getAll() {
		List<Answer> answers = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ANSWERS)){
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Answer answer = getAnswerFromResultSet(resultSet);
				answers.add(answer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answers;
	}

	@Override
	public void insert(Answer answer) {
		try(PreparedStatement statement = connection.prepareStatement(INSERT_ANSWER)){
			statement.setString(1,answer.getAnswerText());
			statement.setBoolean(2,answer.isCorrect());

			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Answer answer) {
		try(PreparedStatement statement = connection.prepareStatement(UPDATE_ANSWER_BY_ID)){
			statement.setString(1,answer.getAnswerText());
			statement.setBoolean(2, answer.isCorrect());
			statement.setInt(3, answer.getId());

			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try(PreparedStatement statement = connection.prepareStatement(DELETE_ANSWER_BY_ID)){
			statement.setInt(1, id);
			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Answer> getListOfAnswersForTask(Task task) {
		List<Answer> answers = new ArrayList<>();

		try(PreparedStatement statement = connection.prepareStatement(SELECT_LIST_OF_ANSWERS_FOR_TASK)){
			statement.setInt(1,task.getId());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				Answer answer = getAnswerFromResultSet(resultSet);
				answers.add(answer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answers;
	}

	@Override
	public void insertAnswerForPersonHistory(int answerId, int personId){
		try(PreparedStatement statement = connection.prepareStatement(INSERT_ANSWER_FOR_PERSON)){
			statement.setInt(1,personId);
			statement.setInt(2,answerId);

			statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Answer getAnswerFromResultSet(ResultSet resultSet) throws SQLException {
		return new Answer.Builder()
				.setId(resultSet.getInt(ANSWER_ID_COLUMN_NAME))
				.setAnswerText(resultSet.getString(ANSWER_TEXT_COLUMN_NAME))
				.setIsCorrect(resultSet.getBoolean(ANSWER_IS_CORRECT_COLUMN_NAME))
				.build();
	}
}
