package com.javaweb.model.services.impl;

import com.javaweb.i18n.ErrorMessageKeys;
import com.javaweb.model.dao.AnswerDao;
import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.PersonTestHistoryDao;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.history.Grade;
import com.javaweb.model.entity.history.PersonHistory;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.PersonTestHistoryService;
import com.javaweb.model.services.exception.ServiceException;

import java.sql.Timestamp;
import java.util.List;

import static com.javaweb.i18n.ErrorMessageKeys.ERROR_NO_SUCH_HISTORY;

/**
 * @author Andrii Chernysh on 01-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class PersonTestHistoryServiceImpl implements PersonTestHistoryService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static final int MAX_GRADE = 100;
    private static final String NO_SUCH_GRADE_ERROR_LOG = "No such grade :%f";
    private static final String NO_SUCH_HISTORY_ERROR_LOG = "No such grade :%f";

    private static final class Holder {
        static final PersonTestHistoryServiceImpl INSTANCE = new PersonTestHistoryServiceImpl();
    }

    private PersonTestHistoryServiceImpl() {
    }

    public static PersonTestHistoryServiceImpl getInstance() {
        return PersonTestHistoryServiceImpl.Holder.INSTANCE;
    }

    @Override
    public PersonHistory getPersonHistoryForTest(Person person, Test test) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PersonTestHistoryDao dao = daoFactory.createPersonTestHistoryDao(connection);
            return dao.getPersonHistoryForTest(person, test)
                    .orElseThrow(() -> new ServiceException()
                            .addLogMessage(NO_SUCH_HISTORY_ERROR_LOG)
                            .addMessage(ERROR_NO_SUCH_HISTORY));
        }
    }

    @Override
    public boolean isTestPassedByPerson(Test test, Person person) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PersonTestHistoryDao dao = daoFactory.createPersonTestHistoryDao(connection);
            List<Test> passedAnswers = dao.getListOfPassedTestsByPerson(person);
            return passedAnswers != null && passedAnswers.contains(test);
        }
    }

    private Grade calculateGradeOfTestPassedByPerson(Test test, Person person) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            AnswerDao answerDao = daoFactory.createAnswerDao(connection);
            PersonTestHistoryDao historyDao =
                    daoFactory.createPersonTestHistoryDao(connection);
            //TODO not to get all answers, but get quantity;
            List<Answer> passedAnswersInTest = historyDao.getAllPassedAnswersByPersonForTest(test, person);
            List<Answer> allAnswersForTest = answerDao.getListOfAnswersForTest(test);

            int countOfPassedCorrectAnswers = getCountOfCorrectAnswers(passedAnswersInTest);
            int countAllCorrectAnswers = getCountOfCorrectAnswers(allAnswersForTest);

            double gradeInMaxGradeFormat = countOfPassedCorrectAnswers /
                    (double) countAllCorrectAnswers * (double) MAX_GRADE;
            return Grade.getECTSGrade(gradeInMaxGradeFormat, countAllCorrectAnswers, countOfPassedCorrectAnswers)
                    .orElseThrow(() -> new ServiceException()
                            .addMessage(ErrorMessageKeys.ERROR_INCORRECT_GRADE)
                            .addLogMessage(String.format(NO_SUCH_GRADE_ERROR_LOG, gradeInMaxGradeFormat)));
        }
    }

    private int getCountOfCorrectAnswers(List<Answer> allAnswers) {
        int countOfCorrectAnswers = 0;
        for (Answer answer : allAnswers) {
            if (answer.getIsCorrect()) {
                countOfCorrectAnswers++;
            }
        }
        return countOfCorrectAnswers;
    }

    @Override
    public void insertAnswerForPersonHistory(int answerId, int personId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PersonTestHistoryDao personTestHistoryDao =
                    daoFactory.createPersonTestHistoryDao(connection);
            personTestHistoryDao.insertAnswerForPersonHistory(answerId, personId);
        }
    }

    @Override
    public void insertTestHistoryPassedByPerson(Test test, Person person) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PersonTestHistoryDao historyDao =
                    daoFactory.createPersonTestHistoryDao(connection);
            PersonHistory history = createPersonHistory(test, person);
            historyDao.insert(history);
        }
    }

    @Override
    public List<Answer> getListOfAnswersByPersonForTask(Person person, Task task) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PersonTestHistoryDao historyDao =
                    daoFactory.createPersonTestHistoryDao(connection);
            return historyDao.getListOfAnswersByPersonForTask(task, person);
        }
    }

    @Override
    public List<Test> getListOfTestsPassedByStudent(Person person) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PersonTestHistoryDao historyDao =
                    daoFactory.createPersonTestHistoryDao(connection);
            return historyDao.getListOfPassedTestsByPerson(person);
        }
    }

    private PersonHistory createPersonHistory(Test test, Person person) {
        Grade grade = calculateGradeOfTestPassedByPerson(test, person);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new PersonHistory.Builder()
                .setEndTime(timestamp)
                .setTest(test)
                .setPerson(person)
                .setGrade(grade)
                .build();
    }
}
