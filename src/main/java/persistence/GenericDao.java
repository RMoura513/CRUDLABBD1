package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {

	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		
		String hostName = "localhost";
		String dbName = "exercicio1";
		String user = "anderson";
		String senha = "123321456";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:62614;databaseName=%s;user=%s;password=%s",
				hostName, dbName, user, senha));
		return c;
	}
	
	
}
