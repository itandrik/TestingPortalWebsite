package com.javaweb.model.services;

import com.javaweb.model.entity.Subject;

import java.util.List;

/**
 * @author Andrii Chernysh on 24-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public interface SubjectService {
    List<Subject> getAll();
    Subject getSubjectById(long id);
    void addNewSubject(Subject subject);
}
