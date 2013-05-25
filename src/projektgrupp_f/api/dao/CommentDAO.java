package projektgrupp_f.api.dao;

import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import projektgrupp_f.api.model.Comment;
import projektgrupp_f.api.model.NumberOfComment;
import projektgrupp_f.api.mongodb.ConnectionMongoDB;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * This class have the methods that handles the data access from and to the database.
 * 
 * @author Markus Eriksson
 */
public class CommentDAO {

	private Gson gson;
	private DB db;
	private DBCollection coll;
	private DBCursor cursor;
	private BasicDBObject query;
	
	/**
	 * This method get all the comments from the database.
	 * 
	 * @return					Returns a list of all the comments.
	 */
	public List<Comment> getComments() {

		List<Comment> list = new ArrayList<Comment>();
		DBObject dbo;
		Comment comment;
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			System.out.println("Authenticated: " + db.isAuthenticated());
			coll = db.getCollection("comments");
			gson = new Gson();
			cursor = coll.find();
			
			while(cursor.hasNext()) {
				dbo = cursor.next();
				comment = (Comment)gson.fromJson(dbo.toString(), Comment.class);
				comment.setId(dbo.get("_id").toString());
				list.add(comment);
			}
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		
		return list;
	}
	
	/**
	 * This method gets a comment with a unique id from the database.
	 * 
	 * @param commentId			The unique id for the comment.
	 * @return					Returns a comment.
	 */
	public Comment getCommentById(String commentId) {

		Comment comment = new Comment();
		DBObject dbo;
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("comments");
			gson = new Gson();
			query = new BasicDBObject("_id", new ObjectId(commentId));
			cursor = coll.find(query);
			
			while(cursor.hasNext()) {
				dbo = cursor.next();
				comment = (Comment)gson.fromJson(dbo.toString(), Comment.class);
				comment.setId(dbo.get("_id").toString());
			}
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return comment;
	}
	
	/**
	 * This method gets comments by a query from the database.
	 * 
	 * @param restaurantId		Restaurant id on the comments you want to search for.
	 * @param userId			User id on the comments you want to search for.
	 * @param userName			User name on the comments you want to search for.
	 * @param soundLvl			Sound level on the comments you want to search for.
	 * @param text				Text on the comments you want to search for.
	 * @param flagged			If the comments are flagged or not (true or false).
	 * @return					Returns a list of requested comments.
	 */
	public List<Comment> getCommentsByQuery(String restaurantId, String userId, String userName, String soundLvl, String text, String flagged) {

		List<Comment> list = new ArrayList<Comment>();
		DBObject dbo;
		Comment comment;
		
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
				query.put("flagged", Boolean.parseBoolean(flagged));
			else {
				query.put("restaurantId", restaurantId);
				query.put("userId", userId);
				query.put("userName", userName);
				query.put("soundLvl", soundLvl);
				query.put("text", text);
				query.put("flagged", Boolean.parseBoolean(flagged));
			}
			
			cursor = coll.find(query);
			
			while(cursor.hasNext()) {
				dbo = cursor.next();
				comment = (Comment)gson.fromJson(dbo.toString(), Comment.class);
				comment.setId(dbo.get("_id").toString());
				list.add(comment);
			}
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	/**
	 * This method inserts a comment to the database.
	 * 
	 * @param comment			The comment you want to insert.
	 */
	public void postComment(Comment comment) {

		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("comments");
			gson = new Gson();
			
			Date date= new Date();
	    	String timeStamp = new Timestamp(date.getTime()).toString().substring(0, 16);
			comment.setTimeStamp(timeStamp);
			
			coll.insert((BasicDBObject)JSON.parse(gson.toJson(comment)));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
	}
	
	/**
	 * This method updates a comment on the database.
	 * 
	 * @param comment			The comment with the update.
	 * @param commentId			Id for the comment you want to update.
	 */
	public void updateComment(Comment comment, String commentId) {
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("comments");
			gson = new Gson();
			query = new BasicDBObject("_id", new ObjectId(commentId));
			
			coll.update(query, (BasicDBObject)JSON.parse(gson.toJson(comment)), true, false);
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
	}
	
	/**
	 * This method deletes a comment on the database.
	 * 
	 * @param commentId			Id for the comment you want to delete.
	 * @return					Returns a boolean if the delete was successful.
	 */
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
	
	/**
	 * This method counts the comments on the database.
	 * 
	 * @return					Returns the number of comments on the database.
	 */
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
	
	/**
	 * This method gets the top ten commented restaurants from the database.
	 * 
	 * @return					Returns a list of the top ten restaurants.
	 */
	public List<NumberOfComment> getTopTen() {
		
		List<String> restaurantIdList = new ArrayList<String>();
		List<Double> latList = new ArrayList<Double>();
		List<Double> lonList = new ArrayList<Double>();
		List<NumberOfComment> nocList = new ArrayList<NumberOfComment>();
		DBObject dbo;
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			System.out.println("Authenticated: " + db.isAuthenticated());
			coll = db.getCollection("comments");
			gson = new Gson();
			cursor = coll.find();
			
			while(cursor.hasNext()) {
				
				dbo = cursor.next();
				
				if(!restaurantIdList.contains((String)dbo.get("restaurantId"))) {
					latList.add((double)dbo.get("lat"));
					lonList.add((double)dbo.get("lon"));
					restaurantIdList.add((String)dbo.get("restaurantId"));
				}
			}
			
			for(int i = 0; i < restaurantIdList.size(); i++) {
				
				query = new BasicDBObject("restaurantId", restaurantIdList.get(i));
				nocList.add(new NumberOfComment(restaurantIdList.get(i), latList.get(i), lonList.get(i), coll.find(query).count()));
			}
			
			java.util.Collections.sort(nocList);
			
			if(nocList.size() > 10)
				nocList = nocList.subList(0, 10);
			else
				nocList = nocList.subList(0, nocList.size());

		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		
		return nocList;
	}
}


