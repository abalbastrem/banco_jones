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

import bd.ConnectionManager;
import beans.Account;

public class AccountDAO {

	static Connection con = null;

	static Properties prop = new Properties();
	static InputStream input = RegistryDAO.class.getClassLoader().getResourceAsStream("sql.properties");

	public static List<Account> getAccounts(String dni) throws SQLException, IOException {

		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;

		if (input == null) {
			System.out.println("No se encontró el fichero");
		}

		prop.load(input);

		// CONSTRUYE EL QUERY
		stmt = con.prepareStatement(prop.getProperty("account.getaccounts"));
		stmt.setString(1, dni);

		ResultSet rs = (ResultSet) stmt.executeQuery();
		System.out.println("::::: RESULTSET: "+rs.toString());

		List<Account> accounts = new ArrayList<>();
		while (rs.next()) {
			System.out.println("::::: RS.IBAN: " + rs.getString("iban"));
			Account account = new Account();
			account.setCliente(dni);
			account.setIban(rs.getString("iban"));
			account.setSaldo(rs.getLong("saldo"));
			System.out.println("::::: ACCOUNT: "+account);
			accounts.add(account);
		}

		 System.out.println("::::: ACCOUNTS: "+accounts);

		stmt.close();
		con.close();

		return accounts;
	}

	public static boolean insertAccount(Account account) throws SQLException, IOException {

		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;

		if (input == null) {
			System.out.println("No se encontró el fichero");
		}

		prop.load(input);

		// CONSTRUYE EL QUERY
		stmt = con.prepareStatement(prop.getProperty("account.insert"));
		stmt.setString(1, account.getIban());
		stmt.setLong(2, account.getSaldo());
		stmt.setString(3, account.getCliente());

		stmt.executeUpdate();

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
			System.out.println("No se encontró el fichero");
		}

		prop.load(input);

		// CONSTRUYE EL QUERY
		stmt = con.prepareStatement(prop.getProperty("account.delete"));
		stmt.setString(1, account.getIban());
		stmt.setString(2, account.getCliente());

		stmt.executeUpdate();

		stmt.close();
		con.close();

		return true;

	}

}
