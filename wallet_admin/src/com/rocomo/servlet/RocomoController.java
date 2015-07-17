/*
 * Filename	: RocomoController.java
 * Class		: com.rocomo.servlet.RocomoController
 * History	: 2012/07/18, litlhope, develop
 *
 * Comment	: MVC 모델 프로젝트의 모든 컨트롤러의 최상위 컨트롤러 입니다.
 * 					  getParam 메소드는 이곳에 구현이 되어 있습니다.
 * 					  getParam 메소드에서 사용할 checkStr메소드(실제로 문자열 체크가 발생하는 메소드)
 * 					  는 각 프로젝트의 공통 컨트롤러에서 구현합니다.
 */
package com.rocomo.servlet;

import javax.servlet.http.HttpServletRequest;

import com.rocomo.util.Const;

/**
 * MVC 중 컨트롤러의 최상위 클래스
 * 
 * @since   JDK 1.6.0
 * @version 1.0
 * @author  Copyright (c) 2012 by ROCOMO.INC. All Rights Reserved.
 */
public abstract class RocomoController {
	/**
	 * 파라메터의 값을 가져옵니다.
	 * 
	 * @param request
	 * @param key	가져올 파라메터의 키.
	 * @return	key 해당하는 파라메터의 값.
	 */
	protected String getParam(HttpServletRequest request, String key) {
		return checkStr(request.getParameter(key));
	}
	
	/**
	 * 파라메터의 값을 가져옵니다.
	 * 
	 * @param request
	 * @param key	가져올 파라메터의 키.
	 * @param defaultValue	key에 해당하는 파라메터의 값이 null이거나 blank일 경우 반환 할 기본값.
	 * @return	key 해당하는 파라메터의 값 또는 defaultValue
	 */
	protected String getParam(HttpServletRequest request, String key, String defaultValue) {
		return checkStr(request.getParameter(key), defaultValue);
	}
	
	protected String checkStr(String checkValue) {
		return checkStr(checkValue, Const.DEFAULT_BLANK_STRING);
	}
	
	protected abstract String checkStr(String checkValue, String defaultValue);
	protected abstract String[] checkStrArr(String[] checkValues);
}
