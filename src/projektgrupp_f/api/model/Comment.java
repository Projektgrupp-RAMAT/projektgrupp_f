package projektgrupp_f.api.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Markus Eriksson
*/

@XmlRootElement
public class Comment {

	private String _id;
	private String userId;
	private String userName;
	private double soundLvl;
	private String text;
	
	public Comment() {
		
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public double getSoundLvl() {
		return soundLvl;
	}
	
	public void setSoundLvl(double soundLvl) {
		this.soundLvl = soundLvl;
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

}
