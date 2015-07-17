package com.amore.common.util;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Common implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//DB 테이블에서 갖고 있지 않는 속성값 들을 담아 쓰기 위함. 
	private HashMap<Object, Object> hashMap;
	
	public HashMap<Object, Object> getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap<Object, Object> hashMap) {
		this.hashMap = hashMap;
	}

	/**
	 * toString 메소드를 오버라이드 한다.
	 * 객체 비교를 하기 위함.
	 */
	
	public String toString() {
		//DEFAULT_STYLE, SIMPLE_STYLE, MULTI_LINE_STYLE
		return ToStringBuilder.reflectionToString (this, ToStringStyle.SIMPLE_STYLE);
	} 
}
