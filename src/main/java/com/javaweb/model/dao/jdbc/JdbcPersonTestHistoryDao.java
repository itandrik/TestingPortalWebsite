package com.javaweb.model.dao.jdbc;

import com.javaweb.model.dao.PersonTestHistoryDao;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.history.PersonHistory;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.task.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.javaweb.model.dao.DatabaseContract.*;

/**
 * @author Andrii Chernysh on 31-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class JdbcPersonTestHistoryDao implements PersonTestHistoryDao {
    private Connection connection;

    private static final String INSERT_PERSON_TEST_HISTORY =
            "INSERT INTO person_test_history (person_id, test_id, end_time, grade)" +
                    " VALUES (?,?,?,?)";
    private static final String SELECT_PASSED_TESTS_BY_PERSON =
            "SELECT test_id, name, subject_id, duration_time_in_minutes FROM" +
                    " Test JOIN Person_test_history USING (test_id)" +
                    " WHERE person_id = ? ORDER BY subject_id DESC";
    private static final String SELECT_ALL_PASSED_ANSWERS_BY_PERSON_FOR_TEST =
            "SELECT answer_id, text, is_correct FROM " +
                    "(SELECT task_id FROM M2m_tests_tasks WHERE test_id = ?) t " +
                    "JOIN Answer USING(task_id) " +
                    "JOIN Person_has_answer USING(answer_id) WHERE person_id = ?";
    private static final String SELECT_ALL_PASSED_ANSWERS_BY_PERSON_FOR_TASK =
            "SELECT answer_id, text, is_correct FROM " +
                    "(SELECT answer_id FROM Person_has_answer WHERE person_id = ?) t " +
                    "JOIN Answer USING(answer_id) WHERE task_id = ?";
    private static final String INSERT_ANSWER_FOR_PERSON =
            "INSERT INTO person_has_answer (person_id, answer_id) VALUES(?,?)";
    private static final String SELECT_LAST_INSERT_ID =
            "SELECT LAST_INSERT_ID() FROM person_test_history";

    public JdbcPersonTestHistoryDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<PersonHistory> getById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<PersonHistory> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int insert(PersonHistory personHistory) {
        int lastInsertId = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_PERSON_TEST_HISTORY)) {
            statement.setInt(1, personHistory.getPerson().getId());
            statement.setInt(2, personHistory.getTest().getId());
            statement.setTimestamp(3, personHistory.getEndTime());
            statement.setDouble(4, personHistory.getGrade().getNumberGrade());

            statement.executeUpdate();
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
    public int update(PersonHistory personHistory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Test> getListOfPassedTestsByPerson(Person person) {
        List<Test> tests = new ArrayList<>();

        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_PASSED_TESTS_BY_PERSON)) {
            statement.setInt(1, person.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Test test = getTestFromResultSet(resultSet);
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    private Test getTestFromResultSet(ResultSet resultSet) throws SQLException {
        return new Test.Builder()
                .setId(resultSet.getInt(TEST_ID_COLUMN_NAME))
                .setName(resultSet.getString(TEST_NAME_COLUMN_NAME))
                .setDurationTimeInMinutes(
                        resultSet.getInt(TEST_DURATION_TIME_COLUMN_NAME))
                .build();
    }

    @Override
    public List<Answer> getAllPassedAnswersByPersonForTest(Test test, Person person) {
        List<Answer> answers = new ArrayList<>();

        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL_PASSED_ANSWERS_BY_PERSON_FOR_TEST)) {
            statement.setInt(1, test.getId());
            statement.setInt(2, person.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Answer answer = getAnswerFromResultSet(resultSet);
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public List<Answer> getListOfAnswersByPersonForTask(Task task, Person person) {
        List<Answer> answers = new ArrayList<>();

        try(PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL_PASSED_ANSWERS_BY_PERSON_FOR_TASK)) {
            statement.setInt(1,person.getId());
            statement.setInt(2,task.getId());
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

    private Answer getAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Answer.Builder()
                .setId(resultSet.getInt(ANSWER_ID_COLUMN_NAME))
                .setAnswerText(resultSet.getString(ANSWER_TEXT_COLUMN_NAME))
                .setIsCorrect(resultSet.getBoolean(ANSWER_IS_CORRECT_COLUMN_NAME))
                .build();
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
}
