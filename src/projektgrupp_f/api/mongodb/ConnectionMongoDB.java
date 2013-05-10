package projektgrupp_f.api.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
*
* @author Markus Eriksson
*/

public class ConnectionMongoDB {

	private static MongoClient mongoClient;
	private static DB db;
	
	public ConnectionMongoDB() {

	}
	
	public static DB getConnection() throws UnknownHostException, MongoException {
		
		try {
			
			mongoClient = new MongoClient("localhost", 27017);
			
			db = mongoClient.getDB("test");
			db.authenticate("markus", "mackan".toCharArray());
			
			return db;
			
		} catch (UnknownHostException e) {
			throw e;
		} catch (MongoException me) {
			throw me;
		}
	}
	
	public static void closeConnection() {
			mongoClient.close();
	}
}
