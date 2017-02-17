package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.AnswerDao;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcAnswerDao extends AbstractDao<Answer> implements AnswerDao {
    private static final String SELECT_LIST_OF_ANSWERS_FOR_TASK =
            "SELECT answer_id, text, is_correct" +
                    " FROM Answer WHERE task_id = ?";
    private static final String SELECT_LIST_OF_ANSWERS_FOR_TEST =
            "SELECT answer_id, text, is_correct" +
                    " FROM (SELECT task_id FROM M2m_tests_tasks JOIN" +
                    " Task USING(task_id) WHERE test_id = ?) t" +
                    " JOIN Answer USING(task_id)";
    private static final String SELECT_ANSWER_BY_ID =
            "SELECT answer_id, text, is_correct FROM Answer WHERE answer_id = ?";
    private static final String SELECT_ALL_ANSWERS =
            "SELECT answer_id, text, is_correct FROM Answer";
    private static final String INSERT_ANSWER =
            "INSERT INTO Answer (text, is_correct, task_id) VALUES (?,?,?)";
    private static final String UPDATE_ANSWER_BY_ID =
            "UPDATE Answer SET text = ?, is_correct = ?, task_id = ?" +
                    " WHERE answer_id = ?";
    private static final String DELETE_ANSWER_BY_ID =
            "DELETE FROM Answer WHERE answer_id = ?";
    private static final String SELECT_LAST_INSERT_ID =
            "SELECT LAST_INSERT_ID() FROM Answer";

    public JdbcAnswerDao(Connection connection) {
        super(connection);
    }

    @Override
    protected Answer getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Answer.Builder()
                .setId(resultSet.getInt(ANSWER_ID_COLUMN_NAME))
                .setAnswerText(resultSet.getString(ANSWER_TEXT_COLUMN_NAME))
                .setIsCorrect(resultSet.getBoolean(ANSWER_IS_CORRECT_COLUMN_NAME))
                .build();
    }

    @Override
    protected int getEntityId(Answer entity) {
        return entity.getId();
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement(SELECT_ANSWER_BY_ID);
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement(SELECT_ALL_ANSWERS);
    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement(INSERT_ANSWER, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement(SELECT_LAST_INSERT_ID);
    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement(UPDATE_ANSWER_BY_ID);
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement(DELETE_ANSWER_BY_ID);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Answer entity) throws SQLException {
        statement.setString(1, entity.getAnswerText());
        statement.setBoolean(2, entity.getIsCorrect());
        statement.setInt(3, entity.getTaskId());
        statement.setInt(4, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Answer answer)
            throws SQLException {
        statement.setString(1, answer.getAnswerText());
        statement.setBoolean(2, answer.getIsCorrect());
        statement.setInt(3, answer.getTaskId());
    }

    @Override
    public List<Answer> getListOfAnswersForTask(Task task) {
        return getListOfEntitiesForAnotherEntity(SELECT_LIST_OF_ANSWERS_FOR_TASK, task.getId());
    }

    @Override
    public List<Answer> getListOfAnswersForTest(Test test) {
        return getListOfEntitiesForAnotherEntity(SELECT_LIST_OF_ANSWERS_FOR_TEST, test.getId());
    }
}
