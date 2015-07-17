 package constants;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyHostInfo {
	static InetAddress inet;

	 
	public String MyIP() throws UnknownHostException{
		InetAddress myAddress = InetAddress.getLocalHost();
		return myAddress +"";
	}
	
	public static String getMyIPAddress (){
	   String MyIP ="";

		try{
			inet = InetAddress.getLocalHost();
			MyIP =  inet.getHostAddress();
			return MyIP;
		}
		catch (UnknownHostException e) {
			// TODO: handle exception
			return "UnknownHostException 에러발생";
		}
	}
	public static String getMyDomainName (){
		   String MyName = "";
		try{
			inet = InetAddress.getLocalHost();
			MyName =  inet.getHostName();
			return MyName;
		}
		catch (UnknownHostException e) {
			// TODO: handle exception
			return "UnknownHostException 에러발생";
		}
	}
	
	// TEST 	
		public static void main (String[] args){
			MyHostInfo ip = new MyHostInfo();
	 
				System.out.print("=========>" +  ip.getMyIPAddress() + " ===>"  +  ip.getMyDomainName()  + "");
	 
		}
}
