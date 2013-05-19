package projektgrupp_f.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import projektgrupp_f.api.dao.CommentDAO;
import projektgrupp_f.api.model.Comment;

/**
 * This class have the methods that handles the client requests on the comment resource.
 * The comment resource have methods that handles a specific comment.
 *
 * @author Markus Eriksson
 */
public class CommentResource {

	private CommentDAO dao;
	private String commentId;
	
	/**
	 * The constructor for the class CommentResource.
	 * 
	 * @param commentId			Comment id for the unique comment.
	 * @param dao				The data access object for the comments.
	 */
	public CommentResource(String commentId, CommentDAO dao) {
		this.commentId = commentId;
		this.dao = dao;
	}
	
	/**
	 * This method will process HTTP GET requests and it will produce
	 * content of the MIME media type "application/json".
	 * Get a comment.
	 * 
	 * @return					Returns the requested comment.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment() {
		return dao.getCommentById(commentId);
	}
	
	/**
	 * This method will process HTTP PUT requests and it will consume
	 * content of the MIME media type "application/json".
	 * Update a comment.
	 * 
	 * @param comment			The comment you want to update.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void putComment(Comment comment) {
		dao.updateComment(comment, commentId);
	}
	
	/**
	 * This method will process HTTP DELETE requests.
	 * Delete a comment.
	 */
	@DELETE
	public void deleteComment() {
		dao.deleteComment(commentId);
	}
	
}