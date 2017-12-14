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
import beans.Cliente;
import beans.Transaction;
import servlets.InitServlet;

public class TransactionDAO {
	
	static Logger logger = LogManager.getLogger(InitServlet.class);
	
	static Connection con = null;
	
	static Properties prop = new Properties();
	static InputStream input = TransactionDAO.class.getClassLoader().getResourceAsStream("sql.properties");
	
	
	public static boolean realizaTransaccion(String origin, String destination, double amount, Cliente c) throws Exception {
		con = ConnectionManager.getConnection();
		
		if (input == null) {
			logger.error("No se encontró el fichero");
		}

		prop.load(input);
		

		// INICIALITZAR LA TRANSACCIÓ
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setOrigin(origin);
		transaction.setDestination(destination);
		
		logger.info("::::: "+transaction);

		// QUERY PER TROBAR EL COMPTE ORIGEN
		logger.info("building query to find origin account...");
		Account cuentaOrigen = new Account();

		PreparedStatement queryCuentaOrigen = null;
		queryCuentaOrigen = con.prepareStatement(prop.getProperty("account.getoriginaccount"));
		queryCuentaOrigen.setString(1, transaction.getOrigin());
		queryCuentaOrigen.setString(2, c.getDni());
		ResultSet cuentaOrigenRS = queryCuentaOrigen.executeQuery();
		if (cuentaOrigenRS.next()) {
			cuentaOrigen.setCliente(cuentaOrigenRS.getString("cliente"));
			cuentaOrigen.setIban(cuentaOrigenRS.getString("iban"));
			cuentaOrigen.setSaldo(cuentaOrigenRS.getLong("saldo"));
		}
		
		// COMPROVAR SI EL COMPTE ORIGEN TÉ PROU LÍQUID
		logger.info("checking whether origin account has enough moneys...");
		if (!cuentaOrigen.isThereEnoughMoney(transaction.getAmount())) {
			return false;
		} else {
			logger.info("enough moneys confirmed");
		}
		
		
		// QUERY PER TROBAR EL COMPTE DESTÍ
		logger.info("building query to find destination account...");
		Account cuentaDest = new Account();

		PreparedStatement queryCuentaDest = null;
		queryCuentaDest = con.prepareStatement(prop.getProperty("account.getaccount"));
		queryCuentaDest.setString(1, transaction.getDestination());
		ResultSet cuentaDestRS = queryCuentaDest.executeQuery();
		if (cuentaDestRS.next()) {
			cuentaDest.setCliente(cuentaDestRS.getString("cliente"));
			cuentaDest.setIban(cuentaDestRS.getString("iban"));
			cuentaDest.setSaldo(cuentaDestRS.getLong("saldo"));
		}
		
		// TRANSFERENCIA
		logger.info("building transfer query...");
		PreparedStatement transfer = null;
		transfer = con.prepareStatement(prop.getProperty("transaction.newtransaction"));
		transfer.setDouble(1, transaction.getAmount());
		transfer.setString(2, transaction.getOrigin());
		transfer.setString(3, transaction.getDestination());

		transfer.executeUpdate();
		logger.info("transfer query submitted to DB...");

		// ACTUALIZA CANTIDAD EN CUENTA ORIGEN Y DESTINO
		logger.info("updating moneys in both origin and destination accounts...");
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo()-transaction.getAmount());
		cuentaDest.setSaldo(cuentaDest.getSaldo()+transaction.getAmount());
		logger.info("::::: is origin account updated? "+AccountDAO.updateAccount(cuentaOrigen));
		logger.info("::::: is destination account updated? "+AccountDAO.updateAccount(cuentaDest));
		
		return true;
	}
	
	public static List<Transaction> listaTransacciones(String iban) throws SQLException, IOException {
		con = ConnectionManager.getConnection();
		
		
		PreparedStatement getTransactionsByIban = null;
		if (input == null) {
			logger.error("No se encontró el fichero");
		}
		
		prop.load(input);
		
		// INICIALIZA LA LISTA
		List<Transaction> transactions = new ArrayList<>();
		
		// QUERY
		prop.getProperty("transaction.gettransactionsbyiban");
		getTransactionsByIban = con.prepareStatement(prop.getProperty("transaction.gettransactionsbyiban"));
		getTransactionsByIban.setString(1, iban);
		getTransactionsByIban.setString(2, iban);
		
		ResultSet transactionsByIbanRS = getTransactionsByIban.executeQuery();
		
		logger.info("building transaction list...");
		while (transactionsByIbanRS.next()) {
			Transaction transaction = new Transaction();
			transaction.setId(transactionsByIbanRS.getLong("id"));
			transaction.setDate(transactionsByIbanRS.getString("fecha"));
			transaction.setOrigin(transactionsByIbanRS.getString("origen"));
			transaction.setDestination(transactionsByIbanRS.getString("destino"));
			transaction.setAmount(transactionsByIbanRS.getDouble("cantidad"));
			transactions.add(transaction);
		}
		logger.info("transaction list done");

		return transactions;

	}

}
