package fr.istic.tpjpa.shared;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersistenceManager {

	private static EntityManager manager = EntityManagerFactory.getInstance();
	
	public static void persist(Object... entities){
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		for(Object o : entities){
			manager.persist(o);
		}
		
		tx.commit();
	}
	
}
