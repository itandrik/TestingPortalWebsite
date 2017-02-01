package com.javaweb.model.dao;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.history.PersonHistory;

import java.util.List;

/**
 * @author Andrii Chernysh on 31-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface PersonTestHistoryDao extends GenericDao<PersonHistory>{
    List<Test> getListOfPassedTestsByPerson(Person person);
    List<Answer> getAllPassedAnswersByPersonForTest(Test test, Person person);
    void insertAnswerForPersonHistory(int answerId, int personId);
}
