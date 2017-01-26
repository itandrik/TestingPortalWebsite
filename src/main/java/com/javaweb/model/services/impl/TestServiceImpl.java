package com.javaweb.model.services.impl;

import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.TestDao;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;
import com.javaweb.model.services.TestService;

import java.util.List;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class TestServiceImpl implements TestService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder{
        static final TestServiceImpl INSTANCE = new TestServiceImpl();
    }

    public static TestServiceImpl getInstance() {
        return TestServiceImpl.Holder.INSTANCE;
    }

    @Override
    public List<Test> getAllTestsForSubject(Subject subject) {
        List<Test> result;
        try(DaoConnection connection = daoFactory.getConnection()){
            TestDao testDao = daoFactory.createTestDao(connection);
            result = testDao.getListOfTestsForSubject(subject);
        }
        return result;
    }
}
