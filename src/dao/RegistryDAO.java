package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import bd.ConnectionManager;

public class RegistryDAO {

	static Connection con = null;

	public static void registry(String dni, String name, String surnames, String pass, String dob, String sex, String address, String phone) {
		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;

		try {
			Properties prop = new Properties();
			InputStream input = RegistryDAO.class.getClassLoader().getResourceAsStream("sql.properties");

			if (input == null) {
				System.out.println("No se encontró el fichero");
			}

			prop.load(input);

			// CONSTRUYE EL QUERY
			stmt = con.prepareStatement(prop.getProperty("cliente.insert"));
			stmt.setString(1, dni);
			stmt.setString(2, name);
			stmt.setString(3, surnames);
			stmt.setString(4, dob);
			stmt.setString(5, sex);
			stmt.setString(6, address);
			stmt.setString(7, phone);
			stmt.setString(8, pass);
	
			stmt.executeUpdate();
			/* No usamos rs porque no esperamos resultados de una BBDD
			/* cuando hacemos un insert. Si hay cualquier error,
			 * el método .executeUpdate() nos fará un error SQLException
			 */
		
		} catch (SQLException e) { // se usa porque hacemos un stmt (statement)
			e.printStackTrace();
		} catch (IOException e) { // Se usa porque accedemos a sql.properties
			e.printStackTrace();
		} finally { // Esto se ejecuta siempre
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
