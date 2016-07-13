package com.j32bit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.j32bit.properties.PropertiesService;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionHelper {

	private Logger logger;

	private MysqlDataSource dataSource;

	private boolean useProperties;

	public void init(Properties properties) {

		logger = LogManager.getLogger(PropertiesService.class);
		// properties.getProperty("dbUName") == null
		/*
		 * if (true) { try { // Look up our data source dataSource =
		 * (DataSource) (new
		 * InitialContext()).lookup("java:comp/env/jdbc/dbMysql"); logger.debug(
		 * "init is finished"); } catch (Exception e) { e.printStackTrace(); } }
		 * else {
		 */
		dataSource = new MysqlDataSource();

		String dbUname = properties.getProperty("dbUName");
		logger.debug("dbUname : " + dbUname);
		dataSource.setUser(dbUname);

		String dbPass = properties.getProperty("dbPass");
		dataSource.setPassword(dbPass);

		int dbPort = Integer.parseInt(properties.getProperty("dbPort"));
		dataSource.setPort(dbPort);

		String dbName = properties.getProperty("dbName");
		dataSource.setDatabaseName(dbName);

		String dbServerName = properties.getProperty("dbServerName");
		dataSource.setServerName(dbServerName);
		
		if(dbServerName ==null || dbName==null || dbPass==null || dbName==null )
			useProperties=false;
		else useProperties=true;

		logger.debug("used properties file");
	}

	private Connection getConnectionFromDatasource() throws NamingException, SQLException {
		logger.debug("DataSource java:comp/env/jdbc/dbMysql");
		Context envCtx = (Context) new InitialContext();
		// Look up our data source
		DataSource ds = (DataSource) envCtx.lookup("java:comp/env/jdbc/dbMysql");
		Connection conn = ds.getConnection();
		logger.debug("Connection taken");
		return conn;
	}

	public Connection getConnection() throws ClassNotFoundException {

		Connection connection = null;

		logger.debug("getConnection is started");

		try {
			if(useProperties)
				connection = dataSource.getConnection();
			else connection = getConnectionFromDatasource();
			logger.debug("getConnection is finished");
		} catch (Exception e) {
			logger.error("getConnection error");
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection(Connection con) throws SQLException {
		if (con != null)
			con.close();
		logger.debug("closeConnection is finished");
	}

	public void closeStatement(Statement st) throws SQLException {
		if (st != null)
			st.close();
	}

	public void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
	}
}
