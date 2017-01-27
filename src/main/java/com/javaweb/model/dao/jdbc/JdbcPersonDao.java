package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.PersonDao;
import com.javaweb.model.entity.person.Gender;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.person.PersonRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

public class JdbcPersonDao implements PersonDao {
    private static final String SELECT_BY_ID = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person WHERE person_id = ?";
    private static final String SELECT_BY_LOGIN = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person WHERE login = ?";
    public static final String SELECT_ALL = "SELECT person_id,"
            + "first_name, second_name, gender, login, password,"
            + " role FROM Person";
    private static final String INSERT_PERSON =
            "INSERT INTO Person (first_name, second_name, gender," +
                    " login, password, role) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_PERSON =
            "UPDATE Person SET first_name = ?, second_name = ?," +
                    " gender = ?, login = ?, password = ?, role = ?" +
                    " WHERE person_id = ?";
    private static final String DELETE_PERSON =
            "DELETE FROM Person WHERE person_id = ?";

    private Connection connection;

    public JdbcPersonDao(java.sql.Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
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
                        resultSet.getString(PERSON_GENDER_COLUMN_NAME)).orElseThrow(RuntimeException::new))
                .setLogin(resultSet.getString(PERSON_LOGIN_COLUMN_NAME))
                .setPassword(resultSet.getString(PERSON_PASSWORD_COLUMN_NAME))
                .setRole(PersonRole.getRoleFromString(
                        resultSet.getString(PERSON_ROLE_COLUMN_NAME)).orElseThrow(RuntimeException::new))
                .build();
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Person person = getPersonFromResultSet(rs);
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public void insert(Person person) {
        try(PreparedStatement statement = connection.prepareStatement(
                INSERT_PERSON,PreparedStatement.RETURN_GENERATED_KEYS)){

            statement.setString(1,person.getFirstName());
            statement.setString(2,person.getSecondName());
            statement.setString(3,person.getGender().toString());
            statement.setString(4,person.getLogin());
            statement.setString(5,person.getPassword());
            statement.setString(6,person.getRole().toString());

            statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Person person) {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_PERSON)){
            statement.setString(1,person.getFirstName());
            statement.setString(2,person.getSecondName());
            statement.setString(3,person.getGender().toString());
            statement.setString(4,person.getLogin());
            statement.setString(5,person.getPassword());
            statement.setString(6,person.getRole().toString());
            statement.setInt(7,person.getId());

            statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_PERSON)){
            statement.setInt(1, id);
            statement.executeUpdate();
				/* TODO Check for null*/
				/* TODO Check is already saved in database */

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
