 package constants;
 
 

 
public class HostConfig {
	
	//�ڵ�ȭ ó���� ���� ���� SVN���� ���߰� ��� ���ÿ� ó�� �ϱ� ����  (Ȥ�� ���� �߸� �ö� ���� �� �ڵ������� ��ȯó�� )
	public static int MyConfig(){
 
		String Domain = "";
		int myint = 0;

		Domain  = MyHostInfo.getMyDomainName();
		
		if(	Domain.equals("OCMAPP01") ||   Domain.equals("OCMAPP02")  ||
			Domain.equals("OCMAPP03") ||   Domain.equals("OCMAPP04")  || 
			Domain.equals("OCMWEB01") ||   Domain.equals("OCMWEB02")  
			) {
			myint = 3;
		}
		else if (Domain.equals("ROCOKIM2-PC")  ||  Domain.equals("ROCOKIM-PC") ) {
			myint = 9;
		} 
		else if (Domain.equals("mail.rocomo.co.kr")) {
			myint = 1;
		}
		else {
			myint = 2;
		}
 
		System.out.println("=============> MY DOMAIN [" + Domain + "] CODE[" + myint  + "]");
		return myint;
	}

}
