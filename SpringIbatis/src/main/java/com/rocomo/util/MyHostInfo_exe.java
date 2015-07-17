package com.rocomo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyHostInfo_exe {
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
	
	
	
	
	/*  /////////////////////////////////////////////////////
	 * 		자신의 아이피주소와 도메인이름을 TEST 해 봅시다.
	 */ /////////////////////////////////////////////////////
		public static void main (String[] args){
			MyHostInfo_exe ip = new MyHostInfo_exe();
			ConfigProject conf = new ConfigProject();
			System.out.println("=========================== [ MyHostInfo ] ===========================");
				System.out.println("MyIp			:		" + ip.getMyIPAddress());
				System.out.println("MyDomain		:		" + ip.getMyDomainName());
				System.out.println("real(2)? or dev(9)?	:		" + conf.getServer_stat());
			System.out.println("======================================================================");
	 
		}
}
