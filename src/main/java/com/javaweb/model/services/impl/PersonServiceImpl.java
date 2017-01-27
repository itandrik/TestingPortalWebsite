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

	private PersonServiceImpl() {
	}

	@Override
	public Optional<Person> login(String login, String password) {
		Optional<Person> result;

		try (DaoConnection connection = daoFactory.getConnection()) {
			PersonDao personDao = daoFactory.createPersonDao(connection);
			result = personDao.getPersonByLogin(login)
					.filter(person -> password.equals(person.getPassword()));
		}
		return result;
	}

	@Override
	public void register(Person person) {
		try(DaoConnection connection = daoFactory.getConnection()){
			connection.begin();
			PersonDao personDao = daoFactory.createPersonDao(connection);
			personDao.insert(person);
			connection.commit();
		}
	}

}
