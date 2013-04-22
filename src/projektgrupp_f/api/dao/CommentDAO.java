package projektgrupp_f.api.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import projektgrupp_f.api.model.Comment;
import projektgrupp_f.api.mongodb.ConnectionMongoDB;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
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
			coll = db.getCollection("restaurants");
			cursor = coll.find();
			gson = new Gson();
			while(cursor.hasNext()) {
				BasicDBObject[] commentArray = ((BasicDBList)cursor.next().get("comments")).toArray(new BasicDBObject[0]);
				for(BasicDBObject dbObj : commentArray) {
					list.add((Comment)gson.fromJson(dbObj.toString(), Comment.class));
				}
			}
		} catch(UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	public List<Comment> getCommentsByQuery(String userId, String userName, String soundLvl) {
		List<Comment> list = new ArrayList<Comment>();
		try {
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			query = new BasicDBObject();
			
			if(userName == null && soundLvl == null)
				query.put("comments.userId", userId);
			else if(userId == null && soundLvl == null)
				query.put("comments.userName", userName);
			else if(userName == null && userId == null)
				query.put("comments.soundLvl", soundLvl);
			else {
				query.put("comments.userId", userId);
				query.put("comments.userName", userName);
				query.put("comments.soundLvl", soundLvl);
			}
			cursor = coll.find(query);
			gson = new Gson();
			while(cursor.hasNext()) {
				BasicDBObject[] commentArray = ((BasicDBList)cursor.next().get("comments")).toArray(new BasicDBObject[0]);
				for(BasicDBObject dbObj : commentArray) {
					if(dbObj.containsValue(soundLvl) || dbObj.containsValue(userName) || dbObj.containsValue(userId))
						list.add((Comment)gson.fromJson(dbObj.toString(), Comment.class));
				}
			}
		} catch(UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	public List<Comment> getRestaurantComments(String restaurantId) {
		List<Comment> list = new ArrayList<Comment>();
		try {
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			if(restaurantId != null) {
				query = new BasicDBObject("_id", restaurantId);
				cursor = coll.find(query);
			} else
				cursor = coll.find();
				
			gson = new Gson();
			while(cursor.hasNext()) {
				BasicDBObject[] commentArray = ((BasicDBList)cursor.next().get("comments")).toArray(new BasicDBObject[0]);
				for(BasicDBObject dbObj : commentArray)
					list.add((Comment)gson.fromJson(dbObj.toString(), Comment.class));
			}
		} catch(UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	public List<Comment> getRestaurantCommentsByQuery(String userId, String userName, String soundLvl, String restaurantId) {
		List<Comment> list = new ArrayList<Comment>();
		try {
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			
			if(restaurantId != null)
				query = new BasicDBObject("_id", restaurantId);
			else
				query = new BasicDBObject();
			
			if(userName == null && soundLvl == null)
				query.put("comments.userId", userId);
			else if(userId == null && soundLvl == null)
				query.put("comments.userName", userName);
			else if(userName == null && userId == null)
				query.put("comments.soundLvl", soundLvl);
			else {
				query.put("comments.userId", userId);
				query.put("comments.userName", userName);
				query.put("comments.soundLvl", soundLvl);
			}
			cursor = coll.find(query);
			gson = new Gson();
			while(cursor.hasNext()) {
				BasicDBObject[] commentArray = ((BasicDBList)cursor.next().get("comments")).toArray(new BasicDBObject[0]);
				for(BasicDBObject dbObj : commentArray) {
					if(dbObj.containsValue(soundLvl) || dbObj.containsValue(userName) || dbObj.containsValue(userId))
						list.add((Comment)gson.fromJson(dbObj.toString(), Comment.class));
				}
			}
		} catch(UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	public void postComment(String restaurantId, Comment comment) {
		try {
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			query = new BasicDBObject("_id", restaurantId);
			coll.update(query, new BasicDBObject("$push", new BasicDBObject("comments", (BasicDBObject)JSON.parse(gson.toJson(comment)))), true, false);
		} catch(UnknownHostException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionMongoDB.closeConnection();
		}
	}
	
	public void putComment() {
		
	}
	
	public void deleteComment() {
		
	}
}
