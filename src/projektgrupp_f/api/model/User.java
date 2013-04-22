package projektgrupp_f.api.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
*
* @author Markus Eriksson
*/
@XmlRootElement
public class User {

	private String userId;
	private String userName;
	
	public User() {
		
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
