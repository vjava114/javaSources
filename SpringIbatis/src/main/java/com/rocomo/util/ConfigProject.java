package com.rocomo.util;

import java.util.HashMap;
import java.util.Map;

public class ConfigProject {
	
	/*
	 *  이 클래스의 용도.
	 *  	1. 전역변수 관리
	 *  	2. DB 접속정보 등 개발과 상용버전에 따라 달라야 하는 셋팅정보 관리
	 */
	

	// 1. 전역변수들 선언.
	public final static int SERVER_LOCAL = 9;
	public final static int SERVER_DEV = 1;
	public final static int SERVER_REAL = 2;

	private final static int SERVER_STAT = HostConfig.MyConfig();		// 1 or 2 or 9
	// private final static int SERVER_STAT = SERVER_REAL;
	private final static String READ_CHARSET = "EUC-KR";
	private final static String MARKET_CODE = "NATURE01"; 			// 마켓 코드 고정
	private final static String POSCODE = "NWEB01"; 				// POST CODE
	
	
	//DB 정보 ID/PW
	private static Map<Integer, String> DB_ID = new HashMap<Integer, String>();
	private static Map<Integer, String> DB_PW = new HashMap<Integer, String>();
	
	//DB 정보 JDBC 환경
	private static Map<Integer, String> JDBC = new HashMap<Integer, String>();
 
	
	// LMS 전송
	public final static String sender_minno = "01094739873";		// 010-9473-9873
	private static Map<Integer, String> SEND_URL = new HashMap<Integer, String>();   //ANDROID_AUTH AUTH KEY
	
	// 2. 전역변수 실체화.
	static 
	{
		// (ex1) LMS 전송
		SEND_URL.put(SERVER_LOCAL, "http://113.217.231.162/newmms/ext_mms_send.php");
		SEND_URL.put(SERVER_DEV, 	"http://113.217.231.162/newmms/ext_mms_send.php");
		SEND_URL.put(SERVER_REAL,   "http://113.217.231.162/newmms/ext_mms_send.php");
		
		
		DB_ID.put(SERVER_LOCAL,				"tshop");
		DB_ID.put(SERVER_DEV,				"tshop");
		DB_ID.put(SERVER_REAL,				"tshop");
		
		
		DB_PW.put(SERVER_LOCAL,				"tshop");
		DB_PW.put(SERVER_DEV,				"tshop");
		DB_PW.put(SERVER_REAL,				"tshop");
		
		
		JDBC.put(SERVER_LOCAL,			"jdbc:oracle:thin:@210.112.117.139:1521:DNRDB");
		JDBC.put(SERVER_DEV,				"jdbc:oracle:thin:@210.112.117.139:1521:DNRDB");
		JDBC.put(SERVER_REAL,			"jdbc:oracle:thin:@210.112.117.139:1521:DNRDB");		
	}

	////////////////////sendLms 관련 게터세터
	public static String getSenderMinno() {
	return sender_minno;
	}
	public static String getSEND_URL() {
	return SEND_URL.get(SERVER_STAT);
	}

	public static String getMarketCode() {
		return MARKET_CODE;
	}

	public static String getPoscode() {
		return POSCODE;
	}

	private static String getStatName() {
		switch (SERVER_STAT) {
		case SERVER_LOCAL:
			return "SERVER_LOCAL";
		case SERVER_DEV:
			return "SERVER_DEV";
		case SERVER_REAL:
			return "SERVER_REAL";
		}
		return "NONE";
	}

	public static int getServer_stat() {
		return SERVER_STAT;
	}

	public static String getReadCharset() {
		return READ_CHARSET;
	}

	public static String getDB_ID() {
		return DB_ID.get(SERVER_STAT);
	}

	public static String getDB_PW() {
		return DB_PW.get(SERVER_STAT);
	}

	public static String getJDBC() {
		return JDBC.get(SERVER_STAT);
	}


}
