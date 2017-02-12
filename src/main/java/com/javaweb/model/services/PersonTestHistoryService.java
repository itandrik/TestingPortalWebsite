package com.javaweb.model.services;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.history.PersonHistory;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.task.Task;

import java.util.List;

/**
 * @author Andrii Chernysh on 31-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface PersonTestHistoryService {
    PersonHistory getPersonHistoryForTest(Person person, Test test);
    boolean isTestPassedByPerson(Test test, Person person);
    void insertAnswerForPersonHistory(int answerId, int personId);
    void insertTestHistoryPassedByPerson(Test test, Person person);
    List<Answer> getListOfAnswersByPersonForTask(Person person, Task task);
    List<Test> getListOfTestsPassedByStudent(Person person);
}
