package com.j32bit.dao.person;

import java.util.Properties;

import com.j32bit.bean.Person;

public interface IPersonDAOService {
	
	void init(Properties properties);
	Person getPerson(long tc);
	void storePerson(Person person);
	void deletePerson(long tc);
	Person updatePerson(Person person);
}
