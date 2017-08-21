package utilits;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Предоставляет коннект в БД
 */
public class ConnectingDataBaseProp {
	
	
	public ConnectingDataBaseProp() {
	}
	
	public static Connection getConnection() {
		Properties properties = new Properties();
		Connection connection = null;
		
		try {
			properties.load(new FileInputStream(new File("configDb.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			String url = String.valueOf(properties.get("url"));
			String login = String.valueOf(properties.get("user"));
			String password = String.valueOf(properties.get("password"));
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
