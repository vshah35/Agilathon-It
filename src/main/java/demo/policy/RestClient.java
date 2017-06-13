package demo.policy;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class RestClient {
	/**
	 * 
	 * @param endPoint
	 * @return
	 */
	private static WebResource getWebResource(String endPoint) {

		Client client = Client.create();
		WebResource webResource = client.resource(endPoint);

		return webResource;
	}

	/**
	 * 
	 * @param endPoint
	 * @return
	 */
	public static ClientResponse getClientResponse(String endPoint) {

		WebResource webResource = getWebResource(endPoint);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

		return response;
	}

	/**
	 * 
	 * @param endPoint
	 * @param request
	 * @return
	 */
	public static ClientResponse postToEndPoint(String endPoint, String request) {

		WebResource webResource = getWebResource(endPoint);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, request);
		return response;

	}

	/**
	 * 
	 * @param endPoint
	 * @return
	 */
	public static ClientResponse delete(String endPoint) {

		WebResource webResource = getWebResource(endPoint);
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON_TYPE).delete(ClientResponse.class);
		return response;

	}

}
