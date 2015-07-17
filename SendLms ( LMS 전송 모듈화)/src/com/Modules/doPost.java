package com.Modules;

import java.net.URL;

import org.apache.log4j.Logger;

import HTTPClient.HttpURLConnection;

public class doPost {
	protected Logger logger = Logger.getLogger(getClass());
	
	public String doPost(String sURL, String data) {

	    boolean doSuccess = false;
	    java.io.BufferedWriter wr = null;
	    String ret="";
	    try {

	      URL url = new URL(sURL);
	      HttpURLConnection URLConn = (HttpURLConnection) url.openConnection();

	      URLConn.setDoOutput(true);
	      URLConn.setDoInput(true);
	      ((HttpURLConnection) URLConn).setRequestMethod("POST");
	      URLConn.setUseCaches(false);
	      HttpURLConnection.setFollowRedirects(true);
	      URLConn.setInstanceFollowRedirects(true);

	      URLConn.setRequestProperty(
	              "User-agent",
	              "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) "
	                  + "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
	      URLConn.setRequestProperty("Accept",
	              "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	      
	      /*
	      if (cookie != null)
	        URLConn.setRequestProperty("Cookie", cookie);
	      if (referer != null)
	        URLConn.setRequestProperty("Referer", referer);
	       	*/
	      
	      URLConn.setRequestProperty("Content-Type",
	          "application/x-www-form-urlencoded");
	      URLConn.setRequestProperty("Content-Length", String.valueOf(data
	          .getBytes().length));

	      java.io.DataOutputStream dos = new java.io.DataOutputStream(URLConn
	          .getOutputStream());
	      dos.writeBytes(data);

	      java.io.BufferedReader rd = new java.io.BufferedReader(
	          new java.io.InputStreamReader(URLConn.getInputStream(),"EUC-KR"));
	      String line;
	      
	      while ((line = rd.readLine()) != null) {
	    	  ret = ret + line;
	      }
	      
	      rd.close();
	      return ret;
	    } catch (java.io.IOException e) {
	      doSuccess = false;
	      logger.info(e);

	    } finally {
	      if (wr != null) {
	        try {
	          wr.close();
	        } catch (java.io.IOException ex) {
	          logger.info(ex);
	        }
	        wr = null;
	      }
	    }
	    return ret;
	  }
}
