package com.javaweb.model.services;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.task.Task;

import java.util.List;

/**
 * @author Andrii Chernysh on 31-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface PersonTestHistoryService {
    boolean isTestPassedByPerson(Test test, Person person);
    void insertAnswerForPersonHistory(int answerId, int personId);
    void insertTestHistoryPassedByPerson(Test test, Person person);
    List<Answer> getListOfAnswersByPersonForTask(Person person, Task task);
}
