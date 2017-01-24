package com.javaweb.model.dao;

import java.util.Optional;

import com.javaweb.model.entity.person.Person;


public interface PersonDao extends GenericDao<Person> {
	Optional<Person> getPersonByLogin(String login);
}
