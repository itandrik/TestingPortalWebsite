package com.javaweb.model.services.impl;

import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.SubjectDao;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;
import com.javaweb.model.services.SubjectService;

import java.util.List;

/**
 * @author Andrii Chernysh on 24-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class SubjectServiceImpl implements SubjectService{
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder{
        static final SubjectServiceImpl INSTANCE = new SubjectServiceImpl();
    }

    public static SubjectServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> result;
        try(DaoConnection connection = daoFactory.getConnection()){
            connection.begin();
            SubjectDao subjectDao = daoFactory.createSubjectDao(connection);
            result = subjectDao.getAll();
            connection.commit();
        }
        return result;
    }

    @Override
    public List<Test> getAllTestsForSubject(Subject subject) {
        List<Test> result;
        try(DaoConnection connection = daoFactory.getConnection()){
            connection.begin();
            SubjectDao subjectDao = daoFactory.createSubjectDao(connection);
            result = subjectDao.getListOfTestsForSubject(subject);
            connection.commit();
        }
        return result;
    }
}
