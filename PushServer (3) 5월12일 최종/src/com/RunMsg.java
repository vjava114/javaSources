package com;

import java.util.HashMap;

import Data.storage.dao.pushDAO;

import com.Modules.SenderLms;
import com.Modules.SenderLmsImpl;

public class RunMsg {

	
	public void SendMsg(String telno, String title, String msg, String seq){

		//String Minno = telno;
		//String Minno = "01079199600";			// 무조건 내 번호로만 오게하자 일단.
		String Minno = telno;			// 무조건 내 번호로만 오게하자 일단.
		String Title = title;
		String Msg = msg;
		
		
		HashMap<String, String> Data = new HashMap<String, String>();
		Data.put("id", "asd");					// rocomo 회사 키??
		Data.put("callback_url", "");
		Data.put("key", "asd1123");
		
		System.out.println("[sms] title : " + title);
		System.out.println("[sms] msg : " + msg);
		
		
		
		SenderLms sendmsg = new SenderLmsImpl(Minno, Title, Msg, Data);
		String rt_msg= "";
		try {
			rt_msg = (String) sendmsg.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=========> send msg result [" + rt_msg + "]");
		
		
		
		
		
	}
	
}
