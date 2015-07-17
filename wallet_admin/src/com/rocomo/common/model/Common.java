package com.rocomo.common.model;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Common implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//DB ���̺��� ���� ���� �ʴ� �Ӽ��� ���� ��� ���� ����. 
	private HashMap<Object, Object> hashMap;
	
	public HashMap<Object, Object> getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap<Object, Object> hashMap) {
		this.hashMap = hashMap;
	}

	/**
	 * toString �޼ҵ带 �������̵� �Ѵ�.
	 * ��ü �񱳸� �ϱ� ����.
	 */
	
	public String toString() {
		//DEFAULT_STYLE, SIMPLE_STYLE, MULTI_LINE_STYLE
		return ToStringBuilder.reflectionToString (this, ToStringStyle.SIMPLE_STYLE);
	} 
}
