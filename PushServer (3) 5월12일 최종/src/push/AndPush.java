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
		
		
		
		
		System.out.println("안드푸시 진입 :" + map.toString());

		
		GCM push_sender = new GCM();
		Result result = push_sender.sender(regId,  msg, map);			// 푸시 완료.
		
		
		// 전달 성공 했다면, push_list 정보를 업데이트 해준다.
		
		
		
		String getmsgid =result.getMessageId();					// 에러나면서 여기서 널이 된다.
		
		String multicast_id = result.getMessageId(); 		
		String message_id = result.getMessageId();			
		String error_msg = result.getErrorCodeName();		
		
		pushDAO dao = new pushDAO();
		//updateParam.put("push_number", push_number);
		
		if(getmsgid == null)
		{
			System.out.print(" [실패] 푸시전달에 실패한 업데이트");
			HashMap<String, String> updateParam = new HashMap<String, String>();
			updateParam.put("message_id", message_id);
			updateParam.put("error_msg", error_msg);
			updateParam.put("seq", seq);
			
			dao.updatePushList_fail(updateParam);
		}
		else
		{
			System.out.print(" [성공] 푸시전달에 성공한 업데이트");
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
				System.out.println(" 현재 currentID를 재배치 해주세요");
			}
		}
		else
		{
			String error = result.getErrorCodeName();
			if ( error.equals(Constants.ERROR_NOT_REGISTERED) )
			{
				// application has been removed from device - unregister database
				System.out.println("사용자가 app을 삭제하였습니다. : " + error);
			}
		}
	
		System.out.println("");
		return prtn;
 
	}
}
