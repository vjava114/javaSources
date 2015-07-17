/*
 * Filename	: CommonController.java
 * Class		: com.wallet.common.web.CommonController
 * History	: 2012/07/18, litlhope, develop
 *
 * Comment	: 모든 Controller의 상위 Controller 입니다.
 * 					  특별한 이유가 없는 한 모든 컨트롤러는 이 컨트롤러를 
 * 					  상속해야 합니다.
 */

package com.wallet.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rocomo.servlet.RocomoController;
import com.wallet.common.cipher.KTDBCipher;
import com.wallet.common.util.Log;

/**
 * Controller의 상위 컨트롤러.
 * 
 * @since   JDK 1.6.0
 * @version 1.0
 * @author  Copyright (c) 2012 by ROCOMO.INC. All Rights Reserved.
 */
public class CommonController extends RocomoController {
	
	HttpSession session = null;
	
 
	/**
	 * 입력받은 문자열에 대해 유효성 및 보안체크를 실시합니다.
	 * 
	 * @param checkValue	체크 할 문자열
	 * @param defaultValue	체크 실패시 반환 할 기본 문자열
	 * @return	유효성 및 보안체크가 완료된 문자열을 반환 한다.
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
	 * 입력받은 문자열에 대해 유효성 및 보안체크를 실시합니다.
	 * 
	 * @param checkValue	체크 할 문자열
	 * @param defaultValue	체크 실패시 반환 할 기본 문자열
	 * @return	유효성 및 보안체크가 완료된 문자열을 반환 한다.
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
	 * 입력받은 배열의 null체크를 실시합니다.
	 * 
	 * @param checkValues
	 * @return
	 */
	protected String[] checkStrArr(String[] checkValues) {
		return checkValues == null ? new String[0] : checkValues;
	}
	
	/**
	 * 세션을 검사해서 로그인이 되어있는지 체크한다.
	 * @param request
	 * @return
	 */
	protected boolean isLogedin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		return session.getAttribute("") != null && ((String) session.getAttribute("")).length() > 0;
	}
	
	/**
	 * 세션정보 : 사용자 ID
	 * @param request
	 * @return
	 */
	protected String getSessionMgrId(HttpServletRequest request) {
		session = request.getSession();
		String mgr_id = String.valueOf(session.getAttribute("SSEEION_MGR_ID"));
		return mgr_id;
	}
	
	/**
	 * 세션정보 : 이름
	 * @param request
	 * @return
	 */
	protected String getSessionName(HttpServletRequest request) {
		session = request.getSession();
		String name = String.valueOf(session.getAttribute("SSEEION_NAME"));
		return getCipherDecoding(name);
	}
	
	/**
	 * 세션정보 : 소속
	 * @param request
	 * @return
	 */
	protected String getSessionPart(HttpServletRequest request) {
		session = request.getSession();
		String part = String.valueOf(session.getAttribute("SSEEION_PART"));
		return part;
	}
	
	/**
	 * 세션정보 : 연락처
	 * @param request
	 * @return
	 */
	protected String getSessionPhone(HttpServletRequest request) {
		session = request.getSession();
		String phone = String.valueOf(session.getAttribute("SSEEION_PHONE"));
		return getCipherDecoding(phone);
	}
	
	/**
	 * 세션정보 : EMAIL
	 * @param request
	 * @return
	 */
	protected String getSessionEmail(HttpServletRequest request) {
		session = request.getSession();
		String email = String.valueOf(session.getAttribute("SSEEION_EMAIL"));
		return getCipherDecoding(email);
	}
	
	/**
	 * 세션정보 : LEV
	 * @param request
	 * @return
	 */
	protected String getSessionLev(HttpServletRequest request) {
		session = request.getSession();
		String lev = String.valueOf(session.getAttribute("SSEEION_LEV"));
		return getCipherDecoding(lev);
	}
	
	/**
	 * 세션정보 : LEV
	 * @param request
	 * @return
	 */
	protected String getLoginDate(HttpServletRequest request) {
		session = request.getSession();
		String login_date = String.valueOf(session.getAttribute("LOGIN_DATE"));
		return getCipherDecoding(login_date);
	}
	
	/**
	 * 세션정보 : decoging
	 * @param String
	 * @return
	 */
	protected String getCipherDecoding(String encoding) {
		
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		
		return dbCipher.decoding(encoding);
	}
}
