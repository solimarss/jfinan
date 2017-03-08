package br.com.solimar.ofx4j.core;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.solimar.ofx4j.persistence.ConnectionDataBase;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("OK");
		
		Connection connection = ConnectionDataBase.getConnection();

	}

}
