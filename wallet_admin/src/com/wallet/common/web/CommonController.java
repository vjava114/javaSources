/*
 * Filename	: CommonController.java
 * Class		: com.wallet.common.web.CommonController
 * History	: 2012/07/18, litlhope, develop
 *
 * Comment	: ��� Controller�� ���� Controller �Դϴ�.
 * 					  Ư���� ������ ���� �� ��� ��Ʈ�ѷ��� �� ��Ʈ�ѷ��� 
 * 					  ����ؾ� �մϴ�.
 */

package com.wallet.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rocomo.servlet.RocomoController;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;

/**
 * Controller�� ���� ��Ʈ�ѷ�.
 * 
 * @since   JDK 1.6.0
 * @version 1.0
 * @author  Copyright (c) 2012 by ROCOMO.INC. All Rights Reserved.
 */
public class CommonController extends RocomoController {
	
	HttpSession session = null;
	
 
	/**
	 * �Է¹��� ���ڿ��� ���� ��ȿ�� �� ����üũ�� �ǽ��մϴ�.
	 * 
	 * @param checkValue	üũ �� ���ڿ�
	 * @param defaultValue	üũ ���н� ��ȯ �� �⺻ ���ڿ�
	 * @return	��ȿ�� �� ����üũ�� �Ϸ�� ���ڿ��� ��ȯ �Ѵ�.
	 */
	protected String checkStr(String checkValue, String defaultValue) {
		String result = "";

	   if(checkValue != null && checkValue.length() > 0) {

	  	//XSS
	  	checkValue = checkValue.replaceAll("\\^", "");
	  	checkValue = checkValue.replaceAll("\\{", "");
  	  checkValue = checkValue.replaceAll("\\}", "");
  	  checkValue = checkValue.replaceAll("\\(", "");
  	  checkValue = checkValue.replaceAll("\\)", "");
  	  checkValue = checkValue.replaceAll("\\[", "");
  	  checkValue = checkValue.replaceAll("\\]", "");
  	  checkValue = checkValue.replaceAll("\\<", "");
  	  checkValue = checkValue.replaceAll("\\>", "");
  	  checkValue = checkValue.replaceAll("\"", "");
			checkValue = checkValue.replaceAll("\'", "");
			
	  	result = checkValue.trim() ;
	  	
	   } else {
	  	 result = defaultValue ;
	   }
	   
		return result;
	}
	
	/**
	 * �Է¹��� ���ڿ��� ���� ��ȿ�� �� ����üũ�� �ǽ��մϴ�.
	 * 
	 * @param checkValue	üũ �� ���ڿ�
	 * @param defaultValue	üũ ���н� ��ȯ �� �⺻ ���ڿ�
	 * @return	��ȿ�� �� ����üũ�� �Ϸ�� ���ڿ��� ��ȯ �Ѵ�.
	 */
	protected String checkStr2(String checkValue, String defaultValue) {
		String result = "";

	   if(checkValue != null && checkValue.length() > 0) {
			
	  	result = checkValue.trim() ;
	  	
	   } else {
	  	 result = defaultValue ;
	   }
	   
		return result;
	}
	
	/**
	 * �Է¹��� �迭�� nullüũ�� �ǽ��մϴ�.
	 * 
	 * @param checkValues
	 * @return
	 */
	protected String[] checkStrArr(String[] checkValues) {
		return checkValues == null ? new String[0] : checkValues;
	}
	
	/**
	 * ������ �˻��ؼ� �α����� �Ǿ��ִ��� üũ�Ѵ�.
	 * @param request
	 * @return
	 */
	protected boolean isLogedin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		return session.getAttribute("") != null && ((String) session.getAttribute("")).length() > 0;
	}
	
	/**
	 * �������� : ����� ID
	 * @param request
	 * @return
	 */
	protected String getSessionMgrId(HttpServletRequest request) {
		session = request.getSession();
		String mgr_id = String.valueOf(session.getAttribute("SSEEION_MGR_ID"));
		return mgr_id;
	}
	
	/**
	 * �������� : �̸�
	 * @param request
	 * @return
	 */
	protected String getSessionName(HttpServletRequest request) {
		session = request.getSession();
		String name = String.valueOf(session.getAttribute("SSEEION_NAME"));
		return getCipherDecoding(name);
	}
	
	/**
	 * �������� : �Ҽ�
	 * @param request
	 * @return
	 */
	protected String getSessionPart(HttpServletRequest request) {
		session = request.getSession();
		String part = String.valueOf(session.getAttribute("SSEEION_PART"));
		return part;
	}
	
	/**
	 * �������� : ����ó
	 * @param request
	 * @return
	 */
	protected String getSessionPhone(HttpServletRequest request) {
		session = request.getSession();
		String phone = String.valueOf(session.getAttribute("SSEEION_PHONE"));
		return getCipherDecoding(phone);
	}
	
	/**
	 * �������� : EMAIL
	 * @param request
	 * @return
	 */
	protected String getSessionEmail(HttpServletRequest request) {
		session = request.getSession();
		String email = String.valueOf(session.getAttribute("SSEEION_EMAIL"));
		return getCipherDecoding(email);
	}
	
	/**
	 * �������� : LEV
	 * @param request
	 * @return
	 */
	protected String getSessionLev(HttpServletRequest request) {
		session = request.getSession();
		String lev = String.valueOf(session.getAttribute("SSEEION_LEV"));
		return getCipherDecoding(lev);
	}
	
	/**
	 * �������� : LEV
	 * @param request
	 * @return
	 */
	protected String getLoginDate(HttpServletRequest request) {
		session = request.getSession();
		String login_date = String.valueOf(session.getAttribute("LOGIN_DATE"));
		return getCipherDecoding(login_date);
	}
	
	/**
	 * �������� : decoging
	 * @param String
	 * @return
	 */
	protected String getCipherDecoding(String encoding) {
		
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		
		return dbCipher.decoding(encoding);
	}
}
