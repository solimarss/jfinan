package br.com.solimar.ofx4j.core;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.solimar.ofx4j.persistence.ConnectionDataBase;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("OK");
		
		//Connection connection = ConnectionDataBase.getConnection();
		//connection.close();
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jfinan");  
		EntityManager em = emf.createEntityManager();                               
		//EntityTransaction tx = em.getTransaction();                                 

		/*Test test = em.find(Test.class, 1);                                         
		if (test == null) {                                                         
		  test = new Test();                                                        
		  test.id = 1;                                                              
		  test.data = "a";                                                          

		  tx.begin();                                                               
		  em.persist(test);                                                         
		  tx.commit();                                                              
		}   */                                                                        

		//tx.commit();
		//System.out.format("Test{id=%s, data=%s}\n", test.id, test.data);            

		em.close();                                                                 
		emf.close();    

	}

}
