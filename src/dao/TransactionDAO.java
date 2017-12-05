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
import beans.Cliente;
import beans.Transaction;

public class TransactionDAO {
	
	static Connection con = null;
	
	static Properties prop = new Properties();
	static InputStream input = TransactionDAO.class.getClassLoader().getResourceAsStream("sql.properties");
	
	
	public static boolean realizaTransaccion(String origin, String destination, long amount, Cliente c) throws Exception {
		if (input == null) {
			System.out.println("No se encontró el fichero");
		}
		
		prop.load(input);
		
		
		// INICIALITZAR LA TRANSACCIÓ
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setOrigin(origin);
		transaction.setDestination(destination);
		
		// QUERY PER TROBAR EL COMPTE ORIGEN
		Account cuentaOrigen = new Account();
		
		PreparedStatement queryCuentaOrigen = null;
		queryCuentaOrigen = con.prepareStatement(prop.getProperty("cuenta.getaccount"));
		queryCuentaOrigen.setString(1, transaction.getOrigin());
		queryCuentaOrigen.setString(2, c.getDni());
		ResultSet cuentaOrigenRS = queryCuentaOrigen.executeQuery();
		if (cuentaOrigenRS.next()) {
			cuentaOrigen.setCliente(cuentaOrigenRS.getString("cliente"));
			cuentaOrigen.setIban(cuentaOrigenRS.getString("iban"));
			cuentaOrigen.setSaldo(cuentaOrigenRS.getLong("saldo"));
		}
		
		System.out.println("::::: CUENTA ORIGEN: "+cuentaOrigen);
		
		// COMPROVAR SI EL COMPTE ORIGEN TÉ PROU LÍQUID
		if (!cuentaOrigen.isThereEnoughMoney(transaction.getAmount())) {
			return false;
		}
		
		
		// QUERY PER TROBAR EL COMPTE DESTÍ
		Account cuentaDest = new Account();

		PreparedStatement queryCuentaDest = null;
		queryCuentaDest = con.prepareStatement(prop.getProperty("cuenta.getaccount"));
		queryCuentaDest.setString(1, transaction.getOrigin());
		queryCuentaDest.setString(2, c.getDni());
		ResultSet cuentaDestRS = queryCuentaDest.executeQuery();
		if (cuentaDestRS.next()) {
			cuentaDest.setCliente(cuentaDestRS.getString("cliente"));
			cuentaDest.setIban(cuentaDestRS.getString("iban"));
			cuentaDest.setSaldo(cuentaDestRS.getLong("saldo"));
		}
		
		// TRANSFERENCIA
		PreparedStatement transfer = null;
		transfer = con.prepareStatement(prop.getProperty("transaction.newtransaction"));
		transfer.setLong(1, transaction.getAmount());
		transfer.setString(2, transaction.getOrigin());
		transfer.setString(3, transaction.getDestination());
		
		transfer.executeUpdate();
		
		return true;
	}
	
	public static List<Transaction> listaTransacciones(String iban) throws SQLException, IOException {
		con = ConnectionManager.getConnection();
		
		
		PreparedStatement getTransactionsByIban = null;
		if (input == null) {
			System.out.println("No se encontró el fichero");
		}
		
		prop.load(input);
		
		System.out.println("::::: DAO IBAN: "+iban);
		
		// INICIALIZA LA LISTA
		List<Transaction> transactions = new ArrayList<>();
		
		// QUERY
		System.out.println(prop.getProperty("transaction.gettransactionsbyiban"));
		getTransactionsByIban = con.prepareStatement(prop.getProperty("transaction.gettransactionsbyiban"));
		getTransactionsByIban.setString(1, iban);
		getTransactionsByIban.setString(2, iban);
		
		System.out.println("::::: STMT: "+getTransactionsByIban);
		
		ResultSet transactionsByIbanRS = getTransactionsByIban.executeQuery();
		
		System.out.println("::::: RS: "+transactionsByIbanRS);
		
		while (transactionsByIbanRS.next()) {
			Transaction transaction = new Transaction();
			transaction.setId(transactionsByIbanRS.getLong("id"));
			transaction.setDate(transactionsByIbanRS.getString("fecha"));
			transaction.setOrigin(transactionsByIbanRS.getString("origen"));
			transaction.setDestination(transactionsByIbanRS.getString("destino"));
			transactions.add(transaction);
		}

		System.out.println("::::: TRANSACTIONS: "+transactions);

		return transactions;

	}

}
