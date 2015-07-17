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
 * History	: 2012/09/13, mhjang, 작업구분 : 대사관리
 * Comment	:
 */
public class CompareService {
	
	private Logger log = Log.getLogger("logs");
	private final CompareDao dao;
	
	public CompareService() {
		dao = new CompareDao();
	}
	
	/**
	 * 대사관리 select 쿠폰
	 */
	public List<Compare> selectCompareList(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareList(params);
		return result;
	}
	
	/**
	 * 대사관리 select 쿠폰 Excel
	 */
	public List<Compare> selectCompareListExcel(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareList(params);
		return result;
	}
	
	/**
	 * 대사관리 select cnt 쿠폰
	 */
	public Integer selectCompareListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectCompareListCnt(params).toString());
		
	}
	
	
	/**
	 * 대사관리 select 멤버십
	 */
	public List<Compare> selectCompareMemList(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareMemList(params);
		return result;
	}
	
	/**
	 * 대사관리 select 멤버십 excel
	 */
	public List<Compare> selectCompareMemListExcel(HashMap<String,Object> params) {
		
		List<Compare> result = null;
		result = dao.selectCompareMemListExcel(params);
		return result;
	}
	
	/**
	 * 대사관리 select cnt 멤버십
	 */
	public Integer selectCompareMemListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectCompareMemListCnt(params).toString());
		
	}
	
}