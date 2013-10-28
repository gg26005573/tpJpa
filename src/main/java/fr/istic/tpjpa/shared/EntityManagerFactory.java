package fr.istic.tpjpa.shared;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

	private static EntityManager em = null;
	
	public static EntityManager getInstance(){
		if(em==null){
			javax.persistence.EntityManagerFactory factory = Persistence.createEntityManagerFactory("tpJpa-pu");
			if(factory==null)
				System.err.println("fr.istic.tpjpa.shared.EntityManagerFactory.getINstance(): error while creating an javax.persistence.EntityManagerFactory");
			em = factory.createEntityManager();
		}
		return em;
	}
}
