package com.javaweb.model.services;

import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.util.LoginData;

public interface PersonService {
	Person authenticate(LoginData loginData);
	void register(Person person);
}
