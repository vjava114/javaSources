package Data.Service;
 
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import push.AndPush;
import push.IphonePush;
import Data.bean.PushItemBean;
import Data.storage.dao.pushDAO;

import com.RunMsg;

 

public class SendServer{
	
	public static void main(String[] args) throws Exception {
		
		ArrayList<PushItemBean> dataA;				// 안드로이드
		ArrayList<PushItemBean>  dataI;				// 아이폰
	 
		
		
		/*  아이폰, 안드로이드의 FULL 데이터 가져오기		 */
		PushImpl  getPushData = new PushImpl();
		
		try {	getPushData.run();	} 
		catch (Exception e) {	e.printStackTrace();}
 
		dataA =  getPushData.getAndroid();					
		dataI  = getPushData.getIphone();
		
		System.out.println("안드로이드 전송할 갯수 : "+dataA.size());
		System.out.println("아이폰 전송할 갯수 : "+dataI.size());
		/*  			-------	END	-------					 */
	
		
		
		
		///////////////////////////////////////////// 안드로이드 for 시작 /////////////////////////////////////////////
		for(int i = 0;i < dataA.size();i ++ ){
			
			pushDAO dao = new pushDAO();
			
			/* 팀장님 쿼리 1 */
			dao.statusUpdate(Integer.parseInt(dataA.get(i).getMsg_seq()));
			
			
			System.out.println("루프 진입 : " + dataA.get(i).getStatus());
			
			 System.out.println("======>  [ Msg_seq  : " +dataA.get(i).getMsg_seq() + "]");
			 System.out.println("======>  [ 타이틀   : " +dataA.get(i).getTitle()+ "]");
			 System.out.println("======>  [ 메시지   : " +dataA.get(i).getMsg() + "]");
			 System.out.println("======>  [ Memb_seq : " +dataA.get(i).getMember_seq() + "]");
			 System.out.println("======>  [ 수신자   : " +dataA.get(i).getPush_id()  + "]");
			 System.out.println("======>  [ seq   : " +dataA.get(i).getSeq() + "]");
			 System.out.println("======>  [ push_type   : " +dataA.get(i).getPush_type() + "]");
			 System.out.println("======>  [ push_url   : " +dataA.get(i).getPush_url() + "]");
			 System.out.println("======>  [ push_number   : " +dataA.get(i).getPush_number() + "]");
			 System.out.println("======>  [ phone   : " +dataA.get(i).getPhone() + "]");
		
			 String strgetMl_retry = dataA.get(i).getMl_retry();		 		if ( strgetMl_retry == null || strgetMl_retry == "null" || strgetMl_retry=="" ){ strgetMl_retry = "0"; }
			 String strgetPl_retry = dataA.get(i).getPl_retry();		 		if ( strgetPl_retry == null || strgetPl_retry == "null" ||  strgetPl_retry=="" ){ strgetPl_retry = "0"; }
			 
			 
			
			 if((Integer.parseInt(strgetMl_retry) > Integer.parseInt(strgetPl_retry)) || Integer.parseInt(strgetMl_retry) == 0 ){
				 
				 // 푸쉬
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("title", dataA.get(i).getTitle());
				map.put("userseq", dataA.get(i).getMember_seq());
				map.put("msgseq", dataA.get(i).getMsg_seq());
				map.put("seq", dataA.get(i).getSeq());
		
				map.put("push_type", dataA.get(i).getPush_type());
				if( dataA.get(i).getPush_type().equals("P")       ||  dataA.get(i).getPush_type().equals("T")  )
				{
					 map.put("push_url", dataA.get(i).getPush_number());
				}
				else if (dataA.get(i).getPush_type().equals("M"))
				{
					 map.put("push_url", dataA.get(i).getPush_url());
				}
				else
				{
					map.put("push_url", "");
				}
				 
				 
				System.out.println("======>  [data : " +map+ "]");
				 
				AndPush AP  = new AndPush();
		
				AP.senderExcute(  
						dataA.get(i).getPush_id(),		// [필수] 디바이스 토큰  
						dataA.get(i).getMsg(),			// [필수] 메세지
						map
						);
			 
			 }
			 
			else {
				System.out.println("replaceYN : "+ dataA.get(i).getReplace_yn()); 
				if (dataA.get(i).getReplace_yn().equals("Y")) {				// 대체전송 사용 Y
		
					// LMS 전송
					RunMsg runmsg = new RunMsg();
					runmsg.SendMsg(dataA.get(i).getPhone(), 
							dataA.get(i).getLms_title(), // 제목
							dataA.get(i).getLms_msg(), // 내용
							dataA.get(i).getSeq());
					dao.updatePushList_LMS(Integer.parseInt(dataA.get(i).getSeq()));
					System.out.println(dataA.get(i).getPhone() + " 으로 lms 전송을 완료하였습니다.");
				}else
				{
					dao.no_updatePushList_LMS(Integer.parseInt(dataA.get(i).getSeq()));
				}
			}
		 
			 /* 팀장님 쿼리 2 */
		 	dao.statusUpdate2(Integer.parseInt(dataA.get(i).getMsg_seq()));
		}
	
		
		
		
		
		
		///////////////////////////////////////////// 아이폰 for 시작 /////////////////////////////////////////////
		for(int i = 0;i < dataI.size();i ++ ){			
			pushDAO dao = new pushDAO();
			
			/* 팀장님 쿼리 1 */
			dao.statusUpdate(Integer.parseInt(dataI.get(i).getMsg_seq()));
			
			System.out.println("루프 진입 : " + dataI.get(i).getStatus());
			
			System.out.println("======>  [타입 : " +dataI.get(i).getType() + "]");
			System.out.println("======>  [getMember_seq : " +dataI.get(i).getMember_seq() + "]");
			System.out.println("======>  [getMsg : " +dataI.get(i).getMsg() + "]");
			System.out.println("======>  [getMsg_seq : " +dataI.get(i).getMsg_seq() + "]");
			System.out.println("======>  [getPush_id : " +dataI.get(i).getPush_id()  + "]");
			System.out.println("======>  [getTitle : " +dataI.get(i).getTitle()+ "]");
			System.out.println("======>  [seq : " +dataI.get(i).getSeq()+ "]");
			
			String strgetMl_retry = dataI.get(i).getMl_retry();		 		if ( strgetMl_retry == null || strgetMl_retry == "null" || strgetMl_retry=="" ){ strgetMl_retry = "0"; }
			String strgetPl_retry = dataI.get(i).getPl_retry();		 		if ( strgetPl_retry == null || strgetPl_retry == "null" ||  strgetPl_retry=="" ){ strgetPl_retry = "0"; }
			 
			
			if((Integer.parseInt(strgetMl_retry) > Integer.parseInt(strgetPl_retry)) || Integer.parseInt(strgetMl_retry) == 0 ){
				
				//푸쉬전송
				HashMap<String,String> data = new HashMap<String, String>();		
				 
				data.put("title", dataI.get(i).getTitle());
				data.put("userseq", dataI.get(i).getMember_seq());
				data.put("msgseq", dataI.get(i).getMsg_seq());
				data.put("seq", dataI.get(i).getSeq());
				data.put("push_type", dataI.get(i).getPush_type());
				if( dataI.get(i).getPush_type().equals("P")  ||  dataI.get(i).getPush_type().equals("T") )
				{
					data.put("push_url", dataI.get(i).getPush_number());
				} 
				else if(dataI.get(i).getPush_type().equals("P")) 
				{
					data.put("push_url", dataI.get(i).getPush_url());
				}
				else
				{
					data.put("push_url", "");
				}
				 
				System.out.println("======>  [data : " +data+ "]");
				IphonePush IP = new IphonePush();
				try {
					IP.IphonePushstart(
							dataI.get(i).getMsg(),
							dataI.get(i).getPush_id(),
							data
							);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();				
				}
			}
			else
			{
				System.out.println("getReplace_yn : " + dataI.get(i).getReplace_yn());
				if(dataI.get(i).getReplace_yn().equals("Y") ){						// 대체전송 사용 Y
					
					// lms 전송
					RunMsg runmsg = new RunMsg();
					runmsg.SendMsg(
							dataI.get(i).getPhone(), 
							dataI.get(i).getLms_title(),		// lms의 제목 
							dataI.get(i).getLms_msg(), 			// lms의 내용
							dataI.get(i).getSeq());
					
					dao.updatePushList_LMS(Integer.parseInt(dataI.get(i).getSeq()));
					System.out.println(dataI.get(i).getPhone() + " 으로 lms 전송을 완료하였습니다.");
				}else
				{
					dao.no_updatePushList_LMS(Integer.parseInt(dataI.get(i).getSeq()));
				}
			}
			
			/* 팀장님 쿼리 2 */
			dao.statusUpdate2(Integer.parseInt(dataI.get(i).getMsg_seq()));
		
			}		
			
	
			System.out.println(" =============== Push End!! ===============");
				
				
		}
	
}








