package com.wallet.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;


public class NetworkUtil
{
	protected Logger logger = Logger.getLogger(getClass());
	String 		khub_ip;
	int				khub_port  =80;
	
	// 소켓을 만들기 위한 인스턴스들
	private Socket socket = null;

	// 소켓에서 스트림을 얻어오기 위한 인스턴스들
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	
	String xml_header = ""
			+ "<?xml version=\"1.0\" encoding=\"euc-kr\"?>"
			+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" "
			+ "xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" "
			+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
			+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
			+ "xmlns:ns1=\"http://192.168.0.2/\" "
			+ "xmlns:ns2=\"http://ws.oi.khub.ktf.com/\"> "
			+ "<SOAP-ENV:Body><ns2:Invoke_APIElement>";
	
				
	public NetworkUtil(String  host, int port)
	{
		khub_ip =  host;
		khub_port = port;
		socket = null;
		bis = null;
		bos = null;
	}
	
	public NetworkUtil()
	{
		socket = null;
		bis = null;
		bos = null;
	}
	
	
	
// return 	[REMAIN]
 /* public HashMap  get_egg_amt_munja (String phone) {
	  HashMap h_map = new HashMap();
		String tmp =khub_header
				+ "<String_1>BILL_REQPREVALNOW</String_1><String_2>"
				+ authKey + "</String_2> " + "<WsParamSet_3>" 
				+ "<param><value>"+phone+"</value><name>CALL_CTN</name></param>" 
				+ "<param><value>R</value><name>R_FLAG</name></param>"
				+ "</WsParamSet_3>"
				+ "</ns2:Invoke_APIElement>" + "</SOAP-ENV:Body>"
				+ "</SOAP-ENV:Envelope>";

		String xml =  khub_send( SoapHeaderSet (tmp) );
		disconnect();
		
		// all error case 
		if( xml.length() == 3)
		{
			h_map = null;
			return h_map;
		}
		h_map = KhubXml( xml  );
		return h_map;
	}
  */
  
	public String   socket_send(String url, String param,  int mode)
	{
		
		logger.info(" IP ["+ khub_ip + "] ==> PORT[" + khub_port + "]");
		try {
			socket = new Socket(khub_ip, khub_port);
		} catch (IOException e1) {
				e1.printStackTrace();
				return "E01";
		}
		
		String msg = http_post_header(url, param);
		
		logger.info(" IP ["+ khub_ip + "] ==> PORT[" + khub_port + "]\n" + msg);
		
			
		try {
			bos = new BufferedOutputStream(socket.getOutputStream());
			bos.write(msg.getBytes());
			bos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "E02";
		}
		
		if(mode == 100) 
		{
			disconnect();
			return "OK";
		}
		
		InputStream in;
		StringBuffer str;
		try {
			in = new BufferedInputStream(socket.getInputStream());
			BufferedReader reader = new BufferedReader(	new InputStreamReader(in ,"UTF-8"));	
			String inputLine = null;
			str = new StringBuffer();
			while ((inputLine = reader.readLine()) != null) {
			 	str.append(inputLine);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "E03";
		}
		return str.toString();
	}
	
public String   socket_send(String url,  int mode)
{
		
	logger.info(" IP ["+ khub_ip + "] ==> PORT[" + khub_port + "]");
	try {
		socket = new Socket(khub_ip, khub_port);
	} catch (IOException e1) {
			e1.printStackTrace();
			return "E01";
	}
	
	String msg = http_get_header(url);
	
	logger.info(" IP ["+ khub_ip + "] ==> PORT[" + khub_port + "]\n" + msg);
	
		
	try {
		bos = new BufferedOutputStream(socket.getOutputStream());
		bos.write(msg.getBytes());
		bos.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "E02";
	}
	
	if(mode == 100) 
	{
		disconnect();
		return "OK";
	}
	
	InputStream in;
	StringBuffer str;
	try {
		in = new BufferedInputStream(socket.getInputStream());
		BufferedReader reader = new BufferedReader(	new InputStreamReader(in ,"UTF-8"));	
		String inputLine = null;
		str = new StringBuffer();
		while ((inputLine = reader.readLine()) != null) {
		 	str.append(inputLine);
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "E03";
	}
	return str.toString();
}
	
	
	 public void disconnect()  {
	        try {
				bis.close();
			} catch (Exception e) {
			}
	        try {
				bos.close();
			} catch (Exception e) {
			}
	        try {
			    socket.close();
			} catch (Exception e) {
			}
	    }
	 
	 public String  http_get_header(String url) {
			
		    StringBuffer message = new StringBuffer();
		    message.append("GET "+url+" HTTP/1.1\n");
		    message.append("Host: "+khub_ip+"\n");
		    message.append("Content-Type: text/plain\n"); 
	 	    message.append("Connection: Close\n\n"); 
		    return message.toString() ;
		} 
	 
	 
	 public String  http_post_header(String url, String xml) {
			
	    StringBuffer message = new StringBuffer();
	    String xml2 = "";
	    
	    try {
			 xml2  = new String ( xml.getBytes( "UTF-8") );
			 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    int length = xml2.getBytes().length;
	    
	    message.append("POST "+url+" HTTP/1.1\r\n");
	    message.append("Host: "+khub_ip+"\r\n");
	    message.append("Content-Type: text/plain\r\n"); 
	    message.append("Content-Type: application/x-www-form-urlencoded\n");
	    message.append("User-Agent: Mozilla/4.0 (compatible;MSIE 5.5;Windows NT 5.0)\r\n");
	    message.append("Content-Length:  " +length + "\r\n"); 
 	    message.append("Connection: Close\r\n\n"); 
	    message.append(xml2);
	    return message.toString() ;
	} 
	 
	// common get  
	 public String get_http(String seturl ) 
	 {
		URL url = null;
		StringBuffer strbuf = new StringBuffer(8084);
		InputStream io = null;
		BufferedInputStream bis = null;
		URLConnection urlcon =null;
	
		try {
			url = new URL(seturl);
			urlcon = url.openConnection();
			String contentType = urlcon.getContentType();

			urlcon.getInputStream();
			bis = new BufferedInputStream(urlcon.getInputStream());
		 
			byte[] buf = new byte[8084];

			int read = 0;
			int idx = 0;

			try {
				while ((read = bis.read(buf)) > 0) {
					logger.info("read : " + (new String(buf, 0, read) )) ;
					strbuf.append(new String(buf, 0, read));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		try {
			bis.close();
		} catch (Exception e) {
		}
		return strbuf.toString();
	}


 public String post_http(String seturl, String param ) 
 {
	URL url = null;
	StringBuffer strbuf = new StringBuffer(8084);
	InputStream io = null;
	BufferedInputStream bis = null;
	URLConnection urlcon =null;

	try {
		url = new URL(seturl);
		urlcon = url.openConnection();
		urlcon.setDoOutput(true);
		urlcon.setDoInput(true);
		
		((HttpURLConnection)urlcon).setFollowRedirects(false);
		((HttpURLConnection)urlcon).setRequestMethod("POST");
		urlcon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		urlcon.setRequestProperty("User-Agent","Mozilla/4.0 (compatible;MSIE 5.5;Windows NT 5.0)");
		urlcon.connect();
		
		OutputStreamWriter  out = new OutputStreamWriter(urlcon.getOutputStream());
		out.write(param);
		out.flush();
		out.close();
		
		urlcon.getInputStream();
		bis = new BufferedInputStream(urlcon.getInputStream());
	 
		byte[] buf = new byte[8084];

		int read = 0;
		int idx = 0;

		try {
			while ((read = bis.read(buf)) > 0) {
				logger.info("read : " + (new String(buf, 0, read) )) ;
				strbuf.append(new String(buf, 0, read));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	} catch (Exception e) {
		e.printStackTrace();// TODO: handle exception
	}
	
	try {
		bis.close();
	} catch (Exception e) {
	}
	return strbuf.toString();
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
}