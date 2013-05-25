package projektgrupp_f.api.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Markus Eriksson
 */
@XmlRootElement
public class NumberOfComment implements Comparable<Object> {

	private String restaurantId;
	private int numberOfComments;
	private double lat;
	private double lon;
	
	public NumberOfComment() {
		
	}
	
	public NumberOfComment(String restaurantId, double lat, double lon, int numberOfComments) {
		this.restaurantId = restaurantId;
		this.lat = lat;
		this.lon = lon;
		this.numberOfComments = numberOfComments;
	}
	
	public String getRestaurantId() {
		return restaurantId;
	}
	
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public int getNumberOfComments() {
		return numberOfComments;
	}
	
	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
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
	
	@Override
	public int compareTo(Object o) {
		
		if (this.numberOfComments == ((NumberOfComment) o).numberOfComments)
            return 0;
        else if ((this.numberOfComments) > ((NumberOfComment) o).numberOfComments)
            return -1;
        else
            return 1;
		
	}
}
