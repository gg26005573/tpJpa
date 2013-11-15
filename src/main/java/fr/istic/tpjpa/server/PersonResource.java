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
import fr.istic.tpjpa.shared.BDDUtil;

@Path("/person")
public class PersonResource {
	
		
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.server.PersonResItf#createPerson(java.lang.String)
	 */
	@GET @Path("/create/{name}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Long createPerson(@PathParam("name") String name){
		
		Person p = new Person(name);
		
		PersistenceManager.persist(p);
		
		return p.getId();
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.server.PersonResItf#getPerson(java.lang.String)
	 */
	@GET @Path("/named/{name}")
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Person> getPerson(@PathParam("name") String name){
		return BDDUtil.getPersonsByName(name);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.server.PersonResItf#getPerson(java.lang.Long)
	 */
	@GET @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Person getPerson(@PathParam("id") Long id){
		return BDDUtil.getPersonByID(id);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.server.PersonResItf#getHome(java.lang.Long)
	 */
	@GET @Path("/{id}/home")
    @Produces({ MediaType.APPLICATION_JSON })
	public List<Home> getHome(@PathParam("id") Long id){
		return BDDUtil.getHomeByOwner(id);
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.tpjpa.server.PersonResItf#createHome(java.lang.Long, java.lang.String)
	 */
	@GET @Path("/{id}/createhome/{address}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Long createHome(@PathParam("id") Long id, @PathParam("address") String address){
		
		// We get the person
		Person p;
		try {
			p = BDDUtil.getPersonByID(id);
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
