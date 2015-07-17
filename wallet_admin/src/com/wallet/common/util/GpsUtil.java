package com.wallet.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GpsUtil
{
	private static boolean debug=false;
	private static boolean yahooAccessFlag=true;
	private static boolean daumAccessFlag=true;
	private static boolean googleAccessFlag=true;
	private static boolean naverAccessFlag=true;
	private static int naverCvtCnt=0;

	
	public static String get_gps_value(String pos) {
		String gps="";
		String padStr = "0000000";

		int idx = pos.indexOf('"');
		
		if (idx > 0) {
			gps = pos.substring(0, idx) + padStr.substring(0, pos.length() - idx);
		} else {
			gps = pos;
		}
		//System.out.println("Found Index = "+idx+", orgStr="+pos+", newStr="+gps);
		return gps;
	}

	public String[]  get_yahoo_gps(String addr)
	{
		
		int ret = -1;
		String[] info = new String[4];
		
		info[0] = "";
		info[1] = "";
		info[2] = "01";
		
        BufferedReader in = null;

		String key = "yMvZwQvV34HEFRwOQ6n7ek0kxiD8B4.KDuGMI3SSOfjsFEs6u3ujjX6rDdAB6zlX2RerRMa5CMhlu5eyyeYTnaPHBZup_so";
//			String urlStr = "http://kr.open.gugi.yahoo.com/service/poi.php?appid="+key+"&output=json";
		String urlStr = "http://119.161.14.234/service/poi.php?appid="+key+"&output=json";

		try {
			String line, jsonStr="";
			URL url = new URL(urlStr+"&q="+java.net.URLEncoder.encode(addr, "UTF-8"));
			URLConnection conn = url.openConnection(); 

			conn.connect();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ( ( line = in.readLine() )!= null) {
				jsonStr = jsonStr + line;
			}
			in.close();
			in = null;

			if (debug) System.out.println("get_yahoo_gps() : " + jsonStr);

			int strIdx;
			if ((strIdx = jsonStr.indexOf("latitude")) < 0) 
			{
                Log.info("get_yahoo_gps() : 위도 값 추출 오류");
				info[3] = "WD";
				return info;
			} else 
			{
				info[0] = get_gps_value(jsonStr.substring(strIdx+11, strIdx+11+10));

				if ((strIdx = jsonStr.indexOf("longitude")) < 0) 
				{
                	Log.info("get_yahoo_gps() : 경도 값 추출 오류");
					info[3] = "KD";
					return  info;
				} else 
				{
					info[1] = get_gps_value(jsonStr.substring(strIdx+12, strIdx+12+11));
					info[2] = "01"; /* for the Yahoo Service */
					info[3] = "OK";

					Log.info("get_yahoo_gps() : API = "+info[2]+", 위도 = "+info[0]+", 경도 = "+info[1]);
					ret = 0;
				}
			}
		} catch (MalformedURLException malformedurlexception) {
			System.out.println("get_yahoo_gps() : Malformed URL Exception = "+malformedurlexception.toString());
                } catch (IOException ioexception) {
			yahooAccessFlag=false;
			Log.info("get_yahoo_gps() : Service is not available");
			System.out.println("get_yahoo_gps() : IO Exception = "+ioexception.toString());
                } finally {
            		try {
                		if (in != null) {
                    			in.close();
                		}
            		} catch (Exception ex3) {
				System.out.println("get_yahoo_gps() : BufferedReader close Exception");
                        	ex3.printStackTrace();
            		}
        	}

        return info;
	}
	
	public String[] get_daum_gps(String addr)
	{
		int ret = -1;
		String[] info = new String[4];
		
		info[0] = "";
		info[1] = "";
		info[2] = "02";
		
	    BufferedReader in = null;

		String key = "cde8f9392871f957f1706f9e613322a4cdbc3869";
//			String urlStr = "http://apis.daum.net/maps/addr2coord?apikey="+key+"&output=json&callback=";
		String urlStr = "http://118.218.124.212/maps/addr2coord?apikey="+key+"&output=json";

		try {
			String line, jsonStr="";
			URL url = new URL(urlStr+"&q="+java.net.URLEncoder.encode(addr, "UTF-8"));
			URLConnection conn = url.openConnection(); 
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ( ( line = in.readLine() )!= null) {
                    jsonStr = jsonStr + line;
            }
            in.close();
            in = null;
            
            if (debug) System.out.println("get_daum_gps() : " + jsonStr);

            if (jsonStr.indexOf("dcode") > 0) 
            {
				daumAccessFlag = false;	
        		in.close();
        		info[3] = "LIMIT";
				Log.info("get_daum_gps() : Access Denied - traffic overed");
				return info;
			}

            int strIdx;
            if ((strIdx = jsonStr.indexOf("point_y")) < 0) {
                Log.info("get_daum_gps() : 위도 값 추출 오류");
				info[3] = "WD";
				return info;
		    } else 
            {
                info[0] = get_gps_value(jsonStr.substring(strIdx+9, strIdx+9+10));
                if ((strIdx = jsonStr.indexOf("point_x")) < 0) {
                	Log.info("get_daum_gps() : 경도 값 추출 오류");
            		info[3] = "KD";
					return info;
				} else {
                    info[1] = get_gps_value(jsonStr.substring(strIdx+9, strIdx+9+11));
                    info[2] = "02"; /* for the Daum Service */
                    Log.info("get_daum_gps() : API = "+info[2]+", 위도 = "+info[0]+", 경도 = "+info[1]);
                    info[3] = "OK";
                    ret = 0;
                }
            }
		} catch (MalformedURLException malformedurlexception) {
			System.out.println("get_daum_gps()  : Malformed URL Exception = "+malformedurlexception.toString());
                } catch (IOException ioexception) {
			System.out.println("get_daum_gps() : IO Exception = "+ioexception.toString());
                } finally {
            		try {
                		if (in != null) {
                    			in.close();
                		}
            		} catch (Exception ex3) {
				System.out.println("get_daum_gps() : BufferedReader close Exception");
                        	ex3.printStackTrace();
            		}
        	}

        return info;
	}

	public String[] get_google_gps(String addr)
	{
		int ret = -1;
		String[] info = new String[4];
		info[2] = "03";
		
        BufferedReader in = null;

//			String urlStr = "http://maps.google.co.kr/maps/api/geocode/json?&sensor=false";
		String urlStr = "http://72.14.203.138/maps/api/geocode/json?&sensor=false";

		try {
			String line, jsonStr="";
			URL url = new URL(urlStr+"&address="+java.net.URLEncoder.encode(addr, "UTF-8"));
			URLConnection conn = url.openConnection(); 

			conn.connect();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ( ( line = in.readLine() )!= null) {
				jsonStr = jsonStr + line;
			}
			in.close();
			in = null;
			
            if (debug) System.out.println("get_google_gps() : " + jsonStr);

            if (jsonStr.indexOf("OVER_QUERY_LIMIT") > 0) {
				googleAccessFlag = false;	
                Log.info("get_google_gps() : Access Denied - exceed the query limit");
                info[3] = "LIMIT";
                return info;
			}

            int strIdx;
            if ((strIdx = jsonStr.indexOf("lat")) < 0) 
            {
                Log.info("get_google_gps() : 위도 값 추출 오류");
				info[3] = "WD";
				return info;
		    } else 
            {
            	info[0] = get_gps_value(jsonStr.substring(strIdx+7, strIdx+7+10));

            	if ((strIdx = jsonStr.indexOf("lng")) < 0) 
            	{
                	Log.info("get_google_gps() : 경도 값 추출 오류");
                	info[3] = "KD";
                	return info;
		    	} else {
            		info[1] = get_gps_value(jsonStr.substring(strIdx+7, strIdx+7+11));
            		info[2] = "03"; /* for the Google Service */
            		Log.info("get_google_gps() : API = "+info[2]+", 위도 = "+info[0]+" ,경도 = "+info[1]);
					ret = 0;
					info[3] = "OK";
				}
			}
		} catch (MalformedURLException malformedurlexception) {
			System.out.println("get_google_gps() : Malformed URL Exception = "+malformedurlexception.toString());
                } catch (IOException ioexception) {
			System.out.println("get_google_gps() : IO Exception = "+ioexception.toString());
                } finally {
            		try {
                		if (in != null) {
                    			in.close();
                		}
            		} catch (Exception ex3) {
				System.out.println("get_google_gps() : BufferedReader close Exception");
                        	ex3.printStackTrace();
            		}
        	}

        return info;
	}
	
	public String[] get_naver_gps(String addr)
	{
		int ret = -1;
		String[] info = new String[4];
		info[2] = "04";
		
        BufferedReader in = null;

		String key = "0eb15704b43722225a372307c549b9d0";
//			String urlStr = "http://openapi.map.naver.com/api/geocode.php?key="+key+"&encoding=euc-kr&coord=latlng";
		String urlStr = "http://222.122.212.129/api/geocode.php?key="+key+"&encoding=euc-kr&coord=latlng";

		try {
			String line, xmlStr="";
			URL url = new URL(urlStr+"&query="+java.net.URLEncoder.encode(addr, "UTF-8"));
			URLConnection conn = url.openConnection(); 

			naverCvtCnt++;
			if (naverCvtCnt > 90000) 
				naverAccessFlag = false;

			conn.connect();

			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ( ( line = in.readLine() )!= null) {
				xmlStr = xmlStr + line;
			}
			in.close();
			in = null;
			

	        if (debug) System.out.println("get_naver_gps() : " + xmlStr);
	
	        if (xmlStr.indexOf("over the limit") > 0) {
				naverAccessFlag = false;	
        		Log.info("get_naver_gps() : Access Denied - exceed the query limit");
        		info[3] = "LIMIT";
				return info;
			}

            int strIdx;
            if ((strIdx = xmlStr.indexOf("<y>")) < 0) {
                    Log.info("get_naver_gps() : 위도 값 추출 오류");
                    info[3] = "WD";
    				return info;
             } else 
             {
                    info[0] = get_gps_value(xmlStr.substring(strIdx+3, strIdx+3+10));

                    if ((strIdx = xmlStr.indexOf("<x>")) < 0) 
                    {
                            Log.info("get_naver_gps() : 경도 값 추출 오류");
                            info[3] = "WD";
            				return info;
                     } else {
                            info[1] = get_gps_value(xmlStr.substring(strIdx+3, strIdx+3+11));
                            info[2] = "04"; // for the Naver Service 
                            Log.info("get_naver_gps() : API = "+info[2]+", 위도 = "+info[0]+" 경도 = "+info[1]);
                            info[3] = "OK";
                            ret = 0;
                    }
            }
		} catch (MalformedURLException malformedurlexception) {
			System.out.println("get_naver_gps() : Malformed URL Exception = "+malformedurlexception.toString());
                } catch (IOException ioexception) {
			Log.info("get_naver_gps() : IO Exception = "+ioexception.toString());
                } finally {
            		try {
                		if (in != null) {
                    			in.close();
                		}
            		} catch (Exception ex3) {
				Log.info("get_naver_gps() : BufferedReader close Exception");
                        	ex3.printStackTrace();
            		}
        	}
            return info;
	}

	public String[] get_gps_info( String addr)
	{
		/*
		 * The info[3] includes the following value : 
 		 *
		 *     info[0] 		// 위도 값 
		 *     info[1] 		// 경도 값
		 *     info[2] 		// API 구분 값 : 01(야후), 02(다음), 03(구글), 04(네이버)
	     */
		String[] info  = null;
		if(yahooAccessFlag){
			info = get_yahoo_gps(addr);
			if (info[3].equals("OK") )  
				return info;
		}
		if(daumAccessFlag){
			info = get_daum_gps(addr);
			if (info[3].equals("OK") ) 
				return info;
		}
		if(googleAccessFlag){
			info = get_google_gps( addr);
			if (info[3].equals("OK") ) 
				return info;

		}
		if(naverAccessFlag){
			info = get_naver_gps(addr);
		}
		return info;
	}
	
}
