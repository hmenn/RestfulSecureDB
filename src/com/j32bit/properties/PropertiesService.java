package com.j32bit.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertiesService {

	private Logger logger = LogManager.getLogger(PropertiesService.class);
	
	public  void createPropertiesFile(String fileName) {

		Properties properties = new Properties();
		OutputStream outStream = null;

		try {
			outStream = new FileOutputStream(fileName);
			properties.setProperty("dbDriver", "com.mysql.jdbc.Driver");
			properties.setProperty("dbUsername", "hmenn");
			properties.setProperty("dbPassword", "9415");
			properties.setProperty("dbURL", "jdbc:mysql://localhost:3306/Users");
			properties.store(outStream, "First props");

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (outStream != null) {
			}
			try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public  void readPropertiesFile(String fileName) {			
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(fileName);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			prop.getProperty("database");
			prop.getProperty("dbuser");
			prop.getProperty("dbpassword");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.debug("PropertiesService.readProperties file is finished");
	}	
}
