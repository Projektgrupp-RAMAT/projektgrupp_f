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
	
	public NumberOfComment() {
		
	}
	
	public NumberOfComment(String restaurantId, int numberOfComments) {
		this.restaurantId = restaurantId;
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
