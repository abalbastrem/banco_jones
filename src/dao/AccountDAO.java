package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import bd.ConnectionManager;
import beans.Account;
import servlets.InitServlet;

public class AccountDAO {
	
	static Logger logger = LogManager.getLogger(InitServlet.class);

	static Connection con = null;

	static Properties prop = new Properties();
	static InputStream input = RegistryDAO.class.getClassLoader().getResourceAsStream("sql.properties");

	public static List<Account> getAccounts(String dni) throws SQLException, IOException {

		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;

		if (input == null) {
			logger.error("No se encontró el fichero");
		}

		prop.load(input);

		// CONSTRUYE EL QUERY
		stmt = con.prepareStatement(prop.getProperty("account.getaccounts"));
		stmt.setString(1, dni);

		ResultSet rs = (ResultSet) stmt.executeQuery();
		
		logger.info("building list of accounts...");
		List<Account> accounts = new ArrayList<>();
		while (rs.next()) {
			Account account = new Account();
			account.setCliente(dni);
			account.setIban(rs.getString("iban"));
			account.setSaldo(rs.getLong("saldo"));
			accounts.add(account);
		}

		 logger.info("::::: ACCOUNTS: "+accounts);

		stmt.close();
		con.close();

		return accounts;
	}

	public static boolean insertAccount(Account account) throws SQLException, IOException {

		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;

		if (input == null) {
			logger.info("No se encontró el fichero");
		}

		prop.load(input);

		// CONSTRUYE EL QUERY
		logger.info("building query...");
		stmt = con.prepareStatement(prop.getProperty("account.insert"));
		stmt.setString(1, account.getIban());
		stmt.setDouble(2, account.getSaldo());
		stmt.setString(3, account.getCliente());

		stmt.executeUpdate();
		logger.info("query submitted to DB");

		stmt.close();
		con.close();

		return true;
	}

	public static boolean deleteAccount(Account account) throws SQLException, IOException {

		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;

		Properties prop = new Properties();
		InputStream input = RegistryDAO.class.getClassLoader().getResourceAsStream("sql.properties");

		if (input == null) {
			logger.error("No se encontró el fichero");
		}

		prop.load(input);

		// CONSTRUYE EL QUERY
		logger.info("building query...");
		stmt = con.prepareStatement(prop.getProperty("account.delete"));
		stmt.setString(1, account.getIban());
		stmt.setString(2, account.getCliente());

		stmt.executeUpdate();
		logger.info("query submitted to DB");

		stmt.close();
		con.close();

		return true;

	}
	
	public static boolean updateAccount(Account account) throws SQLException, IOException {

		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;

		Properties prop = new Properties();
		InputStream input = RegistryDAO.class.getClassLoader().getResourceAsStream("sql.properties");

		if (input == null) {
			logger.error("No se encontró el fichero");
		}

		prop.load(input);

		// CONSTRUYE EL QUERY
		logger.info("building query...");
		stmt = con.prepareStatement(prop.getProperty("account.update"));
		stmt.setDouble(1, account.getSaldo());
		stmt.setString(2, account.getIban());

		stmt.executeUpdate();
		logger.info("query submitted to DB");

		stmt.close();
		con.close();

		return true;

	}

}
