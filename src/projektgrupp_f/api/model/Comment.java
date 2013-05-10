package projektgrupp_f.api.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;

/**
*
* @author Markus Eriksson
*/

@XmlRootElement
public class Comment {

	private ObjectId _id;
	private String restaurantId;
	private String userId;
	private String userName;
	private String soundLvl;
	private String text;
	private String flagged;
	
	public Comment() {
		
	}
	
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = new ObjectId(_id);
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

	public String getFlagged() {
		return flagged;
	}

	public void setFlagged(String flagged) {
		this.flagged = flagged;
	}

}
