package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.PersonDao;
import com.javaweb.model.entity.person.Gender;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.person.PersonRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcPersonDao implements PersonDao {
    public static final String SELECT_BY_ID = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person WHERE person_id = ?";
    public static final String SELECT_BY_LOGIN = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person WHERE login = ?";

    private Connection connection;

    public JdbcPersonDao(java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Person> getById(int id) {
        Optional<Person> result = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Person person = getPersonFromResultSet(resultSet);
                result = Optional.of(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Person getPersonFromResultSet(ResultSet resultSet) throws SQLException {
        return new Person.Builder()
                .setId(resultSet.getInt(PERSON_ID_COLUMN_NAME))
                .setFirstName(resultSet.getString(PERSON_FIRST_NAME_COLUMN_NAME))
                .setSecondName(resultSet.getString(PERSON_SECOND_NAME_COLUMN_NAME))
                .setGender(Gender.getGenderFromString(
                        resultSet.getString(PERSON_GENDER_COLUMN_NAME)).get())
                .setLogin(resultSet.getString(PERSON_LOGIN_COLUMN_NAME))
                .setPassword(resultSet.getString(PERSON_PASSWORD_COLUMN_NAME))
                .setRole(PersonRole.getRoleFromString(
                        resultSet.getString(PERSON_ROLE_COLUMN_NAME)).get())
                .build();
    }

    @Override
    public List<Person> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void create(Person e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Person e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Person> getPersonByLogin(String login) {
        Optional<Person> result = Optional.empty();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Person person = getPersonFromResultSet(resultSet);
                result = Optional.of(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
