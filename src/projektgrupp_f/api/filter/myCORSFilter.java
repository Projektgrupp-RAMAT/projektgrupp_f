package projektgrupp_f.api.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
*
* @author Markus Eriksson
*/

public class myCORSFilter implements ContainerResponseFilter {

	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		response.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.getHttpHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
		return response;
	}

}
