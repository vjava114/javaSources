package com.Modules;

import java.util.HashMap;

import constants.ConfigProject;

public class SenderLms implements Sender {
	protected String sminno;
	protected String minno;
	protected String title;
	protected String msg;
	protected HashMap<String, String> data;
	
	public SenderLms (String Minno, String Title, String Msg, HashMap<String, String> Data){
		this.minno = Minno;
		this.title  = Title;
		this.msg = Msg;
		this.data = Data;
		this.sminno = ConfigProject.getSenderMinno();
 
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
		return null;
	}
	
}
