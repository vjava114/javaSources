 package constants;

import java.util.HashMap;
import java.util.Map;

public class ConfigProject {
	
	
	public final static int SERVER_LOCAL = 9; // ������PC
	public final static int SERVER_DEV = 1;       // ����
	public final static int SERVER_REAL = 2; // �
 
	private final static int SERVER_STAT = HostConfig.MyConfig();
 
	//////////////////// ������ START
	public final static String sender_minno = "01094739873";
	private static Map<Integer, String> SEND_URL = new HashMap<Integer, String>();   //ANDROID_AUTH AUTH KEY

	
 
	static {
 
 
		SEND_URL.put(SERVER_LOCAL, "http://113.217.231.162/newmms/ext_mms_send.php");
		SEND_URL.put(SERVER_DEV, 	"http://113.217.231.162/newmms/ext_mms_send.php");
		SEND_URL.put(SERVER_REAL,   "http://113.217.231.162/newmms/ext_mms_send.php");
 
	 
	}
	//////////////////// ������ END
 
	public static String getSEND_URL() {
		return SEND_URL.get(SERVER_STAT);
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
	public static int getServer_stat(){
		return SERVER_STAT;
	}


	public static String getSenderMinno() {
		return sender_minno;
	}

	//////////////////// ������ END
 
}
