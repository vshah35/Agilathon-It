package demo.policy;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RestUtils {

	public static RestResponse delete(Map<String, String> headers, String url) throws Exception {


		HttpClient client;
		HttpDelete delete = new HttpDelete(url);

		if(headers != null) {
			for(String key : headers.keySet()) {
				delete.addHeader(key, headers.get(key));
			}
		}

		client = getClient(url);
		HttpResponse response = client.execute(delete);
		
		RestResponse restResponse = new RestResponse();
		restResponse.setResponseCode(response.getStatusLine().getStatusCode());
		return restResponse;

	}

	public static RestResponse post(SharedStepState sharedStepState,
                                    String url, String endpoint) throws Exception {

		HttpClient client;
		HttpPost post = new HttpPost(url + endpoint);
		RestResponse resResponse = new RestResponse();
		StringBuffer responseString = new StringBuffer();
		Map<String, String> headers = sharedStepState.getHeaders();
		if (headers != null)
			for (Entry<String, String> entry : headers.entrySet()) {
				post.addHeader(entry.getKey(), entry.getValue());
			}
		StringEntity input = new StringEntity(sharedStepState.getJsonRequest());
		input.setContentType("application/json");
		post.setEntity(input);

		client = getClient(url);
		HttpResponse response = client.execute(post);
		Header[] rheaders = response.getAllHeaders();
		for (Header header : rheaders) {
			resResponse.setHeader(header.getName(), header.getValue());
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		String line;
		while ((line = rd.readLine()) != null) {
			responseString.append(line);
		}

		resResponse.setResponseBody(responseString.toString());
		resResponse.setResponseCode(response.getStatusLine().getStatusCode());
		resResponse.setResponseMessage(response.getStatusLine().getReasonPhrase());

		return resResponse;
	}


	public static RestResponse get(SharedStepState sharedStepState, String url,
                                   String endpoint) throws Exception {

		HttpGet request = new HttpGet(url + endpoint);
		HttpClient client;
		RestResponse resResponse = new RestResponse();
		HttpResponse response;
		StringBuffer responseString = new StringBuffer();
		Map<String, String> headers = sharedStepState.getHeaders();

		if (headers != null) {
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				request.addHeader(key, headers.get(key));
			}
		}

		client = getClient(url);
		response = client.execute(request);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			responseString.append(line);
		}

		/*
		 * Setting values for the response object
		 */
		resResponse.setResponseBody(responseString.toString());
		resResponse.setResponseCode(response.getStatusLine().getStatusCode());
		resResponse.setResponseMessage(response.getStatusLine()
				.getReasonPhrase());
		Header[] rheaders = response.getAllHeaders();
		for (Header header : rheaders) {
			resResponse.setHeader(header.getName(), header.getValue());
		}
		return resResponse;
	}


	public static HttpClient getClient(String endpoint) throws Exception {

		HttpClient client = null;
		URL aUrl = null;
		aUrl = new URL(endpoint);

		// Check the Protocol
		if (aUrl != null && "http".equalsIgnoreCase(aUrl.getProtocol())) {

			client = HttpClientBuilder.create().build();
		} else if (aUrl != null && "https".equalsIgnoreCase(aUrl.getProtocol())) {
			client = new SSLKeyStore().getKeyStore();
		}
		return client;
	}
}
