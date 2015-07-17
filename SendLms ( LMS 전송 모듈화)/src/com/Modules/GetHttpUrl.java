package com.Modules;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GetHttpUrl {

	private static final Logger logger = LoggerFactory
			.getLogger(GetHttpUrl.class);
	private String seturl;
	private InputStream io;

	public GetHttpUrl(String SetUrl) {
		this.seturl = SetUrl;
	}

	public String RunHttp() {
		URL url = null;
		StringBuffer strbuf = new StringBuffer(4096);

		try {
			System.out.println(" seturl ---->[" + seturl + "]");
			url = new URL(seturl);
			URLConnection urlcon = url.openConnection();

			// URL 클래스의 openConnection()을 이용하여 URLConnection 객체를 얻는다.

			String contentType = urlcon.getContentType();

			// 웹 문서의 헤드에서 contentType를 읽어 온다.

			long dl = urlcon.getDate();
			// 읽어 온 시간을 ms로 반환한다.

			java.util.Date d = new java.util.Date(dl);

			java.text.SimpleDateFormat format =	new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
			String sdate = format.format(d);

			System.out.println("Content Type : " + contentType);
			System.out.println("읽어온 시간 : " + sdate);

			// InputStream in = urlcon.getInputStream();
			BufferedInputStream bis;
			setIo(urlcon.getInputStream());
			bis = new BufferedInputStream(urlcon.getInputStream());
			

 
			byte[] buf = new byte[4096];

		

			int read = 0;
			int idx = 0;

			try {
				while ((read = bis.read(buf)) > 0) {
 
				//strbuf.append(new String(buf, 0, read, "EUC-KR"));
					strbuf.append(new String(buf, 0, read));

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
 
		return strbuf.toString();
	}
	public String RunHttp_v2() {
		URL url = null;
		StringBuffer strbuf = new StringBuffer(4096);

		try {
			url = new URL(seturl);
			URLConnection urlcon = url.openConnection();

			// URL 클래스의 openConnection()을 이용하여 URLConnection 객체를 얻는다.

			String contentType = urlcon.getContentType();

			// 웹 문서의 헤드에서 contentType를 읽어 온다.

			long dl = urlcon.getDate();
			// 읽어 온 시간을 ms로 반환한다.

			java.util.Date d = new java.util.Date(dl);

			java.text.SimpleDateFormat format =

			new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
			String sdate = format.format(d);

			System.out.println("Content Type : " + contentType);
			System.out.println("읽어온 시간 : " + sdate);

			// InputStream in = urlcon.getInputStream();
			BufferedInputStream bis;
			setIo(urlcon.getInputStream());
			bis = new BufferedInputStream(urlcon.getInputStream());

			
			BufferedReader br = new BufferedReader(	new InputStreamReader(urlcon.getInputStream(),"UTF-8"));	

 
			byte[] buf = new byte[4096];

		

			int read = 0;
			int idx = 0;
			for (;;) {						
				
				String line = br.readLine();				
				
				
				if (line == null) break;
				strbuf.append(line + '\n'); 
					//strbuf.append(new String(buf, 0, read, "EUC-KR"));

			}
			br.close();
/*			try {
				while ((read = br.read(buf)) > 0) {
					logger.info("read : " + (new String(br, 0, read) )) ;
				//	strbuf.append(new String(buf, 0, read, "EUC-KR"));
					strbuf.append(new String(buf, 0, read));

				}
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
 
		return strbuf.toString();
	}
	
 
	

	/**
	 * @return the io
	 */
	public InputStream getIo() {
		return io;
	}

	/**
	 * @param io the io to set
	 */
	public void setIo(InputStream io) {
		this.io = io;
	}

}
