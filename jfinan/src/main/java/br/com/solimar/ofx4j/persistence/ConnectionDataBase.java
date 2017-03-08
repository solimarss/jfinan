package br.com.solimar.ofx4j.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

	private static final String USER = "root";
	private static final String PASS = "JC2000";
	private static final String URL = "jdbc:derby:jfinandb;create=true;user=" + USER + ";password=" + PASS;
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		System.out.println("Conectando ao Banco de Dados");
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL);

	}
}
