package com;

import java.util.HashMap;

import com.Modules.SenderLms;
import com.Modules.SenderLmsImpl;

public class RunMsg {
	public static void main(String[] args) {
		String Minno = "01094739873";
		String Title = "LMS TEST";
		String Msg = "Hello!";
		
		HashMap<String, String> Data = new HashMap<String, String>();
		Data.put("id", "asd");
		Data.put("callback_url", "");
		Data.put("key", "asd1123");
		
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
