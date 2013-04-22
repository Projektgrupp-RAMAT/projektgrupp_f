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

import projektgrupp_f.api.dao.CommentDAO;
import projektgrupp_f.api.model.Comment;

/**
*
* @author Markus Eriksson
*/

public class CommentResource {

	// Creates the Comment Data Access Object
	private CommentDAO dao = new CommentDAO();
	private String restaurantId;
	
	public CommentResource() {
		
	}
	
	public CommentResource(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	// This method will process HTTP GET requests and it will produce
	// content of the MIME media type "application/json"
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getRestaurantComments(@QueryParam("userId") String userId, @QueryParam("userName") String userName, @QueryParam("soundLvl") String soundLvl) {
		if(restaurantId == null && userId != null || userName != null || soundLvl != null)
			return dao.getCommentsByQuery(userId, userName, soundLvl);
		else if(restaurantId == null)
			return dao.getComments();
		else {
			if(userId == null && userName == null && soundLvl == null)
				return dao.getRestaurantComments(restaurantId);
			else {
				return dao.getRestaurantCommentsByQuery(userId, userName, soundLvl, restaurantId);
			}
		}
	}
	
	// This method will process HTTP POST requests and it will consume
	// content of the MIME media type "application/json"
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postComment(Comment comment) {
		if(restaurantId != null)
			dao.postComment(restaurantId, comment);
	}
	
	// This method will process HTTP POST requests and it will consume
	// content of the MIME media type "application/json"
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void putComment(Comment comment) {
		
	}
	
	// This method will process HTTP DELETE requests
	@DELETE
	public void deleteComment() {
		
	}
	
}