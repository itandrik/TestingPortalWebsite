package com.javaweb.model.dao;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.history.PersonHistory;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.task.Task;

import java.util.List;
import java.util.Optional;

/**
 * @author Andrii Chernysh on 31-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface PersonTestHistoryDao extends GenericDao<PersonHistory>{
    Optional<PersonHistory> getPersonHistoryForTest(Person person, Test test);
    List<Test> getListOfPassedTestsByPerson(Person person);
    List<Answer> getAllPassedAnswersByPersonForTest(Test test, Person person);
    List<Answer> getListOfAnswersByPersonForTask(Task task, Person person);
    void insertAnswerForPersonHistory(int answerId, int personId);
}
