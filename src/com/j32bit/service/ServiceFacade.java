package com.j32bit.service;

import java.util.Properties;

import com.j32bit.bean.Person;
import com.j32bit.dao.person.IPersonDAOService;
import com.j32bit.dao.person.PersonDAOMysqlService;

public class ServiceFacade {
	private static ServiceFacade instance = null;
	private  IPersonDAOService personDAOService;
	
	private ServiceFacade(){}
	
	public static ServiceFacade getInstance(){
		if(instance==null)
			instance= new ServiceFacade();
		return instance;
	}
	
	
	public void init(Properties properties){
		personDAOService = new PersonDAOMysqlService();
		personDAOService.init(properties);
	}
	
	public  void stop(){}
	
	public  Person getPerson(long tc){
		return personDAOService.getPerson(tc);		
	}
	
	public  void storePerson(Person person){
		personDAOService.storePerson(person);
	}
	
	public  void deletePerson(long tc){
		personDAOService.deletePerson(tc);
	}
	
	public  void updatePerson(Person person){
		personDAOService.updatePerson(person);
	}
	
	

}
