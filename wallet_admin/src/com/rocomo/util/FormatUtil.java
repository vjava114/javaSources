/*
 * Filename	: FormatUtil.java
 * Class		: com.rocomo.util.FormatUtil
 * History	: 2012/07/18, litlhope, develop
 *
 * Comment	: 날자 / 화폐 등의 화면 표시용 문자열을 생성하는 
 * 					  유틸성(static) 클래스 입니다.
 */
package com.rocomo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 날자 / 화폐 등의 화면 표시용 문자열을 생성하기 위한 범용 클래스
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


