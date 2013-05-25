package projektgrupp_f.api.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Markus Eriksson
 */
@XmlRootElement
public class Comment {

	private String id;
	private String restaurantId;
	private String userId;
	private String userName;
	private String soundLvl;
	private String text;
	private boolean flagged;
	private String timeStamp;
	private double lat;
	private double lon;
	
	public Comment() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getSoundLvl() {
		return soundLvl;
	}
	
	public void setSoundLvl(String soundLvl) {
		this.soundLvl = soundLvl;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}


}
