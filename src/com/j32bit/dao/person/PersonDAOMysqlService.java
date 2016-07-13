package com.j32bit.dao.person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.j32bit.bean.Person;
import com.j32bit.dao.ConnectionHelper;

public class PersonDAOMysqlService extends ConnectionHelper implements IPersonDAOService {

	private Logger logger = LogManager.getLogger(PersonDAOMysqlService.class);

	@Override
	public void init(Properties properties) {
		super.init(properties);
	}

	@Override
	public Person getPerson(long tc) {

		logger.debug("getPerson is started");
		Person person = null;

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pst = null;

		try {
			person = new Person();
			String querry = "SELECT * FROM PERSON WHERE TC=?";
			con = getConnection();
			pst = con.prepareStatement(querry);
			pst.setLong(1, tc);
			rs = pst.executeQuery();
			while (rs.next()) {
				person.setName(rs.getString("NAME"));
				person.setTC(rs.getLong("TC"));
				person.setPhone(rs.getString("PHONE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeResultSet(rs);
				closeStatement(pst);
				closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.debug("getPerson is finished");
		return person;
	}

	@Override
	public void storePerson(Person person) {

		logger.debug("storePerson is started");
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String querry = "INSERT INTO PERSON (TC,NAME,PHONE) VALUES(?,?,?)";

		try {
			con = getConnection();
			pst = con.prepareStatement(querry);
			pst.setLong(1, person.getTC());
			// System.out.println(person.getTC()+person.getName()+person.getPhone());
			pst.setString(2, person.getName());
			pst.setString(3, person.getPhone());
			pst.executeUpdate();
			// pst.executeQuery();
			closeResultSet(rs);
			closeStatement(pst);
			closeConnection(con);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
		}
		logger.debug("storePerson is finished");
	}

	@Override
	public void deletePerson(long tc) {

		logger.debug("deletePerson is started");

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		String querry = "DELETE FROM PERSON WHERE TC=?";

		try {
			con = getConnection();
			pst = con.prepareStatement(querry);
			pst.setLong(1, tc);
			pst.executeUpdate();

			closeResultSet(rs);
			closeStatement(st);
			closeConnection(con);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
		}

		logger.debug("deletePerson is finished");
	}

	@Override
	public Person updatePerson(Person person) {

		logger.debug("updatePerson is started");

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String querry = "UPDATE PERSON SET NAME=?,PHONE=? WHERE TC=?";
		PreparedStatement pst = null;

		try {
			con = getConnection();
			pst = con.prepareStatement(querry);

			pst.setString(1, person.getName());
			pst.setString(2, person.getPhone());
			pst.setLong(3, person.getTC());
			pst.executeUpdate();

			closeResultSet(rs);
			closeStatement(st);
			closeConnection(con);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
		}

		logger.debug("updatePerson is finished");
		return null;
	}

}
