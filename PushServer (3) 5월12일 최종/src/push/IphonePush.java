package push;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.feedback.FeedbackServiceManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

import org.json.JSONException;

import Data.storage.dao.pushDAO;

import constants.ConfigProject;

 
public class IphonePush {

	/**
	 * @param args
	 * @throws JSONException
	 */
	public String IphonePushstart(String msg, String push_id, HashMap<String, String> data)	throws JSONException {
		// TODO Auto-generated method stub

		String fushResult = "";
		
		String deviceToken = push_id;
		try {

			/* Build a blank payload to customize */
			PushNotificationPayload payload = PushNotificationPayload.complex();
			/* Customize the payload */

			//System.out.println(payload.getExpiry());
			payload.addAlert(msg);
		//	payload.addCustomAlertBody(msg);
			payload.addSound("default");
			payload.addBadge(1);
			payload.setExpiry(1000);
			
			
			payload.addCustomDictionary("userseq", data.get("userseq"));
			payload.addCustomDictionary("msgseq", data.get("msgseq"));
			
			
			System.out.println(" === 아래의 값을 전송합니다. === ");
			System.out.println("[userseq] : "+data.get("userseq"));
			System.out.println("[msgseq] : "+data.get("msgseq"));
			
			System.out.println(payload.toString());
			
			List<PushedNotification> notifications = Push.payload(
					payload,
					ConfigProject.getIPHONE_AUTH(), 
					ConfigProject.IPHONE_AUTH_PWD,
					ConfigProject.getIPHONE_REAL_DEV(), 
					deviceToken
					);
			
 
		 

			try {
				if (notifications.get(0).isSuccessful()==true)
				{
					HashMap<String, String> updateparam = new HashMap<String, String>();
					updateparam.put("seq", data.get("seq"));
					
					pushDAO dao = new pushDAO();
					dao.updatePushList(updateparam);
					System.out.println("푸시전송에 성공! push_lst 업데이트합니다. : ");
					fushResult ="successc";
				}
				else if (notifications.get(0).isSuccessful() == false)
				{
					String error_msg = notifications.get(0).getException().toString();
					System.out.println("[errorCode : ]" + error_msg);
					
					HashMap<String, String> updateparam = new HashMap<String, String>();
					updateparam.put("error_msg", error_msg);
					updateparam.put("seq", data.get("seq"));
					
					System.out.println("업데이트 할 파람값 : "+updateparam.toString());
					
					pushDAO dao = new pushDAO();
					dao.updatePushList_fail(updateparam);
					System.out.println("푸시전송에 실패... 업데이트합니다. : " + data.toString());
					fushResult = "fail";
				}
			} catch (Exception e) {	e.printStackTrace();}
			
			
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeystoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fushResult;
	}

	
	
	
	public static String printPushedNotifications(List<PushedNotification> notifications) 
	{
		List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
		List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
		
		String notiResult = null;
		
		int failed = failedNotifications.size();
		int successful = successfulNotifications.size();

		if (successful > 0 && failed == 0) {
			printPushedNotifications("All noti success (" + successfulNotifications.size() + "):",	successfulNotifications);
			
		} 
		else if (successful == 0 && failed > 0) 
		{
			//printPushedNotifications("All notifi failed (" + failedNotifications.size() + "):", failedNotifications);
			System.out.println("Send Fail!!");
			
		} 
		else if (successful == 0 && failed == 0) 
		{
			System.out.println("No notifications could be sent, probably because of a critical error");
			notiResult = "";
		} else {
			printPushedNotifications("Some notifications failed ("
					+ failedNotifications.size() + "):", failedNotifications);
			printPushedNotifications("Others succeeded ("
					+ successfulNotifications.size() + "):",
					successfulNotifications);
		}
		return notiResult;
	}

	public static void printPushedNotifications(String description, List<PushedNotification> notifications) {
		System.out.println(description);
		for (PushedNotification notification : notifications) {
			try {
				System.out.println("  " + notification.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/* 
	 * 아래는 멀티푸시 구현한부분 필요없어서 주석처리
	 */
//	public ArrayList<String> IphoneMultiPush (String msg , ArrayList<String> push_id, HashMap<String, String> data,int Threads )
//	{
//		
//		System.out.println("IphoneMultiPush Msg cnt [" + Threads + "]") ; 
//		 ArrayList<String> deviceToken = new ArrayList<String>();
//		 
//		 deviceToken = push_id;
//	       /* Prepare a simple payload to push */ 
//	        PushNotificationPayload payload = PushNotificationPayload.alert(msg);
//	        try {
//	        	payload.addCustomDictionary("userseq", "7");
//	        	payload.addCustomDictionary("msgseq", "15");
//	        	
//			} catch (JSONException e1) {
//				e1.printStackTrace();
//			}
//
//	       /* Decide how many threads you want to create and use */ 
//	    //    int threads = 30;
//	        	
//	        int threads = Threads;
//	       /* Start threads, wait for them, and get a list of all pushed notifications */
// 
//	        try {
//			List<PushedNotification> notifications = Push.payload(payload, ConfigProject.getIPHONE_AUTH(),
//					   ConfigProject.IPHONE_AUTH_PWD, ConfigProject.getIPHONE_REAL_DEV(), threads, deviceToken);
//			
//			
//			printPushedNotifications(notifications);
//			} catch (CommunicationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (KeystoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//
//		return deviceToken;
//	}

}
