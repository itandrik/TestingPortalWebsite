package com.javaweb.model.dao;

import com.javaweb.model.entity.Subject;
import com.javaweb.model.entity.Test;

import java.util.List;

public interface TestDao extends GenericDao<Test>{
	List<Test> getListOfTestsForSubject(Subject subject);
}
