/*
 * Filename	: FormatUtil.java
 * Class		: com.rocomo.util.FormatUtil
 * History	: 2012/07/18, litlhope, develop
 *
 * Comment	: ���� / ȭ�� ���� ȭ�� ǥ�ÿ� ���ڿ��� �����ϴ� 
 * 					  ��ƿ��(static) Ŭ���� �Դϴ�.
 */
package com.rocomo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���� / ȭ�� ���� ȭ�� ǥ�ÿ� ���ڿ��� �����ϱ� ���� ���� Ŭ����
 * 
 * @since   JDK 1.6.0
 * @version 1.0
 * @author  Copyright (c) 2012 by ROCOMO.INC. All Rights Reserved.
 */
public class FormatUtil {
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		return getDate(date, Const.DEFAULT_FORMAT_DATE);
	}
	
	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDate(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	
}


