package com.j32bit.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.j32bit.bean.Person;
import com.j32bit.service.ServiceFacade;


public class Servlet extends HttpServlet {
	
	private Logger logger = LogManager.getLogger(Servlet.class);
	private static final long serialVersionUID = 1L;
	public void init(){
		logger.debug("servlet.init is finished");	
	}
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		long tc = Long.valueOf(request.getParameter("TC"));
		Person person = ServiceFacade.getInstance().getPerson(tc);
		pw.println(person);
		pw.close();	
	}
	
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException{
		
		response.setContentType("text/html");
				
		PrintWriter pw = response.getWriter();	
		
		long newTC = Long.valueOf(request.getParameter("TC"));
		String newName = request.getParameter("NAME");
		String newPhoneNum = request.getParameter("PHONE");
		
		Person newPerson = new Person(newTC,newName,newPhoneNum);
		ServiceFacade.getInstance().storePerson(newPerson);
		
		pw.println("doPost finished. New user added : " + newPerson);
		pw.close();
	}
	
	public void doDelete(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException{
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();	

		long deleteTC = Long.valueOf(request.getParameter("TC"));
		
		ServiceFacade.getInstance().deletePerson(deleteTC);
		
		pw.println("doDelete finished. user deleted");
		pw.close();
	}
	
	public void doPut(HttpServletRequest request,
			HttpServletResponse response)throws IOException,ServletException{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
	    StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str;
	    while( (str = br.readLine()) != null ){
	        sb.append(str);
	    }   
		
		System.out.println(sb.toString());
		long newTC = Long.valueOf(request.getParameter("PERSON"));
		
		String newName = request.getParameter("NAME");
		String newPhoneNum = request.getParameter("PHONE");
		ServiceFacade.getInstance().updatePerson(new Person(newTC,newName,newPhoneNum));
		pw.println("doPut finished");
		pw.close();
	}
}
