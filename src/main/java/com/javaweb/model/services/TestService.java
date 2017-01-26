package com.javaweb.model.services;

import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;

import java.util.List;
import java.util.Optional;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface TestService {
    List<Test> getAllTestsForSubject(Subject subject);
    Optional<Test> getTestById(int id);
}
