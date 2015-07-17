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
		
		ArrayList<PushItemBean> dataA;				// �ȵ���̵�
		ArrayList<PushItemBean>  dataI;				// ������
	 
		
		
		/*  ������, �ȵ���̵��� FULL ������ ��������		 */
		PushImpl  getPushData = new PushImpl();
		
		try {	getPushData.run();	} 
		catch (Exception e) {	e.printStackTrace();}
 
		dataA =  getPushData.getAndroid();					
		dataI  = getPushData.getIphone();
		
		System.out.println("�ȵ���̵� ������ ���� : "+dataA.size());
		System.out.println("������ ������ ���� : "+dataI.size());
		/*  			-------	END	-------					 */
	
		
		
		
		///////////////////////////////////////////// �ȵ���̵� for ���� /////////////////////////////////////////////
		for(int i = 0;i < dataA.size();i ++ ){
			
			pushDAO dao = new pushDAO();
			
			/* ����� ���� 1 */
			dao.statusUpdate(Integer.parseInt(dataA.get(i).getMsg_seq()));
			
			
			System.out.println("���� ���� : " + dataA.get(i).getStatus());
			
			 System.out.println("======>  [ Msg_seq  : " +dataA.get(i).getMsg_seq() + "]");
			 System.out.println("======>  [ Ÿ��Ʋ   : " +dataA.get(i).getTitle()+ "]");
			 System.out.println("======>  [ �޽���   : " +dataA.get(i).getMsg() + "]");
			 System.out.println("======>  [ Memb_seq : " +dataA.get(i).getMember_seq() + "]");
			 System.out.println("======>  [ ������   : " +dataA.get(i).getPush_id()  + "]");
			 System.out.println("======>  [ seq   : " +dataA.get(i).getSeq() + "]");
			 System.out.println("======>  [ push_type   : " +dataA.get(i).getPush_type() + "]");
			 System.out.println("======>  [ push_url   : " +dataA.get(i).getPush_url() + "]");
			 System.out.println("======>  [ push_number   : " +dataA.get(i).getPush_number() + "]");
			 System.out.println("======>  [ phone   : " +dataA.get(i).getPhone() + "]");
		
			 String strgetMl_retry = dataA.get(i).getMl_retry();		 		if ( strgetMl_retry == null || strgetMl_retry == "null" || strgetMl_retry=="" ){ strgetMl_retry = "0"; }
			 String strgetPl_retry = dataA.get(i).getPl_retry();		 		if ( strgetPl_retry == null || strgetPl_retry == "null" ||  strgetPl_retry=="" ){ strgetPl_retry = "0"; }
			 
			 
			
			 if((Integer.parseInt(strgetMl_retry) > Integer.parseInt(strgetPl_retry)) || Integer.parseInt(strgetMl_retry) == 0 ){
				 
				 // Ǫ��
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
						dataA.get(i).getPush_id(),		// [�ʼ�] ����̽� ��ū  
						dataA.get(i).getMsg(),			// [�ʼ�] �޼���
						map
						);
			 
			 }
			 
			else {
				System.out.println("replaceYN : "+ dataA.get(i).getReplace_yn()); 
				if (dataA.get(i).getReplace_yn().equals("Y")) {				// ��ü���� ��� Y
		
					// LMS ����
					RunMsg runmsg = new RunMsg();
					runmsg.SendMsg(dataA.get(i).getPhone(), 
							dataA.get(i).getLms_title(), // ����
							dataA.get(i).getLms_msg(), // ����
							dataA.get(i).getSeq());
					dao.updatePushList_LMS(Integer.parseInt(dataA.get(i).getSeq()));
					System.out.println(dataA.get(i).getPhone() + " ���� lms ������ �Ϸ��Ͽ����ϴ�.");
				}else
				{
					dao.no_updatePushList_LMS(Integer.parseInt(dataA.get(i).getSeq()));
				}
			}
		 
			 /* ����� ���� 2 */
		 	dao.statusUpdate2(Integer.parseInt(dataA.get(i).getMsg_seq()));
		}
	
		
		
		
		
		
		///////////////////////////////////////////// ������ for ���� /////////////////////////////////////////////
		for(int i = 0;i < dataI.size();i ++ ){			
			pushDAO dao = new pushDAO();
			
			/* ����� ���� 1 */
			dao.statusUpdate(Integer.parseInt(dataI.get(i).getMsg_seq()));
			
			System.out.println("���� ���� : " + dataI.get(i).getStatus());
			
			System.out.println("======>  [Ÿ�� : " +dataI.get(i).getType() + "]");
			System.out.println("======>  [getMember_seq : " +dataI.get(i).getMember_seq() + "]");
			System.out.println("======>  [getMsg : " +dataI.get(i).getMsg() + "]");
			System.out.println("======>  [getMsg_seq : " +dataI.get(i).getMsg_seq() + "]");
			System.out.println("======>  [getPush_id : " +dataI.get(i).getPush_id()  + "]");
			System.out.println("======>  [getTitle : " +dataI.get(i).getTitle()+ "]");
			System.out.println("======>  [seq : " +dataI.get(i).getSeq()+ "]");
			
			String strgetMl_retry = dataI.get(i).getMl_retry();		 		if ( strgetMl_retry == null || strgetMl_retry == "null" || strgetMl_retry=="" ){ strgetMl_retry = "0"; }
			String strgetPl_retry = dataI.get(i).getPl_retry();		 		if ( strgetPl_retry == null || strgetPl_retry == "null" ||  strgetPl_retry=="" ){ strgetPl_retry = "0"; }
			 
			
			if((Integer.parseInt(strgetMl_retry) > Integer.parseInt(strgetPl_retry)) || Integer.parseInt(strgetMl_retry) == 0 ){
				
				//Ǫ������
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
				if(dataI.get(i).getReplace_yn().equals("Y") ){						// ��ü���� ��� Y
					
					// lms ����
					RunMsg runmsg = new RunMsg();
					runmsg.SendMsg(
							dataI.get(i).getPhone(), 
							dataI.get(i).getLms_title(),		// lms�� ���� 
							dataI.get(i).getLms_msg(), 			// lms�� ����
							dataI.get(i).getSeq());
					
					dao.updatePushList_LMS(Integer.parseInt(dataI.get(i).getSeq()));
					System.out.println(dataI.get(i).getPhone() + " ���� lms ������ �Ϸ��Ͽ����ϴ�.");
				}else
				{
					dao.no_updatePushList_LMS(Integer.parseInt(dataI.get(i).getSeq()));
				}
			}
			
			/* ����� ���� 2 */
			dao.statusUpdate2(Integer.parseInt(dataI.get(i).getMsg_seq()));
		
			}		
			
	
			System.out.println(" =============== Push End!! ===============");
				
				
		}
	
}








