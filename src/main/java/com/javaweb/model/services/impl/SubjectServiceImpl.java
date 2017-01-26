package com.javaweb.model.services.impl;

import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.SubjectDao;
import com.javaweb.model.entity.Subject;
import com.javaweb.model.services.SubjectService;

import java.util.List;
import java.util.Optional;

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
            SubjectDao subjectDao = daoFactory.createSubjectDao(connection);
            result = subjectDao.getAll();
        }
        return result;
    }

    @Override
    public Subject getSubjectById(long id) {
        Subject result;
        try(DaoConnection connection = daoFactory.getConnection()){
            SubjectDao subjectDao = daoFactory.createSubjectDao(connection);
            Optional<Subject> optionalSubject = subjectDao.getById((int)id);
            result = optionalSubject.map(subject -> new Subject.Builder()
                    .setId(subject.getId())
                    .setName(subject.getNameOfSubject())
                    .build()).orElseThrow(RuntimeException::new);
        }
        return result;
    }

}
