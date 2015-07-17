package com.wallet.membership.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class gpsAddressGetter {
	String GpsX="",GpsY="";
	public gpsAddressGetter(String Address) {
		// TODO Auto-generated constructor stub
		try{
			String strURL = "http://maps.google.com/maps/geo?q=" + URLEncoder.encode(Address, "UTF-8") + "&output=csv&key=";
			
			String result = getHTML (strURL);
			String[] strArr = result.split(",");
			
			if (strArr.length > 3) {
				GpsX = strArr[2];
				GpsY = strArr[3];
			} else {
					System.out.println("[GpsAddress] : " +  "ÁÂÇ¥ ¿À·ù");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("[GpsAddress] : " +  "ÁÂÇ¥ ¿À·ù");
		}
	}	
	
	public String getHTML(String urlToRead) {
    URL url;
    HttpURLConnection conn;
    BufferedReader rd;
    String line;
    String result = "";
    try {
       url = new URL(urlToRead);
       conn = (HttpURLConnection) url.openConnection();
       conn.setRequestMethod("GET");
       rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
       while ((line = rd.readLine()) != null) {
          result += line;
       }
       rd.close();
    } catch (Exception e) {
       e.printStackTrace();
    }
    return result;
 }

	public String getGpsX() {
		return GpsX;
	}

	public void setGpsX(String gpsX) {
		GpsX = gpsX;
	}

	public String getGpsY() {
		return GpsY;
	}

	public void setGpsY(String gpsY) {
		GpsY = gpsY;
	}    
	
}
