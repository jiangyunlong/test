package com.jyl.test.httpclient.webClient189;

import java.util.Map;

import org.apache.http.HttpEntity;
/**
 * 
 * @author llade
 * simple interface of WebClient.
 *
 */

public interface WebClient {
	/**
	 * HTTP get method,use for load HTML content from URL.
	 * @param url
	 * @return
	 */
	public String httpGet(String url);
	/**
	 * HTTP get method,use for load HTML content from URL,return String array as result.
	 * @param url
	 * @return
	 */
	public String[] httpGetArray(String url);
	/**
	 * get raw HTTP content(such as image,and so on),be careful ,you must close the InputStream associated with this HttpEntity at last.
	 * @param url
	 * @return
	 */
	public HttpEntity httpGetRaw(String url);
	/**
	 * HTTP get method,use for post data,and get result content.
	 * @param url
	 * @param valueMap
	 * @return
	 */
	public String httpPost(String url,Map<String, Object> valueMap);
	/**
	 * HTTP get method,use for post data,and get result content,return String array as result.
	 * @param url
	 * @param valueMap
	 * @return
	 */
	public String[] httpPostArray(String url,Map<String, Object> valueMap);
	/**
	 * get raw HTTP content(such as image,and so on) after post,be careful ,you must close the InputStream associated with this HttpEntity at last.
	 * @param url
	 * @return
	 */
	public HttpEntity httpPostRaw(String url,Map<String, Object> valueMap);
	/**
	 * shut down the WebClient instance ,and release resource allocated,HTTP Session and Cookie will lose 
	 */
	public void shutDown();
	/**
	 * close the HTTP idle connect ,release idle socket connection,but don't shutdown the WebClient <br/>
	 * ,that means the WebClient can use again,and HTTP Session and cookies still validate.
	 */
	public void sleep();
	/**
	 * add request header锛宎fter that ,in each request,the header will add.
	 * @param key
	 * @param value
	 */
	public void addHeader(String key,String value);
	/**
	 * the multi of method addHeader;
	 * @param keys
	 * @param values
	 */
	public void addHeaders(String[] keys,String[] values);
	/**
	 * remove the header
	 * @param key
	 * @return
	 */
	public String removeHeader(String key);
	/**
	 * the multi of method removeHeader;
	 * @param keys
	 * @return
	 */
	public String[] removeHeaders(String[] keys);
	/**
	 * get headers
	 * @return
	 */
	public Map<String,String> getHeaders();
	/**
	 * set headers
	 * @param headers
	 */
	public void setHeaders(Map<String,String> headers);
	/**
	 * set default charset the WebClient use to decode string,Or the WebClient will parse Http Head to determine which charset.
	 * @param charset
	 */
	public void setDefaultCharSet(String charset);
	/**
	 * get default charset the WebClient use to decode string
	 * @return
	 */
	public String getDefaultCharSet();
	/**
	 * get post encoding charset 
	 * @return
	 */
	public String getHttpPostCharset() ;
	/**
	 * set post encoding charset ,default is utf-8
	 * @param httpPostCharset
	 */
	public void setHttpPostCharset(String httpPostCharset) ;
	/**
	 * test if it is in debug mode 
	 * @return
	 */
	public boolean isDebugMode();
	/**
	 * turn on/off debug mode
	 * @param debugMode
	 */
	public void setDebugMode(boolean debugMode);
	/**
	 * get output mode
	 * @return
	 */
	public int getOutputMode();
	/**
	 * set debug output mode ,this option work only in debug mode on <br/>
	 * OUTPUT_MODE_OUT_PRINT is use System.out.println(String str);<br/>
	 * OUTPUT_MODE_LOG use log
	 * @param mode
	 */
	public void setOutputMode(int mode);
	/**
	 * set auto refresh url to prevent http session timeout or other purpose. must be careful
	 * @param url
	 * @param timeInSeconds
	 */
	public void addAutoRefreshUrl(String url,int timeInSeconds);
	/**
	 * cancel all auto refresh url task hold by this object
	 */
	public void cancelAllAutoRefreshUrl();
	/**
	 * cancel the auto refresh url task hold by this object
	 * @param url the right url
	 */
	public void cancelAutoRefreshUrl(String url);
	/**
	 * System.out mode Constant
	 */
	public static final int OUTPUT_MODE_OUT_PRINT=0;
	/**
	 * log mode Constant
	 */
	public static final int OUTPUT_MODE_LOG=1;
	/**
	 * set proxy host;
	 * @param proxyHost
	 * @param port
	 */
	public void setProxy(String proxyHost,int port);
	
	/**
	 * set proxy host;
	 * @param proxyHost
	 * @param port
	 * @param userName
	 * @param password
	 */
	public void setProxy(String proxyHost,int port,String userName,String password);
}
