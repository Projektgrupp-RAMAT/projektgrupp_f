package projektgrupp_f.api.mongodb;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * This class have the methods that handles the connection to the database.
 *
 * @author Markus Eriksson
 */
public class ConnectionMongoDB {

	private static MongoClient mongoClient;
	private static DB db;
	
	public ConnectionMongoDB() {

	}
	
	/**
	 * This method gets a connection to the database
	 * 
	 * @return		Returns the requested database
	 * @throws UnknownHostException
	 * @throws MongoException
	 */
	public static DB getConnection() throws UnknownHostException, MongoException {
		
		try {
			
			mongoClient = new MongoClient("localhost", 27017);
			
			db = mongoClient.getDB("EatAndHear");
			db.authenticate("api", "apipwd".toCharArray());
			
			return db;
			
		} catch (UnknownHostException e) {
			throw e;
		} catch (MongoException me) {
			throw me;
		}
	}
	
	/**
	 * This method close the connection to a connected database
	 */
	public static void closeConnection() {
			mongoClient.close();
	}
}
