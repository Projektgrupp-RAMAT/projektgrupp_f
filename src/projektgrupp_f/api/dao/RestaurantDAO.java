package projektgrupp_f.api.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import projektgrupp_f.api.model.Restaurant;
import projektgrupp_f.api.mongodb.ConnectionMongoDB;

/**
*
* @author Markus Eriksson
*/

public class RestaurantDAO {
	
	private Gson gson;
	private DB db;
	private DBCollection coll;
	private DBCursor cursor;
	private BasicDBObject query;
	private List<Restaurant> list;
	private Restaurant getRestaurant;
	
	public List<Restaurant> getRestaurants() {
		
		list = new ArrayList<Restaurant>();
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			cursor = coll.find();
			
			while(cursor.hasNext()) 
				list.add((Restaurant)gson.fromJson(cursor.next().toString(), Restaurant.class));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	public List<Restaurant> getRestaurantByQuery(String name, String address, String userId, String userName, String soundLvl) {
		
		list = new ArrayList<Restaurant>();
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			query = new BasicDBObject();
			
			if(address == null && userId == null && userName == null && soundLvl == null)
				query.put("name", name);
			else if(name == null && userId == null && userName == null && soundLvl == null)
				query.put("address", address);
			else if(address == null && name == null && userName == null && soundLvl == null)
				query.put("comments.userId", userId);
			else if(address == null && name == null && userId == null && soundLvl == null)
				query.put("comments.userName", userName);
			else if(address == null && name == null && userId == null && userName == null)
				query.put("comments.soundLvl", soundLvl);
			else {
				query.put("name", name);
				query.put("address", address);
				query.put("comments.userId", userId);
				query.put("comments.userName", userName);
				query.put("comments.soundLvl", soundLvl);
			}
			
			cursor = coll.find(query);
			
			while(cursor.hasNext())
				list.add((Restaurant)gson.fromJson(cursor.next().toString(), Restaurant.class));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return list;
	}
	
	public Restaurant getRestaurantById(String id) {
		
		getRestaurant = new Restaurant();
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			query = new BasicDBObject("_id", id);
			cursor = coll.find(query);
			
			while(cursor.hasNext())
				getRestaurant = (Restaurant)gson.fromJson(cursor.next().toString(), Restaurant.class);
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			cursor.close();
			ConnectionMongoDB.closeConnection();
		}
		return getRestaurant;
	}
	
	public Restaurant postRestaurant(Restaurant restaurant) {
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			
			coll.insert((BasicDBObject)JSON.parse(gson.toJson(restaurant)));
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
		return restaurant;
	}
	
	public Restaurant putRestaurant(Restaurant restaurant, String id) {
		
		getRestaurant = new Restaurant();
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			gson = new Gson();
			query = new BasicDBObject("_id", id);
			
			coll.update(query, (BasicDBObject)JSON.parse(gson.toJson(restaurant)), true, false);
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} finally {
			
			ConnectionMongoDB.closeConnection();
		}
		return getRestaurant;
	}
	
	public boolean deleteRestaurant(String id) {
		
		boolean deleted = false;
		
		try {
			
			db = ConnectionMongoDB.getConnection();
			coll = db.getCollection("restaurants");
			query = new BasicDBObject("_id", id);
			
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
}
