package com.javaweb.model.services;

import com.javaweb.model.entity.Test;

import java.util.List;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface TestService {
    List<Test> getAllTestsForSubjectWithId(int subjectId);
    Test getTestById(int id);
    void addTest(Test test);
}
