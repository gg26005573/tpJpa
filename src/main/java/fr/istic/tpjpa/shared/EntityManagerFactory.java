package fr.istic.tpjpa.shared;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

	private static EntityManager em = null;
	
	public static EntityManager getInstance(){
		if(em==null){
			javax.persistence.EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
			em = factory.createEntityManager();
		}
		return em;
	}
}
