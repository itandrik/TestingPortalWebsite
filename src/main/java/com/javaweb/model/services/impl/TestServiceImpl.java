package com.javaweb.model.services.impl;

import com.javaweb.i18n.ErrorMessageKeys;
import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.TestDao;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;
import com.javaweb.model.services.TestService;
import com.javaweb.model.services.exception.ServiceException;

import java.util.List;

/**
 * @author Andrii Chernysh on 26-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class TestServiceImpl implements TestService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static final String NO_SUCH_TEST_LOG = "No such test with id %d!";

    private static class Holder {
        static final TestServiceImpl INSTANCE = new TestServiceImpl();
    }

    public static TestServiceImpl getInstance() {
        return TestServiceImpl.Holder.INSTANCE;
    }

    private TestServiceImpl() {
    }

    @Override
    public List<Test> getAllTestsForSubject(Subject subject) {
        List<Test> result;
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestDao testDao = daoFactory.createTestDao(connection);
            result = testDao.getListOfTestsForSubject(subject);
        }
        return result;
    }

    @Override
    public Test getTestById(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestDao testDao = daoFactory.createTestDao(connection);
            return testDao.getById(id)
                    .orElseThrow(() -> new ServiceException()
                            .addMessage(ErrorMessageKeys.ERROR_NO_SUCH_TEST)
                            .addLogMessage(String.format(NO_SUCH_TEST_LOG, id)));

        }
    }

    @Override
    public void addTestWithName(String name) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            TestDao testDao = daoFactory.createTestDao(connection);
            Test test = new Test();
            test.setNameOfTest(name);
            testDao.insert(test);
        }
    }

    @Override
    public void changeTestWithId(int testId) {

    }
}
