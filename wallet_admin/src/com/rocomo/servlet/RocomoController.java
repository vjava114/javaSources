/*
 * Filename	: RocomoController.java
 * Class		: com.rocomo.servlet.RocomoController
 * History	: 2012/07/18, litlhope, develop
 *
 * Comment	: MVC �� ������Ʈ�� ��� ��Ʈ�ѷ��� �ֻ��� ��Ʈ�ѷ� �Դϴ�.
 * 					  getParam �޼ҵ�� �̰��� ������ �Ǿ� �ֽ��ϴ�.
 * 					  getParam �޼ҵ忡�� ����� checkStr�޼ҵ�(������ ���ڿ� üũ�� �߻��ϴ� �޼ҵ�)
 * 					  �� �� ������Ʈ�� ���� ��Ʈ�ѷ����� �����մϴ�.
 */
package com.rocomo.servlet;

import javax.servlet.http.HttpServletRequest;

import com.rocomo.util.Const;

/**
 * MVC �� ��Ʈ�ѷ��� �ֻ��� Ŭ����
 * 
 * @since   JDK 1.6.0
 * @version 1.0
 * @author  Copyright (c) 2012 by ROCOMO.INC. All Rights Reserved.
 */
public abstract class RocomoController {
	/**
	 * �Ķ������ ���� �����ɴϴ�.
	 * 
	 * @param request
	 * @param key	������ �Ķ������ Ű.
	 * @return	key �ش��ϴ� �Ķ������ ��.
	 */
	protected String getParam(HttpServletRequest request, String key) {
		return checkStr(request.getParameter(key));
	}
	
	/**
	 * �Ķ������ ���� �����ɴϴ�.
	 * 
	 * @param request
	 * @param key	������ �Ķ������ Ű.
	 * @param defaultValue	key�� �ش��ϴ� �Ķ������ ���� null�̰ų� blank�� ��� ��ȯ �� �⺻��.
	 * @return	key �ش��ϴ� �Ķ������ �� �Ǵ� defaultValue
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
