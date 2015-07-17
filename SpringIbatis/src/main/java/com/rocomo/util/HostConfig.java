package com.rocomo.util;

public class HostConfig {
	
	//자동화 처리를 위해 생성 SVN으로 개발과 상용 동시에 처리 하기 위함  (잘못 올라 갔을 때 자동적으로 변환처리 )
	public static int MyConfig(){
 
		String Domain = "";
		int myint = 0;

		Domain  = MyHostInfo_exe.getMyDomainName();
		
		if(	Domain.equals("OCMAPP01") ||   Domain.equals("OCMAPP02")  ) 
		{
			myint = 3;	//
		}
		else if (Domain.equals("ROCOKIM2-PC")  ||  Domain.equals("ROCOKIM-PC") || Domain.equals("kang-PC")) 
		{
			myint = 9;	// 로컬
		} 
		else if (Domain.equals("mail.rocomo.co.kr")) 
		{
			myint = 1;	// 개발
		}
		else 
		{
			myint = 2;	// 리얼
		}
 
		
		return myint;
	}
	
	

}