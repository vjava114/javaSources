package com.Modules;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import org.bouncycastle.util.encoders.Base64Encoder;

import constants.ConfigProject;

public class SenderLmsImpl extends SenderLms {

	public SenderLmsImpl(String Minno, String Title, String Msg, HashMap<String, String> Data) {
		super(Minno, Title, Msg, Data);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object input(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object output() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	public Object run() throws Exception {
		
		
		// TODO Auto-generated method stub
		String id = "";
		String key = "";
		String callback_url;
		String rt_msg = "";
		
		id = data.get("id");
		key = data.get("key");
		callback_url = data.get("callback_url");
		
      	String send_msg = "id=" + id + "&sender=" + sminno + "&phone=" + minno + "&title=" +  URLEncoder.encode(title,"UTF-8") + "&msg=" + URLEncoder.encode(msg,"UTF-8") + "&key=" + key + "&url=" + callback_url;
		String url =   ConfigProject.getSEND_URL();
   
        String send_url = url + "?" +  send_msg;
 
        System.out.println("SEND URL[" + send_url + "]");
        GetHttpUrl doGetproc = new GetHttpUrl(send_url);
 
        rt_msg = doGetproc.RunHttp_v2();
           
     
        return rt_msg;
 
	}

}
