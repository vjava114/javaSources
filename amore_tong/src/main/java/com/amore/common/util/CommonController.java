/*
 * Filename	: CommonController.java
 * Class		: com.wallet.common.web.CommonController
 * History	: 2012/07/18, litlhope, develop
 *
 * Comment	: 모든 Controller의 상위 Controller 입니다.
 * 					  특별한 이유가 없는 한 모든 컨트롤러는 이 컨트롤러를 
 * 					  상속해야 합니다.
 */

package com.amore.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Controller의 상위 컨트롤러.
 * 
 * @since   JDK 1.6.0
 * @version 1.0
 * @author  Copyright (c) 2012 by ROCOMO.INC. All Rights Reserved.
 */
public class CommonController{
	
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
	

}
