package com.javaweb.model.services;

import com.javaweb.model.entity.person.Person;

import java.util.Optional;

public interface PersonService {
	Optional<Person> login(String login , String password);
	void register(Person person);
}
