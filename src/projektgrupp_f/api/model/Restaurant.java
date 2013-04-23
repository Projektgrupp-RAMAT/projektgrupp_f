package projektgrupp_f.api.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
*
* @author Markus Eriksson
*/

@XmlRootElement
public class Restaurant {

	private String _id;
	private String name;
	private String address;
	private double soundAvg;
	private List<Comment> comments;
	
	public Restaurant() {
		
	}
	
	public String get_Id() {
		return _id;
	}
	
	public void set_Id(String _id) {
		this._id = _id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public double getSoundAvg() {
		double tmp=0;
		if(getComments() != null) {
			for(int i = 0; i < getComments().size(); i++) {
				tmp += getComments().get(i).getSoundLvl();
			}
		return soundAvg =(double)tmp/getComments().size();
		} else {
			return tmp;
		}
	}
	
	public void setSoundAvg(double soundAvg) {
		this.soundAvg = soundAvg;
	}
	
}
