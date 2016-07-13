package com.j32bit.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.j32bit.bean.Person;
import com.j32bit.service.ServiceFacade;
import com.j32bit.servlet.Servlet;

@Path("/")
public class Rest {
	
	private Logger logger = LogManager.getLogger(Servlet.class);
	
	@Path("/getPerson")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Person getPerson(String TC){

		long tc = Long.valueOf(TC);
		logger.debug("getPerson param TC:"+TC+" value:"+tc);
		Person person = ServiceFacade.getInstance().getPerson(tc);
		
		logger.debug("getPerson is finished, Person: "+person);
		return person;		
	}
	
	@Path("/addPerson")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	public void addPerson(Person person){

		logger.debug("addPerson param Person:"+person);
		ServiceFacade.getInstance().storePerson(person);
		
		logger.debug("addPerson is finished");
	}
	
	@Path("/deletePerson")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
	public void deletePerson(String tc){

		logger.debug("deletePerson param tc:"+tc);
		long TC = Long.parseLong(tc);
		logger.debug("deletePerson long value:"+TC);
		ServiceFacade.getInstance().deletePerson(TC);
		
		logger.debug("deletePerson is finished");
	}
	
	
	@Path("/updatePerson")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
	public void updatePerson(Person person){

		logger.debug("updatePerson param Person:"+person);
		ServiceFacade.getInstance().updatePerson(person);
		
		logger.debug("updatePerson is finished");
	}

}
