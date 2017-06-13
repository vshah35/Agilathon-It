package demo.policy;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SSLKeyStore {
	
	
	InputStream inputStream;
	
	Logger LOGGER = Logger.getLogger(SSLKeyStore.class);
	
	/**
	 * 
	 * @return
	 */
	public HttpClient getKeyStore() throws Exception {

		Properties prop = readPropertyFile();

		String keystoreFilename = prop.getProperty("keystoreFileName");
		String keystorePassword =  prop.getProperty("keyStorePassword");

		SSLContext sslcontext = SSLContexts
				.custom()
				.loadTrustMaterial(new File(keystoreFilename),
						keystorePassword.toCharArray(),
						new TrustSelfSignedStrategy()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
				.<ConnectionSocketFactory> create().register("https", sslsf)
				.build();

		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);

		HttpClient httpclient = HttpClients.custom()
				.setConnectionManager(httpClientConnectionManager).build();

		if(inputStream != null){

			inputStream.close();
		}

		return httpclient;

	}
	
	public HttpClient getKeyStore(String app) throws Exception {
		
		Properties prop = readPropertyFile(app);
		
		String keystoreFilename = prop.getProperty("keystoreFileName");
		String keystorePassword =  prop.getProperty("keyStorePassword");
		
		SSLContext sslcontext = SSLContexts
				.custom()
				.loadTrustMaterial(new File(keystoreFilename),
						keystorePassword.toCharArray(),
						new TrustSelfSignedStrategy()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
				.<ConnectionSocketFactory> create().register("https", sslsf)
				.build();

		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager(
				socketFactoryRegistry);

		HttpClient httpclient = HttpClients.custom()
				.setConnectionManager(httpClientConnectionManager).build();
		
		if(inputStream != null){
			
			inputStream.close();
		}
		
		return httpclient;

	}
	
	/**
	 * 
	 * @return
	 */
	private Properties readPropertyFile(){
		
		Properties prop = new Properties();
		String fileName = "application_config.properties";
		
		inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		
		try {
			prop.load(inputStream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Not Able to load the Property File");
		}
		return prop;
	}
	
private Properties readPropertyFile(String app){
		
		Properties prop = new Properties();
		String fileName = app+ "_application_config.properties";
		
		inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		
		try {
			prop.load(inputStream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Not Able to load the Property File");
		}
		return prop;
	}
}
