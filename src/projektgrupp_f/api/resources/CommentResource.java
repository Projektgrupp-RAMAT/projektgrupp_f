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
*
* @author Markus Eriksson
*/

public class CommentResource {

	private CommentDAO dao;
	private String commentId;
	
	public CommentResource(String commentId, CommentDAO dao) {
		this.commentId = commentId;
	}
	
	// This method will process HTTP GET requests and it will produce
	// content of the MIME media type "application/json"
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getComment() {
		return dao.getCommentById(commentId);
	}
	/*
	// This method will process HTTP POST requests and it will consume
	// content of the MIME media type "application/json"
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postComment(Comment comment) {
		
	}
	*/
	// This method will process HTTP POST requests and it will consume
	// content of the MIME media type "application/json"
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void putComment(Comment comment) {
		dao.updateComment(comment);
	}
	
	// This method will process HTTP DELETE requests
	@DELETE
	public void deleteComment() {
		dao.deleteComment(commentId);
	}
	
}