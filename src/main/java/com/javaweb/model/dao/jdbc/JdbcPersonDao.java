package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.PersonDao;
import com.javaweb.model.entity.person.Gender;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.person.PersonRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcPersonDao extends AbstractDao<Person> implements PersonDao {
    private static final String SELECT_BY_ID = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person WHERE person_id = ?";
    private static final String SELECT_BY_LOGIN = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person WHERE login = ?";
    private static final String SELECT_ALL = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person";
    private static final String SELECT_ALL_STUDENTS =
            "SELECT person_id, first_name, second_name, gender," +
                    " login, password, role FROM Person WHERE role = ?";
    private static final String INSERT_PERSON =
            "INSERT INTO Person (first_name, second_name, gender," +
                    " login, password, role) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_PERSON =
            "UPDATE Person SET first_name = ?, second_name = ?," +
                    " gender = ?, login = ?, password = ?, role = ?" +
                    " WHERE person_id = ?";
    private static final String DELETE_PERSON =
            "DELETE FROM Person WHERE person_id = ?";
    private static final String SELECT_LAST_INSERT_ID =
            "SELECT LAST_INSERT_ID() FROM Person";

    public JdbcPersonDao(java.sql.Connection connection) {
        super(connection);
    }

    @Override
    protected PreparedStatement getSelectEntityByIdStatement() throws SQLException {
        return connection.prepareStatement(SELECT_BY_ID);
    }

    @Override
    protected PreparedStatement getSelectAllEntitiesStatement() throws SQLException {
        return connection.prepareStatement(SELECT_ALL);
    }

    @Override
    protected PreparedStatement getInsertEntityStatement() throws SQLException {
        return connection.prepareStatement(
                INSERT_PERSON, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    @Override
    protected PreparedStatement getLastInsertIdStatement() throws SQLException {
        return connection.prepareStatement(SELECT_LAST_INSERT_ID);
    }

    @Override
    protected PreparedStatement getUpdateStatement() throws SQLException {
        return connection.prepareStatement(UPDATE_PERSON);
    }

    @Override
    protected PreparedStatement getDeleteStatement() throws SQLException {
        return connection.prepareStatement(DELETE_PERSON);
    }

    @Override
    protected Person getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Person.Builder()
                .setId(resultSet.getInt(PERSON_ID_COLUMN_NAME))
                .setFirstName(resultSet.getString(PERSON_FIRST_NAME_COLUMN_NAME))
                .setSecondName(resultSet.getString(PERSON_SECOND_NAME_COLUMN_NAME))
                .setGender(Gender.getGenderFromString(
                        resultSet.getString(PERSON_GENDER_COLUMN_NAME)).orElseThrow(RuntimeException::new))
                .setLogin(resultSet.getString(PERSON_LOGIN_COLUMN_NAME))
                .setPassword(resultSet.getString(PERSON_PASSWORD_COLUMN_NAME))
                .setRole(PersonRole.getRoleFromString(
                        resultSet.getString(PERSON_ROLE_COLUMN_NAME)).orElseThrow(RuntimeException::new))
                .build();
    }

    @Override
    protected int getEntityId(Person entity) {
        return entity.getId();
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Person entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getSecondName());
        statement.setString(3, entity.getGender().toString());
        statement.setString(4, entity.getLogin());
        statement.setString(5, entity.getPassword());
        statement.setString(6, entity.getRole().toString());
        statement.setInt(7, entity.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Person entity) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getSecondName());
        statement.setString(3, entity.getGender().toString());
        statement.setString(4, entity.getLogin());
        statement.setString(5, entity.getPassword());
        statement.setString(6, entity.getRole().toString());
    }

    @Override
    public Optional<Person> getPersonByLogin(String login) {
        Optional<Person> result = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Person person = getEntityFromResultSet(resultSet);
                result = Optional.of(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Person> getAllStudents() {
        List<Person> students = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            statement.setString(1, PersonRole.STUDENT.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Person student = getEntityFromResultSet(resultSet);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
