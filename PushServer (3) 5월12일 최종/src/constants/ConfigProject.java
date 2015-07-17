 package constants;

import java.util.HashMap;
import java.util.Map;

public class ConfigProject {
	
	
	public final static int SERVER_LOCAL = 9; // ������PC
	public final static int SERVER_DEV = 1; // ����
	public final static int SERVER_REAL = 2; // �

	// ���߼��� ��� ���� SETTING 
	//2013-04-15 �ڵ�ȭ ó�� 
	private final static int SERVER_STAT = HostConfig.MyConfig();
//	private final static int SERVER_STAT = SERVER_REAL;
	 
	//���� PUSH ��� ���� ����
 
	private static Map<Integer, String> ANDROID_AUTH = new HashMap<Integer, String>();   //ANDROID_AUTH AUTH KEY
	private static Map<Integer, String> ANDROID_APIID = new HashMap<Integer, String>();   //ANDROID_AUTH AUTH KEY
	
	//���� ������Ʈ URL 
	public final static String IPHONE_UPDATE_URL    = "http://itunes.apple.com/kr/app/dooo-saeng-gaggwanli/id423785695?mt=8";
	
	//���� PUSH ��� ���� ����
	public final static String IPHONE_AUTH_PWD = "rcm123";
	private static Map<Integer, String> IPHONE_APP_CODE = new HashMap<Integer, String>();     // IPHONE PUSH AUTH KEY
	private static Map<Integer, String> IPHONE_REAL_DEV = new HashMap<Integer, String>();     // IPHONE PUSH AUTH KEY
	private static Map<Integer, String> IPHONE_AUTH = new HashMap<Integer, String>();     // IPHONE PUSH AUTH KEY
	
	
	// LMS ����
	public final static String sender_minno = "01094739873";		// 010-9473-9873
	private static Map<Integer, String> SEND_URL = new HashMap<Integer, String>();   //ANDROID_AUTH AUTH KEY

	
 
	static {
		// LMS ����
		SEND_URL.put(SERVER_LOCAL, "http://113.217.231.162/newmms/ext_mms_send.php");
		SEND_URL.put(SERVER_DEV, 	"http://113.217.231.162/newmms/ext_mms_send.php");
		SEND_URL.put(SERVER_REAL,   "http://113.217.231.162/newmms/ext_mms_send.php");
 
		
 
		// 
		ANDROID_APIID.put(SERVER_LOCAL, "AIzaSyCeYcS0cRe0OOtyY--KnRKloGGVnYaSKyc");		
		ANDROID_APIID.put(SERVER_DEV, 	"AIzaSyCeYcS0cRe0OOtyY--KnRKloGGVnYaSKyc");
		ANDROID_APIID.put(SERVER_REAL,   "AIzaSyCeYcS0cRe0OOtyY--KnRKloGGVnYaSKyc");
		
		
//		/www/web/rocomo/leebanjang/leebj/resources/IPhoneAuth/smartcoach_apns_dev.p12
//		IPHONE_AUTH.put(SERVER_LOCAL, "C:/temp/smartcoach_apns_prod.p12");				// �۵���.
		
		IPHONE_AUTH.put(SERVER_LOCAL, "C:/temp/smartcoach_apns_prod.p12");
		
		//IPHONE_AUTH.put(SERVER_DEV, "/www/web/rocomo/leebanjang/leebj/resources/IPhoneAuth/smartcoach_apns_prod.p12");
		IPHONE_AUTH.put(SERVER_DEV, "/www/pns/push_server/cert/smartcoach_apns_prod.p12");
		
		//IPHONE_AUTH.put(SERVER_REAL, "/www/web/rocomo/leebanjang/leebj/resources/IPhoneAuth/smartcoach_apns_prod.p12");
		IPHONE_AUTH.put(SERVER_REAL, "/www/pns/push_server/cert/smartcoach_apns_prod.p12");
//		IPHONE_AUTH.put(SERVER_REAL, "D:/PushDev/PushServer (3)/src/lib/auth/smartcoach_apns_prod.p12");
		
		IPHONE_APP_CODE.put(SERVER_LOCAL,"com.kmn.smartcoach");
		IPHONE_APP_CODE.put(SERVER_DEV,   "com.kmn.smartcoach");
		IPHONE_APP_CODE.put(SERVER_REAL,  "com.kmn.smartcoach");
		
		
		IPHONE_REAL_DEV.put(SERVER_LOCAL, "false");
		IPHONE_REAL_DEV.put(SERVER_DEV, "false");
		IPHONE_REAL_DEV.put(SERVER_REAL, "true");		// ������ true������, ���� ���߻�Ȳ�� false�� �ٲ����
		
	}
 
 
	public static String getANDROID_APIID() {
		return ANDROID_APIID.get(SERVER_STAT);
	}

	public static String getANDROID_AUTH() {
		return ANDROID_AUTH.get(SERVER_STAT);
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
	
	//////////////////// ������ START


	public static String getSenderMinno() {
		return sender_minno;
	}

	//////////////////// ������ END
 
	public static String getSEND_URL() {
		return SEND_URL.get(SERVER_STAT);
	}

	
	public static int getServer_stat(){
		return SERVER_STAT;
	}

 
	public static String getIPHONE_APP_CODE() {
		return IPHONE_APP_CODE.get(SERVER_STAT);
	}

	public static boolean  getIPHONE_REAL_DEV() {
		if( IPHONE_REAL_DEV.get(SERVER_STAT) == "true")
			return true;
		else 
			return false;
	}
	public static String getIPHONE_AUTH() {
		return IPHONE_AUTH.get(SERVER_STAT);
	}

	public static String getIphoneAuthPwd() {
		return IPHONE_AUTH_PWD;
	}
	
 
	
}
