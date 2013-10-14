package fr.istic.tpjpa.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.tpjpa.shared.Address;
import fr.istic.tpjpa.shared.EntityManagerFactory;
import fr.istic.tpjpa.shared.Home;
import fr.istic.tpjpa.shared.Person;

@Path("/services")
public class Admin {
	
	private EntityManager manager;
	
	public Admin() {
		manager = EntityManagerFactory.getInstance();
		
//		JpaTest tester = new JpaTest(manager);
		
		
//		EntityTransaction tx = manager.getTransaction();
//		tx.begin();
//
//		// creating entities
//		try{
//			tester.createStuff();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//		// persist entity
//
//		tx.commit();
	}
	
	@GET @Path("createperson/{name}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Long createPerson(@PathParam("name") String name){
		
		Person p = new Person(name);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(p);
		tx.commit();
		
		return p.getId();
	}
	
	@GET @Path("person/{name}")
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Person> getPerson(@PathParam("name") String name){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
		Root<Person> from = query.from(Person.class);
		query.select(from).where(from.get("name").in(name));
		return manager.createQuery(query).getResultList();
	}
	
	@GET @Path("personbyid/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Person> getPerson(@PathParam("id") Long id){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
		Root<Person> from = query.from(Person.class);
		query.select(from).where(from.get("id").in(id));
		return manager.createQuery(query).getResultList();
	}
	
	@GET @Path("/createhome")
    @Produces({ MediaType.APPLICATION_JSON })
	public Long createHome(@QueryParam("personid") Long id, @QueryParam("address") String address){
		
		// We get the person
		List<Person> persons = getPerson(id);
		if(persons.isEmpty())
			return -1L;
		Person p = persons.get(0);
		
		// We create the home of this person
		
		Address a = new Address(address);
		Home h = new Home(a);
		h.setOwner(p);
		p.addHome(h);
		
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		manager.persist(a);
		manager.persist(h);
		tx.commit();
		
		return h.getId();
	}
	
	@GET @Path("homebyid/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Home> getHome(@PathParam("id") Long id){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Home> query = criteriaBuilder.createQuery(Home.class);
		Root<Home> from = query.from(Home.class);
		query.select(from).where(from.get("id").in(id));
		return manager.createQuery(query).getResultList();
	}
	
	
}
