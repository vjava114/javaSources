package com.wallet.membership.common;

import java.util.ArrayList;
import java.util.List;

public class PostcdGetter {
	public static List<Postcd> getPostcd(String Address){
		List<Postcd> list = new ArrayList<Postcd>();
		
		String Url = "http://biz.epost.go.kr/KpostPortal/openapied";
		//-- TEST-BED 에서는 ip로만 사용이 가능한 상황임... 테스트 했으나, ip로는 서비를 이용할 수 없음.
		//String Url = "http://203.254.120.177/KpostPortal/openapied"; 
		String Param = "regkey=6e1393c0465f479b91347058792557&target=post&query="+Address;
		NetManager netManager = new NetManager(Url,Param,"EUC-KR");
		netManager.getPOSTConnention();
		netManager.getXml();
		
		int Cnt = netManager.getXmlCnt("postcd");
		
		System.out.println("####################"); //##
		System.out.println("PostcdGetter START"); //##
		System.out.println("--------------------"); //##
		System.out.println("URL : " + Url); //##
		System.out.println("Param : " + Param); //##
		System.out.println("Cnt : " + Cnt); //##
		System.out.println("####################"); //##
		
		for(int i=0;i<Cnt;i++){
			String address = netManager.getXmlString("address", i);
			String postcd = netManager.getXmlString("postcd", i);
			if(!address.equals("")){
				address = address.replace("'", "");
			}
			
			//System.out.println("\n@@@@ address [" + i + "] : " + address); //##
			list.add(new Postcd(address, postcd));
		}		
		return list;
	}
}
