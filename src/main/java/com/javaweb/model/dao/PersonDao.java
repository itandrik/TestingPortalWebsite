package com.javaweb.model.dao;

import com.javaweb.model.entity.person.Person;

import java.util.Optional;


public interface PersonDao extends GenericDao<Person> {
	Optional<Person> getPersonByLogin(String login);
}
