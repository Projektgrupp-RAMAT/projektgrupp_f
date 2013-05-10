package projektgrupp_f.api.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

@Path("/comments")
public class CommentsResource {

	// Creates the Comment Data Access Object
	private CommentDAO dao = new CommentDAO();
	
	// This method will process HTTP GET requests and it will produce
	// content of the MIME media type "application/json"
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@QueryParam("restaurantId") String restaurantId, @QueryParam("userId") String userId, @QueryParam("userName") String userName,
			@QueryParam("soundLvl") String soundLvl, @QueryParam("text") String text, @QueryParam("flagged") String flagged) {
		
		if(restaurantId==null && userId==null && userName==null && soundLvl==null && text==null && flagged==null) {
			//System.out.println(dao.getComments().get(0).get_id().toString());
			return dao.getComments();
		}
		else 
			return dao.getCommentsByQuery(restaurantId, userId, userName, soundLvl, text, flagged);
	}
	
	// This method will process HTTP POST requests and it will consume
	// content of the MIME media type "application/json"
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postComment(Comment comment) {
		dao.postComment(comment);
	}
	
	@Path("{id}")
	public CommentResource getCommentById(@PathParam("id") String id) {
		return new CommentResource(id, dao);
	}
	
	@Path("count")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String countComments() {
		return Long.toString(dao.countComments());
	}
		
}
