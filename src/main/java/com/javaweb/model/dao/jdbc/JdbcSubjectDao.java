package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.SubjectDao;
import com.javaweb.model.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javaweb.model.dao.DatabaseContract.SUBJECT_ID_COLUMN_NAME;
import static com.javaweb.model.dao.DatabaseContract.SUBJECT_NAME_COLUMN_NAME;

public class JdbcSubjectDao extends AbstractDao<Subject> implements SubjectDao {
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
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement(SELECT_SUBJECT_BY_ID);
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement(SELECT_ALL_SUBJECTS);
    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement(INSERT_SUBJECT);
    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement(SELECT_LAST_INSERT_ID);
    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement(UPDATE_SUBJECT);
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement(DELETE_SUBJECT);
    }

    @Override
    protected Subject getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Subject.Builder()
                .setId(resultSet.getInt(SUBJECT_ID_COLUMN_NAME))
                .setName(resultSet.getString(SUBJECT_NAME_COLUMN_NAME))
                .build();
    }

    @Override
    protected int getEntityId(Subject entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Subject entity) throws SQLException {
        statement.setString(1, entity.getNameOfSubject());
        statement.setInt(2, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Subject entity) throws SQLException {
        statement.setString(1, entity.getNameOfSubject());
    }
}
