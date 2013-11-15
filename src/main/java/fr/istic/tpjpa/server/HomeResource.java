package fr.istic.tpjpa.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.tpjpa.shared.BDDUtil;
import fr.istic.tpjpa.shared.Home;

@Path("/home")
public class HomeResource {


	@GET @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Home getHome(@PathParam("id") Long id){
		return BDDUtil.getHomeByID(id);
	}
	
	
}
