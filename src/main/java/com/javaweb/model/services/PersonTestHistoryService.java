package com.javaweb.model.services;

import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;

/**
 * @author Andrii Chernysh on 31-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface PersonTestHistoryService {
    boolean isTestPassedByPerson(Test test, Person person);
    void insertAnswerForPersonHistory(int answerId, int personId);
    void insertTestHistoryPassedByPerson(Test test, Person person);
}
