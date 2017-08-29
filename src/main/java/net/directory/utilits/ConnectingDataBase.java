package net.directory.utilits;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class ConnectingDataBase {
	
	
	
	
	public ConnectingDataBase(){
	
	}
	
	public static Connection getConnection() {
		InitialContext initialContext = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			initialContext = new InitialContext();
			dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/postgres");
			connection = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
}
