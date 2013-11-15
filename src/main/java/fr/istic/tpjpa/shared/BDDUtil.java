package fr.istic.tpjpa.shared;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class BDDUtil {

	public static List<Person> getPersonsByName(String name){
		EntityManager manager = EntityManagerFactory.getInstance();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
		Root<Person> from = query.from(Person.class);
		query.select(from).where(from.get("name").in(name));
		return manager.createQuery(query).getResultList();
	}
	
	public static Person getPersonByID(Long id){
		EntityManager manager = EntityManagerFactory.getInstance();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
		Root<Person> from = query.from(Person.class);
		query.select(from).where(from.get("id").in(id));
		Person res = manager.createQuery(query).getSingleResult();
		return res;
	}
	
	public static List<Home> getHomeByOwner(Long id){
		EntityManager manager = EntityManagerFactory.getInstance();
		List<Home> results = manager.createQuery("SELECT h FROM Home AS h WHERE owner="+id, Home.class).getResultList();
		return results;
	}
	
	public static Home getHomeByID(Long id){
		EntityManager manager = EntityManagerFactory.getInstance();
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Home> query = criteriaBuilder.createQuery(Home.class);
		Root<Home> from = query.from(Home.class);
		query.select(from).where(from.get("id").in(id));
		return manager.createQuery(query).getSingleResult();
	}
	
}
