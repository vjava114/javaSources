package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.CompareDao;
import com.wallet.harex.model.Compare;


/*
 * Filename	: CompareService.java
 * Class	:  com.wallet.harex.service.CompareService
 * History	: 2012/09/13, mhjang, �۾����� : ������
 * Comment	:
 */
public class CompareService {
	
	private Logger log = Log.getLogger("logs");
	private final CompareDao dao;
	
	public CompareService() {
		dao = new CompareDao();
	}
	
	/**
	 * ������ select ����
	 */
	public List<Compare> selectCompareList(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareList(params);
		return result;
	}
	
	/**
	 * ������ select ���� Excel
	 */
	public List<Compare> selectCompareListExcel(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareList(params);
		return result;
	}
	
	/**
	 * ������ select cnt ����
	 */
	public Integer selectCompareListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectCompareListCnt(params).toString());
		
	}
	
	
	/**
	 * ������ select �����
	 */
	public List<Compare> selectCompareMemList(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareMemList(params);
		return result;
	}
	
	/**
	 * ������ select ����� excel
	 */
	public List<Compare> selectCompareMemListExcel(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareMemListExcel(params);
		return result;
	}
	
	/**
	 * ������ select cnt �����
	 */
	public Integer selectCompareMemListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectCompareMemListCnt(params).toString());
		
	}
	
}