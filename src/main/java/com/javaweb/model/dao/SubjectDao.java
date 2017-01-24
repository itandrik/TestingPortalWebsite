package com.javaweb.model.dao;

import java.util.List;
import java.util.Optional;

import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;

public interface SubjectDao extends GenericDao<SubjectDao> {
	Optional<Subject> getSubjectByName(String name);
	List<Test> getListOfTestsForSubject(long id);
}
