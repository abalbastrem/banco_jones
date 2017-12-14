package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import bd.ConnectionManager;
import beans.Cliente;
import servlets.InitServlet;

public class ClienteDAO {
	
	static Logger logger = LogManager.getLogger(InitServlet.class);
	
	static Connection con = null;
	
	public static Cliente loginValid(String user, String pass) {
		Cliente c = new Cliente();
		con = ConnectionManager.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Properties prop = new Properties();
			InputStream input = ClienteDAO.class.getClassLoader().getResourceAsStream("sql.properties");
			
			if ( input == null ) {
				logger.error("No se encontró el fichero");
			}
			
			prop.load(input);
			
			// CONSTRUYE EL QUERY
			logger.info("building query...");
			stmt = con.prepareStatement(prop.getProperty("cliente.login"));
			stmt.setString(1, pass);
			stmt.setString(2, user);
			
			rs = (ResultSet) stmt.executeQuery();
			
			if (rs.next()) {
				c.setName(rs.getString("nombre"));
				c.setDni(rs.getString("dni"));
				c.setSurnames(rs.getString("apellidos"));
				c.setDob(rs.getString("fecha_nacimiento"));
				c.setSex(rs.getString("sexo"));
				c.setAddress(rs.getString("direccion"));
				c.setPhone(rs.getString("telefono"));
				c.setPass(rs.getString("contrasena"));
				c.setValid(true);
			} else {
				c.setValid(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // Esto se ejecuta siempre
			if (rs != null) {
				try {
					rs.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return c;
	}

}
