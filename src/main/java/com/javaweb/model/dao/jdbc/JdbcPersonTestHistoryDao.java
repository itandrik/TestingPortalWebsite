package com.javaweb.model.dao.jdbc;

import com.javaweb.i18n.ErrorMessageKeys;
import com.javaweb.model.dao.PersonTestHistoryDao;
import com.javaweb.model.dao.exception.DaoException;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.history.Grade;
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
            "INSERT INTO person_test_history (person_id, test_id, end_time, grade," +
                    " count_of_passed_answers, count_of_all_correct_answers)" +
                    " VALUES (?,?,?,?,?,?)";
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
    private static final String SELECT_PERSON_HISTORY_FOR_TEST =
            "SELECT end_time, grade, count_of_passed_answers, count_of_all_correct_answers" +
                    " FROM Person_test_history" +
                    " WHERE person_id = ? AND test_id = ?";
    private static final String INSERT_ANSWER_FOR_PERSON =
            "INSERT INTO person_has_answer (person_id, answer_id) VALUES(?,?)";
    private static final String SELECT_LAST_INSERT_ID =
            "SELECT LAST_INSERT_ID() FROM person_test_history";
    private static final String LOGGER_ERROR_GRADE =
            "Error grade for ECTS scale. Check your grade or change grade scale";

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
            statement.setInt(5,personHistory.getGrade().getCountOfPassedCorrectAnswers());
            statement.setInt(6,personHistory.getGrade().getCountOfAllCorrectAnswers());

            statement.executeUpdate();
            Statement lastInsertIdStatement = connection.createStatement();
            ResultSet rs = lastInsertIdStatement.executeQuery(SELECT_LAST_INSERT_ID);
            rs.next();
            lastInsertId = rs.getInt(LAST_INSERT_ID);
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
    public Optional<PersonHistory> getPersonHistoryForTest(Person person, Test test) {
        Optional<PersonHistory> result = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_PERSON_HISTORY_FOR_TEST)) {
            statement.setInt(1, person.getId());
            statement.setInt(2, test.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grade personGrade = extractGradeFromResultSet(resultSet);
                result = Optional.of(new PersonHistory.Builder()
                        .setPerson(person)
                        .setTest(test)
                        .setGrade(personGrade)
                        .setEndTime(resultSet.getTimestamp(PERSON_HISTORY_END_TIME_COLUMN_NAME))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Grade extractGradeFromResultSet(ResultSet resultSet) throws SQLException {
        Grade personGrade = Grade.getECTSGrade(
                resultSet.getDouble(PERSON_HISTORY_GRADE_COLUMN_NAME))
                .orElseThrow(() -> new DaoException()
                        .addMessage(ErrorMessageKeys.ERROR_INCORRECT_GRADE)
                        .addLogMessage(LOGGER_ERROR_GRADE)
                        .setClassThrowsException(JdbcPersonTestHistoryDao.class));
        personGrade.setCountOfPassedCorrectAnswers(
                resultSet.getInt(PERSON_HISTORY_COUNT_OF_PASSED_ANSWERS));
        personGrade.setCountOfAllCorrectAnswers(
                resultSet.getInt(PERSON_HISTORY_COUNT_OF_ALL_CORRECT_ANSWERS));
        return personGrade;
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
                .setSubjectId(resultSet.getInt(TEST_SUBJECT_ID_NAME))
                .build();
    }

    @Override
    public List<Answer> getAllPassedAnswersByPersonForTest(Test test, Person person) {
        return getAnswersUsingTwoEntities(
                SELECT_ALL_PASSED_ANSWERS_BY_PERSON_FOR_TEST,
                test.getId(), person.getId()
        );
    }

    @Override
    public List<Answer> getListOfAnswersByPersonForTask(Task task, Person person) {
       return getAnswersUsingTwoEntities(
               SELECT_ALL_PASSED_ANSWERS_BY_PERSON_FOR_TASK, person.getId(),task.getId());
    }

    private List<Answer> getAnswersUsingTwoEntities(String query, int firstEntityId, int secondEntityId){
        List<Answer> answers = new ArrayList<>();

        try (PreparedStatement statement =
                     connection.prepareStatement(query)) {
            statement.setInt(1, firstEntityId);
            statement.setInt(2, secondEntityId);
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

    private Answer getAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Answer.Builder()
                .setId(resultSet.getInt(ANSWER_ID_COLUMN_NAME))
                .setAnswerText(resultSet.getString(ANSWER_TEXT_COLUMN_NAME))
                .setIsCorrect(resultSet.getBoolean(ANSWER_IS_CORRECT_COLUMN_NAME))
                .build();
    }

    @Override
    public void insertAnswerForPersonHistory(int answerId, int personId) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ANSWER_FOR_PERSON)) {
            statement.setInt(1, personId);
            statement.setInt(2, answerId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
