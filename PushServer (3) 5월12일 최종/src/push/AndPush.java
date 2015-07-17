package push;

import java.util.HashMap;

import Data.storage.dao.pushDAO;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Result;

public class AndPush {
	public String senderExcute (String regId,  String msg, HashMap<String, String> map) throws Exception { 
		// TODO Auto-generated method stub
		
		String prtn = "";
		
		String seq = map.get("seq");
		String push_type = map.get("push_type");
		String push_url = map.get("push_url");
	//	String push_number = map.get("push_number");
		
		
		
		
		System.out.println("�ȵ�Ǫ�� ���� :" + map.toString());

		
		GCM push_sender = new GCM();
		Result result = push_sender.sender(regId,  msg, map);			// Ǫ�� �Ϸ�.
		
		
		// ���� ���� �ߴٸ�, push_list ������ ������Ʈ ���ش�.
		
		
		
		String getmsgid =result.getMessageId();					// �������鼭 ���⼭ ���� �ȴ�.
		
		String multicast_id = result.getMessageId(); 		
		String message_id = result.getMessageId();			
		String error_msg = result.getErrorCodeName();		
		
		pushDAO dao = new pushDAO();
		//updateParam.put("push_number", push_number);
		
		if(getmsgid == null)
		{
			System.out.print(" [����] Ǫ�����޿� ������ ������Ʈ");
			HashMap<String, String> updateParam = new HashMap<String, String>();
			updateParam.put("message_id", message_id);
			updateParam.put("error_msg", error_msg);
			updateParam.put("seq", seq);
			
			dao.updatePushList_fail(updateParam);
		}
		else
		{
			System.out.print(" [����] Ǫ�����޿� ������ ������Ʈ");
			HashMap<String, String> updateParam = new HashMap<String, String>();
			updateParam.put("message_id", message_id);
			updateParam.put("error_msg", error_msg);
			updateParam.put("seq", seq);
			
			
//			updateParam.put("push_type", push_type);
//			updateParam.put("push_url", push_url);
			
				dao.updatePushList(updateParam);
		}
		
		
		
		
		
		if (result.getMessageId() != null){
			String canonicalRegId = result.getCanonicalRegistrationId();
			
			if(canonicalRegId != null)
			{
				// same device has more than on registration ID: update database
				System.out.println(" ���� currentID�� ���ġ ���ּ���");
			}
		}
		else
		{
			String error = result.getErrorCodeName();
			if ( error.equals(Constants.ERROR_NOT_REGISTERED) )
			{
				// application has been removed from device - unregister database
				System.out.println("����ڰ� app�� �����Ͽ����ϴ�. : " + error);
			}
		}
	
		System.out.println("");
		return prtn;
 
	}
}
