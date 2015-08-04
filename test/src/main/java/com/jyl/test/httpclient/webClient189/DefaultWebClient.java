package com.jyl.test.httpclient.webClient189;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


public class DefaultWebClient implements WebClient {

	private static Timer timer = new Timer("WebClientAutoRefreshThread", true);

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	private DefaultWebClient self;

	private boolean debugMode = false;

	private Map autoRefreshTask = new ConcurrentHashMap();

	private int mode = OUTPUT_MODE_LOG;

	HttpClient httpclient = new DefaultHttpClient();

	Map<String, String> headers = new LinkedHashMap<String, String>(10);

	String defaultCharSet;
	String httpPostCharset = HTTP.UTF_8;

	public DefaultWebClient() {
		//LogDebug("DefaultWebClient() start");
		this.self = this;
		//LogDebug("DefaultWebClient() end");
	}


	private void _setHttpHeader(HttpRequest request) {
		if (debugMode) {
			String debugString = "http request head : " + this.headers;
			if (mode == OUTPUT_MODE_LOG)
				this.LogDebug(debugString);
			else if (mode == OUTPUT_MODE_OUT_PRINT)
				System.out.println(debugString);
		}

		for (Iterator<String> iterator = this.headers.keySet().iterator(); iterator
				.hasNext();) {
			String key = iterator.next();
			request.addHeader(key, this.headers.get(key));
		}
	}
	
	public void setProxy(String proxyHost,int port){
		HttpHost proxy = new HttpHost(proxyHost, port);
	    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}
	
	public void setProxy(String proxyHost,int port,String userName ,String password){
		HttpHost proxy = new HttpHost(proxyHost, port);
		((DefaultHttpClient)httpclient).getCredentialsProvider().setCredentials(
        new AuthScope("proxyHost", port),
        new UsernamePasswordCredentials(userName, password));
	    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	}

	public synchronized void addAutoRefreshUrl(final String url,
			int timeInSeconds) {
		if (url == null) {
			throw new IllegalArgumentException("url can't be null");
		}
		TimerTask task = (TimerTask) this.autoRefreshTask.remove(url);
		if (task != null) {
			task.cancel();
		}
		task = new TimerTask() {

			@Override
			public void run() {
				self.httpGet(url);
			}

		};
		this.autoRefreshTask.put(url, task);
		timer.schedule(task, 0, timeInSeconds * 1000);
		timer.purge();
	}

	public synchronized void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	public synchronized void addHeaders(String[] keys, String[] values) {
		if (keys.length != values.length) {
			IllegalArgumentException e = new IllegalArgumentException(
					"keys-values length not equals");
			this.LogException("", e);
			throw e;
		}
		for (int i = 0; i < keys.length; i++) {
			this.headers.put(keys[i], values[i]);
		}
	}

	private void LogException(String string, Exception e) {
		// TODO Auto-generated method stub
		
	}


	public synchronized void cancelAllAutoRefreshUrl() {
		for (Iterator iterator = this.autoRefreshTask.values().iterator(); iterator
				.hasNext();) {
			TimerTask task = (TimerTask) iterator.next();
			if (task != null) {
				task.cancel();
			}
		}
		this.autoRefreshTask.clear();
		timer.purge();
	}

	public synchronized void cancelAutoRefreshUrl(String url) {
		TimerTask task = (TimerTask) this.autoRefreshTask.remove(url);
		if (task != null) {
			task.cancel();
		}
		timer.purge();
	}

	private String[] executeHttpMethod(HttpUriRequest httpMethod) {
		String[] result = null;
		try {
			String charset = null;
			HttpResponse response = httpclient.execute(httpMethod);
			if (debugMode) {
				String debugString = "http status : "
						+ response.getStatusLine();
				if (mode == OUTPUT_MODE_LOG)
					this.LogDebug(debugString);
				else if (mode == OUTPUT_MODE_OUT_PRINT)
					System.out.println(debugString);
			}

			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			// redirect status
			// 鈥�301 Moved Permanently. HttpStatus.SC_MOVED_PERMANENTLY
			// 鈥�302 Moved Temporarily. HttpStatus.SC_MOVED_TEMPORARILY
			// 鈥�303 See Other. HttpStatus.SC_SEE_OTHER
			// 鈥�307 Temporary Redirect. HttpStatus.SC_TEMPORARY_REDIRECT
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY
					|| statusCode == HttpStatus.SC_MOVED_TEMPORARILY
					|| statusCode == HttpStatus.SC_SEE_OTHER
					|| statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {// redirect

				Header redirectHeader = response.getLastHeader("location");
				if (debugMode) {
					String debugString = "http header >>>"+LINE_SEPARATOR;
					Header[] headers = response.getAllHeaders();
					for (int i = 0; i < headers.length; i++) {
						debugString += headers[i]+LINE_SEPARATOR;
					}
					if (mode == OUTPUT_MODE_LOG)
						this.LogDebug(debugString);
					else if (mode == OUTPUT_MODE_OUT_PRINT)
						System.out.println(debugString);
				}
				String redirectLocation = redirectHeader.getValue();
				redirectLocation = redirectLocation.replaceAll(" ", "%20").replaceAll("\\|", "%70");//TODO redirect new method
				URI uri = httpMethod.getURI();
				if(redirectLocation.startsWith("/")){
					String schema = uri.getScheme();
					String host = uri.getHost();
					int port = uri.getPort();
					redirectLocation =  schema+"://"+host+(port == -1 ? "":":"+port)+redirectLocation;
				}
				HttpGet redirectGet = new HttpGet();
				redirectGet.setURI(uri.resolve(redirectLocation));
				response.getEntity().consumeContent();
				response = httpclient.execute(redirectGet);

				if (debugMode) {
					String debugString = "http redirect url : "
							+ redirectLocation;
					debugString += "redirect result status : "
							+ response.getStatusLine();
					if (mode == OUTPUT_MODE_LOG)
						this.LogDebug(debugString);
					else if (mode == OUTPUT_MODE_OUT_PRINT)
						System.out.println(debugString);
				}
			}

			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++) {
				if (headers[i].getName().equalsIgnoreCase("Content-Type")
						&& headers[i].getValue().toLowerCase().indexOf(
								"charset") != -1) {
					charset = this.getChatsetEncoding(headers[i].getValue());
					if (debugMode) {
						String debugString = "find charset in header : "
								+ charset;
						if (mode == OUTPUT_MODE_LOG)
							this.LogDebug(debugString);
						else if (mode == OUTPUT_MODE_OUT_PRINT)
							System.out.println(debugString);
					}
				}
			}
			if (charset == null)
				charset = this.defaultCharSet;
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release
			List strList = new ArrayList();
			if (entity != null) {
				InputStream instream = entity.getContent();
				Header header = entity.getContentEncoding();
				if (header != null
						&& header.getValue().toLowerCase().indexOf("gzip") != -1) {
					instream = new GZIPInputStream(instream);
				}
				try {

					InputStreamReader inputStreamReader = charset == null ? new InputStreamReader(
							instream)
							: new InputStreamReader(instream, charset);
					BufferedReader reader = new BufferedReader(
							inputStreamReader);
					// do something useful with the response
					String read = null;
					while ((read = reader.readLine()) != null) {
						if (debugMode) {
							if (mode == OUTPUT_MODE_LOG)
								this.LogDebug(read);
							else if (mode == OUTPUT_MODE_OUT_PRINT)
								System.out
										.println(read);
						}
						strList.add(read);
					}
				} catch (IOException ex) {

					// In case of an IOException the connection will be released
					// back to the connection manager automatically
					throw ex;

				} catch (RuntimeException ex) {

					// In case of an unexpected exception you may want to abort
					// the HTTP request in order to shut down the underlying
					// connection and release it back to the connection manager.
					httpMethod.abort();
					throw ex;

				} finally {
					// Closing the input stream will trigger connection release
					instream.close();

				}
				result = (String[]) strList.toArray(new String[strList.size()]);
			}
		} catch (ClientProtocolException e) {
			this.LogException("http exception", e);
			httpMethod.abort();
			e.printStackTrace();
		} catch (IOException e) {
			this.LogException("connection exception", e);
			httpMethod.abort();
			e.printStackTrace();
		}
		return result;
	}

	private void LogDebug(String debugString) {
		// TODO Auto-generated method stub
		
	}


	private HttpEntity executeHttpRawMethod(HttpUriRequest httpMethod) {
		_setHttpHeader(httpMethod);
		try {
			HttpResponse response = httpclient.execute(httpMethod);
			return response.getEntity();
		} catch (ClientProtocolException e) {
			this.LogException("http exception", e);
			httpMethod.abort();
			e.printStackTrace();
		} catch (IOException e) {
			this.LogException("connection exception", e);
			httpMethod.abort();
			e.printStackTrace();
		}
		return null;
	}

	private String getChatsetEncoding(String headString) {
		String temp = headString.toLowerCase();
		temp = temp.substring(temp.indexOf("charset"));
		if (temp.indexOf(";") == -1) {
			temp = temp.substring(temp.indexOf("=") + 1).trim();
		} else {
			temp = temp.substring(temp.indexOf("=") + 1, temp.indexOf(";"))
					.trim();
		}
		return temp.toUpperCase();
	}

	public String getDefaultCharSet() {
		return this.defaultCharSet;
	}

	public synchronized Map<String, String> getHeaders() {
		Map<String, String> result = new HashMap<String, String>(10);
		result.putAll(this.headers);
		return result;
	}

	public String getHttpPostCharset() {
		return httpPostCharset;
	}

	public int getOutputMode() {
		return mode;
	}

	public String httpGet(String url) {
		return this.turnArrayResultToString(this.httpGetArray(url));
	}

	public synchronized String[] httpGetArray(String url) {
		if (debugMode) {
			String methodString = "http method : get";
			String urlString = "http url : " + url;
			if (mode == OUTPUT_MODE_LOG) {
				this.LogDebug(methodString);
				this.LogDebug(urlString);
			} else if (mode == OUTPUT_MODE_OUT_PRINT) {
				System.out.println(methodString);
				System.out.println(urlString);
			}
		}
		HttpGet httpGet = new HttpGet(url);
		_setHttpHeader(httpGet);
		return executeHttpMethod(httpGet);
	}

	public synchronized HttpEntity httpGetRaw(String url) {
		if (debugMode) {
			String methodString = "raw http method : get";
			String urlString = "raw http url : " + url;
			if (mode == OUTPUT_MODE_LOG) {
				this.LogDebug(methodString);
				this.LogDebug(urlString);
			} else if (mode == OUTPUT_MODE_OUT_PRINT) {
				System.out.println(methodString);
				System.out.println(urlString);
			}
		}
		HttpGet httpGet = new HttpGet(url);
		return executeHttpRawMethod(httpGet);
	}

	public String httpPost(String url, Map<String, Object> valueMap) {
		return this.turnArrayResultToString(this.httpPostArray(url, valueMap));
	}

	public synchronized String[] httpPostArray(String url,
			Map<String, Object> valueMap) {
		if (debugMode) {
			String methodString = "http method : post";
			String urlString = "http url : " + url;
			String paramsString = "http params : " + valueMap;
			if (mode == OUTPUT_MODE_LOG) {
				this.LogDebug(methodString);
				this.LogDebug(urlString);
				this.LogDebug(paramsString);
			} else if (mode == OUTPUT_MODE_OUT_PRINT) {
				System.out.println(methodString);
				System.out.println(urlString);
				System.out.println(paramsString);
			}
		}
		HttpPost httpPost = new HttpPost(url);
		postValueSetUp(valueMap, httpPost);
		return executeHttpMethod(httpPost);
	}

	public synchronized HttpEntity httpPostRaw(String url,
			Map<String, Object> valueMap) {
		if (debugMode) {
			String methodString = "raw http method : post";
			String urlString = "raw http url : " + url;
			String paramsString = "raw http params : " + valueMap;
			if (mode == OUTPUT_MODE_LOG) {
				this.LogDebug(methodString);
				this.LogDebug(urlString);
				this.LogDebug(paramsString);
			} else if (mode == OUTPUT_MODE_OUT_PRINT) {
				System.out.println(methodString);
				System.out.println(urlString);
				System.out.println(paramsString);
			}
		}
		HttpPost httpPost = new HttpPost(url);
		postValueSetUp(valueMap, httpPost);
		return executeHttpRawMethod(httpPost);
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	private void postValueSetUp(Map<String, Object> valueMap, HttpPost httpPost) {
		if (valueMap != null) {
			BasicNameValuePair a = new BasicNameValuePair("", "");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator<String> iterator = valueMap.keySet().iterator(); iterator
					.hasNext();) {
				String name = iterator.next();
				Object valueObject = valueMap.get(name);
				if (valueObject == null) {
					throw new IllegalArgumentException("valueMap error : "
							+ name + " is null");
				}
				if (valueObject instanceof String) {
					nvps
							.add(new BasicNameValuePair(name,
									(String) valueObject));
				} else if (valueObject instanceof Collection) {
					Collection c = (Collection) valueObject;
					for (Iterator iterator2 = c.iterator(); iterator2.hasNext();) {
						String value = (String) iterator2.next();
						nvps.add(new BasicNameValuePair(name, value));
					}
				} else if (valueObject instanceof String[]) {
					String values[] = (String[]) valueObject;
					for (int i = 0; i < values.length; i++) {
						nvps.add(new BasicNameValuePair(name, values[i]));
					}
				} else {
					nvps
					.add(new BasicNameValuePair(name,
							 valueObject.toString()));
				}
			}
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps,
						this.httpPostCharset));
			} catch (UnsupportedEncodingException e) {
				this.LogException("charset error:" + this.httpPostCharset, e);
				e.printStackTrace();
			}
		}
	}

	public synchronized String removeHeader(String key) {
		return this.headers.remove(key);
	}

	public synchronized String[] removeHeaders(String[] keys) {
		String[] result = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			result[i] = this.headers.remove(keys[i]);
		}
		return result;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public void setDefaultCharSet(String charset) {
		this.defaultCharSet = charset;
	}

	public synchronized void setHeaders(Map<String, String> headers) {
		this.headers.clear();
		this.headers.putAll(headers);
	}

	public void setHttpPostCharset(String httpPostCharset) {
		this.httpPostCharset = httpPostCharset;
	}

	public void setOutputMode(int mode) {
		this.mode = mode;
	}

	public synchronized void shutDown() {
		if (debugMode) {
			String debugString = "web client shutDown";
			if (mode == OUTPUT_MODE_LOG) {
				this.LogDebug(debugString);
			} else if (mode == OUTPUT_MODE_OUT_PRINT) {
				System.out.println(debugString);
			}
		}
		this.cancelAllAutoRefreshUrl();
		this.httpclient.getConnectionManager().shutdown();
	}

	public synchronized void sleep() {
		if (debugMode) {
			String debugString = "web client sleep";
			if (mode == OUTPUT_MODE_LOG) {
				this.LogDebug(debugString);
			} else if (mode == OUTPUT_MODE_OUT_PRINT) {
				System.out.println(debugString);
			}
		}
		this.httpclient.getConnectionManager().closeExpiredConnections();
	}

	private String turnArrayResultToString(String[] arrayString) {
		if (arrayString == null)
			return null;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arrayString.length; i++) {
			sb.append(arrayString[i]).append(LINE_SEPARATOR);
		}
		return sb.toString();
	}

}
