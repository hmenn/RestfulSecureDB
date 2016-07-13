package com.j32bit.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.j32bit.service.ServiceFacade;

public class ContextListener implements ServletContextListener {

	private Logger logger = LogManager.getLogger(ContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("contexDestroyed is finished");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.debug("contextInitialized is started");
		
		Properties properties = readPropertiesFile("cfg.properties");
		ServiceFacade.getInstance().init(properties);
		logger.debug("contextInitialized is finished");
	}

	public Properties readPropertiesFile(String fileName) {

		Properties prop = null;

		InputStream input = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream(fileName);
			// input = new FileInputStream(fileName);

			// load a properties file
			prop = new Properties();
			prop.load(input);

			logger.debug("dbUserName : "+prop.getProperty("dbUName"));
			
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
		logger.debug("readProperties file is finished");
		return prop;
	}
}
