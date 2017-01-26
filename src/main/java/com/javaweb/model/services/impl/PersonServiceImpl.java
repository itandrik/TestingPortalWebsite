package com.javaweb.model.services.impl;

import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.PersonDao;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.services.PersonService;

import java.util.Optional;

public class PersonServiceImpl implements PersonService {

	private DaoFactory daoFactory = DaoFactory.getInstance();

	private static class Holder {
		static final PersonServiceImpl INSTANCE = new PersonServiceImpl();
	}

	public static PersonServiceImpl getInstance() {
		return Holder.INSTANCE;
	}

	@Override
	public Optional<Person> login(String login, String password) {
		Optional<Person> result;

		try (DaoConnection connection = daoFactory.getConnection()) {
			PersonDao dao = daoFactory.createPersonDao(connection);
			result = dao.getPersonByLogin(login)
					.filter(person -> password.equals(person.getPassword()));
		}
		return result;
	}

}
