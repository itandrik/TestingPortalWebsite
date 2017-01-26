package com.javaweb.model.services.impl;

import com.javaweb.model.dao.AnswerDao;
import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.AnswerService;

import java.util.List;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class AnswerServiceImpl implements AnswerService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static final class Holder{
        static final AnswerServiceImpl INSTANCE = new AnswerServiceImpl();
    }

    public static AnswerServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public List<Answer> getListOfAnswersForTask(Task task) {
        List<Answer> result;
        try(DaoConnection connection = daoFactory.getConnection()) {
            AnswerDao answerDao = daoFactory.createAnswerDao(connection);
            result = answerDao.getListOfAnswersForTask(task);
        }
        return result;
    }
}
