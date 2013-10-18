package fr.istic.tpjpa.server;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.tpjpa.shared.Address;
import fr.istic.tpjpa.shared.Home;
import fr.istic.tpjpa.shared.PersistenceManager;
import fr.istic.tpjpa.shared.Person;

@Path("/person")
public class PersonResource {
	
		
	@GET @Path("/create/{name}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Long createPerson(@PathParam("name") String name){
		
		Person p = new Person(name);
		
		PersistenceManager.persist(p);
		
		return p.getId();
	}
	
	@GET @Path("/named/{name}")
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Person> getPerson(@PathParam("name") String name){
		return Person.getPersonsByName(name);
	}
	
	@GET @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Person getPerson(@PathParam("id") Long id){
		System.err.println("*** *** Getting Person #"+id+" *** ***");
		return Person.getPersonByID(id);
	}
	
	@GET @Path("/{id}/home")
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Home> getHome(@PathParam("id") Long id){
		return Home.getHomeByOwner(id);
	}
	
	@GET @Path("/{id}/createhome/{address}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Long createHome(@PathParam("id") Long id, @PathParam("address") String address){
		
		// We get the person
		Person p;
		try {
			System.err.println("*** *** Getting Person #"+id+" *** ***");
			p = Person.getPersonByID(id);
		} catch (Exception e) {
			e.getStackTrace();
			return -1L;
		}
		
		// We create the home of this person
		
		Address a = new Address(address);
		Home h = new Home(a);
		h.setOwner(p);
		p.addHome(h);
		
		
		PersistenceManager.persist(a, h);
		
		return h.getId();
	}
}
