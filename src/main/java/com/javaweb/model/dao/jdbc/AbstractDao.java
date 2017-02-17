package com.javaweb.model.dao.jdbc;

import com.javaweb.exception.ApplicationException;
import com.javaweb.model.dao.GenericDao;
import com.javaweb.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.javaweb.i18n.ErrorMessageKeys.*;
import static com.javaweb.model.dao.DatabaseContract.LAST_INSERT_ID;

/**
 * @author Andrii Chernysh on 17-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public abstract class AbstractDao<E> implements GenericDao<E> {
    private static final String GETTING_ALL_ROWS_FROM_DATABASE_LOG_MSG =
            "Error while select all rows from the table";
    private static final String GETTING_ROW_FROM_DATABASE_LOG_MSG =
            "Error while concrete row from the table";
    private static final String INSERT_ROW_TO_DATABASE_LOG_MSG =
            "Error while inserting row to the table";
    private static final String UPDATE_ROW_TO_DATABASE_LOG_MSG =
            "Error while updating row in the table";
    private static final String DELETE_ROW_FROM_DATABASE_LOG_MSG =
            "Error while deleting row in the table";
    private static final String LAST_INSERT_ID_LOG_MSG =
            "Error while getting last insert id after inserting";
    private static final String GETTING_ENTITIES_FOR_ANOTHER_ENTITY_LOG_MSG =
            "Error while getting getting list of entities for another entity";
    protected Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected abstract PreparedStatement getSelectEntityByIdStatement() throws SQLException;

    protected abstract PreparedStatement getSelectAllEntitiesStatement() throws SQLException;

    protected abstract PreparedStatement getInsertEntityStatement() throws SQLException;

    protected abstract PreparedStatement getLastInsertIdStatement() throws SQLException;

    protected abstract PreparedStatement getUpdateStatement() throws SQLException;

    protected abstract PreparedStatement getDeleteStatement() throws SQLException;

    protected abstract E getEntityFromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract int getEntityId(E entity);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, E entity) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, E entity) throws SQLException;

    @Override
    public Optional<E> getById(int id) {
        Optional<E> result = Optional.empty();

        try (PreparedStatement statement = getSelectEntityByIdStatement()) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                E entity = getEntityFromResultSet(resultSet);
                result = Optional.of(entity);
            }
        } catch (SQLException e) {
            throw generateException(ERROR_GETTING_ROW_FROM_DATABASE,
                    GETTING_ROW_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public List<E> getAll() {
        List<E> entities = new ArrayList<>();

        try (PreparedStatement statement = getSelectAllEntitiesStatement()) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                E entity = getEntityFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            throw generateException(ERROR_GETTING_ALL_ROWS_FROM_DATABASE,
                    GETTING_ALL_ROWS_FROM_DATABASE_LOG_MSG, getClass());

        }
        return entities;
    }

    @Override
    public int insert(E entity) {
        Objects.requireNonNull(entity);
        try (PreparedStatement statement = getInsertEntityStatement()) {
            prepareStatementForInsert(statement, entity);
            statement.executeUpdate();
            return getLastInsertId(entity);
        } catch (SQLException e) {
            throw generateException(ERROR_INSERT_ROW_TO_DATABASE,
                    INSERT_ROW_TO_DATABASE_LOG_MSG, entity.getClass());
        }
    }

    private int getLastInsertId(E entity) throws SQLException {
        try (PreparedStatement statement = getLastInsertIdStatement()) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(LAST_INSERT_ID);
            } else {
                throw generateException(ERROR_UNKNOWN_EXCEPTION,
                        LAST_INSERT_ID_LOG_MSG, entity.getClass());
            }
        }
    }

    @Override
    public int update(E entity) {
        Objects.requireNonNull(entity);
        int lastInsertId = getEntityId(entity);
        try (PreparedStatement statement = getUpdateStatement()) {
            prepareStatementForUpdate(statement, entity);

            if (statement.executeUpdate() == 0) {
                lastInsertId = insert(entity);
            }
        } catch (SQLException e) {
            throw generateException(ERROR_UPDATE_ROW_TO_DATABASE,
                    UPDATE_ROW_TO_DATABASE_LOG_MSG, entity.getClass());
        }
        return lastInsertId;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = getDeleteStatement()) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw generateException(ERROR_DELETE_ROW_FROM_DATABASE,
                    DELETE_ROW_FROM_DATABASE_LOG_MSG, getClass());
        }
    }

    protected List<E> getListOfEntitiesForAnotherEntity(
            String query, int anotherEntityId) {
        List<E> entities = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, anotherEntityId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                E entity = getEntityFromResultSet(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            throw generateException(ERROR_UNKNOWN_EXCEPTION,
                    GETTING_ENTITIES_FOR_ANOTHER_ENTITY_LOG_MSG, getClass());
        }
        return entities;
    }

    protected ApplicationException generateException(
            String message, String logMessage, Class classThatThrows) {
        return new DaoException()
                .addMessage(message)
                .addLogMessage(logMessage)
                .setClassThrowsException(classThatThrows);
    }
}
