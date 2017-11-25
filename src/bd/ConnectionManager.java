package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	
	static Connection con;
	
	public static Connection getConnection() {
		
		javax.naming.InitialContext ctx;
		
		try {
			ctx = new javax.naming.InitialContext();
			DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/banco");
			con = ds.getConnection();
		
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return con;
		
		
		// validar dades amb Postgresql
//		Connection con = null;
		
			
//			Class.forName("org.postgresql.Driver");
//			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","albert","jupiter");
//					
//		} catch (Exception e) {
//			
//			System.out.println("falla la connexió");
//			e.printStackTrace();
//		}
		
//		return con;
	}

}
