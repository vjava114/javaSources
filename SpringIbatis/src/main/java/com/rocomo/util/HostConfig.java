package com.rocomo.util;

public class HostConfig {
	
	//�ڵ�ȭ ó���� ���� ���� SVN���� ���߰� ��� ���ÿ� ó�� �ϱ� ����  (�߸� �ö� ���� �� �ڵ������� ��ȯó�� )
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
			myint = 9;	// ����
		} 
		else if (Domain.equals("mail.rocomo.co.kr")) 
		{
			myint = 1;	// ����
		}
		else 
		{
			myint = 2;	// ����
		}
 
		
		return myint;
	}
	
	

}