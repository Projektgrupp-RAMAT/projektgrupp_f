package projektgrupp_f.api.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import projektgrupp_f.api.model.Comment;
import projektgrupp_f.api.mongodb.ConnectionMongoDB;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

/**
*
* @author Markus Eriksson
*/

public class CommentDAO {

	private Gson gson;
	private DB db;
	private DBCollection coll;
	private DBCursor cursor;
	private BasicDBObject query;
	
	public List<Comment> getComments() {

		List<Comment> list = new ArrayList<Comment>();
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			System.out.println(db.isAuthenticated());
			coll = db.getCollection("comments");
			gson = new Gson();
			cursor = coll.find();
			
			while(cursor.hasNext()) 
				list.add((Comment)gson.fromJson(cursor.next().toString(), Comment.class));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		
		return list;
	}
	
	public Comment getCommentById(String commentId) {

		Comment comment = new Comment();
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			query = new BasicDBObject("_id", new ObjectId(commentId));
			cursor = coll.find(query);
			
			while(cursor.hasNext())
				comment = (Comment)gson.fromJson(cursor.next().toString(), Comment.class);
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		
		return comment;
	}
	
	public List<Comment> getCommentsByQuery(String restaurantId, String userId, String userName, String soundLvl, String text, String flagged) {

		List<Comment> list = new ArrayList<Comment>();
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("comments");
			gson = new Gson();
			query = new BasicDBObject();
			
			if(userId == null && userName == null && soundLvl == null && text == null && flagged == null)
				query.put("restaurantId", restaurantId);
			else if(restaurantId == null && userName == null && soundLvl == null && text == null && flagged == null)
				query.put("userId", userId);
			else if(userId == null && restaurantId == null && soundLvl == null && text == null && flagged == null)
				query.put("userName", userName);
			else if(userId == null && userName == null && restaurantId == null && text == null && flagged == null)
				query.put("soundLvl", soundLvl);
			else if(userId == null && userName == null && soundLvl == null && restaurantId == null && flagged == null)
				query.put("text", text);
			else if(userId == null && userName == null && soundLvl == null && text == null && restaurantId == null)
				query.put("flagged", flagged);
			else {
				query.put("restaurantId", restaurantId);
				query.put("userId", userId);
				query.put("userName", userName);
				query.put("soundLvl", soundLvl);
				query.put("text", text);
				query.put("flagged", flagged);
			}
			
			cursor = coll.find(query);
			
			while(cursor.hasNext())
				list.add((Comment)gson.fromJson(cursor.next().toString(), Comment.class));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	public void postComment(Comment comment) {

		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("comments");
			gson = new Gson();
			
			coll.insert((BasicDBObject)JSON.parse(gson.toJson(comment)));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
	}
	
	public void updateComment(Comment comment) {
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			query = new BasicDBObject("_id", comment.get_id());
			
			coll.update(query, (BasicDBObject)JSON.parse(gson.toJson(comment)), true, false);
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
	}
	
	public boolean deleteComment(String commentId) {

		boolean deleted = false;
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("comments");
			query = new BasicDBObject("_id", new ObjectId(commentId));
			
			coll.remove(query);
			deleted = true;
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
		return deleted;
	}
	
	public long countComments() {
		
		long numberOfComments;
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("comments");
			
			numberOfComments = coll.getCount();
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
		
		return numberOfComments;
	}
}
