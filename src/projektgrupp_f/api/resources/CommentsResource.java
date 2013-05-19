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
import projektgrupp_f.api.model.NumberOfComment;

/**
 * This class have the methods that handles the client requests on the comments resource.
 * The comments resource have methods that handles a bunch of comments.
 *
 * @author Markus Eriksson
 */
@Path("/comments")
public class CommentsResource {

	// Creates the Comment Data Access Object
	private CommentDAO dao = new CommentDAO();
	
	/**
	 * This method will process HTTP GET requests and it will produce
	 * content of the MIME media type "application/json".
	 * Read comments from the database.
	 * 
	 * @param restaurantId		Restaurant id on the comments you want to search for.
	 * @param userId			User id on the comments you want to search for.
	 * @param userName			User name on the comments you want to search for.
	 * @param soundLvl			Sound level on the comments you want to search for.
	 * @param text				Text on the comments you want to search for.
	 * @param flagged			If the comments are flagged or not (true or false).
	 * @return					Returns a list of requested comments.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@QueryParam("restaurantId") String restaurantId, @QueryParam("userId") String userId, @QueryParam("userName") String userName,
			@QueryParam("soundLvl") String soundLvl, @QueryParam("text") String text, @QueryParam("flagged") String flagged) {
		
		if(restaurantId==null && userId==null && userName==null && soundLvl==null && text==null && flagged==null) {
			return dao.getComments();
		}
		else 
			return dao.getCommentsByQuery(restaurantId, userId, userName, soundLvl, text, flagged);
	}
	
	/**
	 * This method will process HTTP POST requests and it will consume
	 * content of the MIME media type "application/json". 
	 * Inserts a comment on the database. 
	 * 
	 * @param comment			The comment you want to insert.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postComment(Comment comment) {
		dao.postComment(comment);
	}
	
	/**
	 * This method will process HTTP methods on a comment with a unique id.
	 * 
	 * @param id				Id on the comment you want to search for.
	 * @return					Returns a comment resource.
	 */
	@Path("{id}")
	public CommentResource getCommentById(@PathParam("id") String id) {
		return new CommentResource(id, dao);
	}
	
	/**
	 * This method will process HTTP GET requests and it will produce
	 * content of the MIME media type "TEXT_PLAIN".
	 * Get the number of comments on the database.
	 * 
	 * @return					Returns the number of comments in a string.
	 */
	@Path("count")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String countComments() {
		return Long.toString(dao.countComments());
	}
	
	/**
	 * This method will process HTTP GET requests and it will produce
	 * content of the MIME media type "application/json".
	 * Read comments from the database.
	 * 
	 * @param restaurantId		Restaurant id on the comments you want to search for.
	 * @return					Returns a list of requested comments.
	 */
	@Path("restaurantId/{restaurantId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getCommentsByRestaurantId(@PathParam("restaurantId") String restaurantId) {
		return dao.getCommentsByQuery(restaurantId, null, null, null, null, null);
	}
	
	/**
	 * This method will process HTTP GET requests and it will produce
	 * content of the MIME media type "application/json".
	 * Gets the top ten commented restaurants from the database
	 * 
	 * @return					Returns a list of object type NumberOfComment
	 */
	@Path("topten")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NumberOfComment> getTopTen() {
		return dao.getTopTen();
	}
}
