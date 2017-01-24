package com.javaweb.model.services;

import java.util.Optional;

import com.javaweb.model.entity.person.Person;

public interface PersonService {
	Optional<Person> login(String login , String password);
}
