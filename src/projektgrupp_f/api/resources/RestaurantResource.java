package projektgrupp_f.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import projektgrupp_f.api.dao.RestaurantDAO;
import projektgrupp_f.api.model.Restaurant;

/**
*
* @author Markus Eriksson
*/

// The class will be hosted at the URI path "/restaurants"
@Path("/restaurants")
public class RestaurantResource {

	// Creates the Restaurant Data Access Object
	private RestaurantDAO dao = new RestaurantDAO();
	
	public RestaurantResource() {
		
	}
	
	// This method will process HTTP GET requests and it will produce
	// content of the MIME media type "application/json"
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Restaurant> getRestaurants(@QueryParam("name") String name, @QueryParam("address") String address, @QueryParam("userId") String userId, 
											@QueryParam("userName") String userName, @QueryParam("soundLvl") String soundLvl) {
		
		if(name == null && address == null && userId == null && userName == null && soundLvl == null)
			return dao.getRestaurants();
		else
			return dao.getRestaurantByQuery(name, address, userId, userName, soundLvl);
	}
	
	// This method will process HTTP POST requests and it will consume
	// content of the MIME media type "application/json"
	@POST
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Restaurant postRestaurant(Restaurant restaurant) {
		return dao.postRestaurant(restaurant);
	}

	// The method will be hosted at the URI path "/restaurants/{id}"
	// This method will process HTTP GET requests and it will produce
	// content of the MIME media type "application/json"
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Restaurant getRestaurantById(@PathParam("id") String id) {
		return dao.getRestaurantById(id);
	}
	
	// The method will be hosted at the URI path "/restaurants/{id}"
	// This method will process HTTP PUT requests and it will consume
	// content of the MIME media type "application/json"
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Restaurant putRestaurant(Restaurant restaurant, @PathParam("id") String id) {
		return dao.putRestaurant(restaurant, id);
	}
	
	// The method will be hosted at the URI path "/restaurants/{id}"
	// This method will process HTTP DELETE requests
	@Path("{id}")
	@DELETE
	public void deleteRestaurant(@PathParam("id") String id) {
		dao.deleteRestaurant(id);
	}
	
	@Path("{id}/comments")
	public CommentResource commentResource(@PathParam("id") String id) {
		return new CommentResource(id);
	}
	
	@Path("/comments")
	public CommentResource commentResource() {
		return new CommentResource();
	}
	
}
