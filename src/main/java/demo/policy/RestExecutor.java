/**
 * 
 */
package demo.policy;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RestExecutor {
	

	private static Logger LOGGER = Logger.getLogger(RestExecutor.class);
	
	//Rest Executor Block
	private HttpClient client;
	private String url;

	/**
	 * Constructor for RestExecutor
	 * 
	 * @param url
	 */
	public RestExecutor(String url) throws Exception {
		
		URL aUrl = null;
		
		try {
			aUrl = new URL(url);
			
		} catch (MalformedURLException e) {
			LOGGER.error("Not able to load the URL");
		}
		//Check the Protocol
		if(aUrl != null && "http".equalsIgnoreCase(aUrl.getProtocol())){
			
			client = HttpClientBuilder.create().build();
		}else if(aUrl != null && "https".equalsIgnoreCase(aUrl.getProtocol())){
			
			client = new SSLKeyStore().getKeyStore();
		}		
		this.url = url;
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public RestValidator get(String path) throws Exception {
		return get(path, null);
	}

	/**
	 * Executes GET req and returns response json.
	 * 
	 * @param path
	 * @param headers
	 * @return
	 */
	public RestValidator get(String path, HashMap<String, String> headers) throws Exception {
		

		HttpGet request = new HttpGet(url + path);
		HttpResponse response;
		/*
		 * The response object which holds the details of the response.
		 */
		RestResponse resResponse = new RestResponse();
		StringBuffer responseString = new StringBuffer();
		// try {
		/*
		 * Setting the headers for the request
		 */
		if (headers != null) {
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				request.addHeader(key, headers.get(key));
			}
		}

		/*
		 * Executing the GET operation
		 */
		

		response = client.execute(request);
		//System.out.println("check 5");
		/*
		 * Obtaining the response body from the response stream.
		 */
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			responseString.append(line);
		}
		
		LOGGER.debug("Response String >>" + responseString.toString());
		/*
		 * Setting values for the response object
		 */
		resResponse.setResponseBody(responseString.toString());
		resResponse.setResponseCode(response.getStatusLine().getStatusCode());
		resResponse.setResponseMessage(response.getStatusLine().getReasonPhrase());
		Header[] rheaders = response.getAllHeaders();
		for (Header header : rheaders) {
			resResponse.setHeader(header.getName(), header.getValue());
			
		}
	  
		/*
		 * Returns the RestValidator object providing the response object
		 */
		return new RestValidator(resResponse);
	}

	public RestValidator post(String path, String xmlContent, String contentType) {
		return post(path, null, xmlContent, contentType);
	}

	/**
	 * Executes POST req and returns response json.
	 */
	public RestValidator post(String path, Map<String, String> headers, String request, String contentType) {

		HttpPost post = new HttpPost(url + path);
		RestResponse resResponse = new RestResponse();
		StringBuffer responseString = new StringBuffer();

		try {
			if (headers != null)
				// Temp commenting post.setEntity(getEntities(headers));

				// Adding headers
				for (Entry<String, String> entry : headers.entrySet()) {
				post.addHeader(entry.getKey(), entry.getValue());
				}

			/*
			 * Setting the xml content and content type.
			 */

			StringEntity input = new StringEntity(request);
			input.setContentType(contentType);
			post.setEntity(input);

			HttpResponse response = client.execute(post);
			
			Header[] rheaders = response.getAllHeaders();
			for (Header header : rheaders) {
				resResponse.setHeader(header.getName(), header.getValue());
			}
			
			System.out.println("Response Entity > "+ response.getEntity().getContent());
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			String line;
			while ((line = rd.readLine()) != null) {
				responseString.append(line);
			}
			
			resResponse.setResponseBody(responseString.toString());
			resResponse.setResponseCode(response.getStatusLine().getStatusCode());
			resResponse.setResponseMessage(response.getStatusLine().getReasonPhrase());
			
		} catch (Exception e) {
			e.printStackTrace(); // handle
		}
		return new RestValidator(resResponse);
	}
}
