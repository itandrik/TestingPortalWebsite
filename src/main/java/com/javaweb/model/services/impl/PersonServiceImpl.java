package com.javaweb.model.services.impl;

import com.javaweb.i18n.ErrorMessageKeys;
import com.javaweb.model.dao.DaoConnection;
import com.javaweb.model.dao.DaoFactory;
import com.javaweb.model.dao.PersonDao;
import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.util.LoginData;
import com.javaweb.model.services.PersonService;
import com.javaweb.model.services.exception.ServiceException;

public class PersonServiceImpl implements PersonService {
	private static final String LOGGER_NO_SUCH_LOGIN_OR_PASSWORD =
			"Login failed : no such login or password in the database" +
					" - LOGIN : %s, PASSWORD : %s";
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
	public Person authenticate(LoginData loginData) {
		Person result;

		try (DaoConnection connection = daoFactory.getConnection()) {
			PersonDao personDao = daoFactory.createPersonDao(connection);
			result = personDao.getPersonByLogin(loginData.getLogin())
					.filter(person -> loginData.getPassword().equals(person.getPassword()))
			.orElseThrow(() -> new ServiceException()
					.addMessage(ErrorMessageKeys.ERROR_INCORRECT_LOGIN_DATA)
					.addLogMessage(LOGGER_NO_SUCH_LOGIN_OR_PASSWORD));
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
