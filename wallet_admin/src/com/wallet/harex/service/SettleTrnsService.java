package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.SettleTrnsDao;
import com.wallet.harex.model.SettleTrns;


/*
 * Filename	: SettleTrnsService.java
 * Class	: com.wallet.harex.service.SettleTrnsService
 * History	: 2012/09/10, kma, 작업구분 : 정산관리
 * Comment	:
 */
public class SettleTrnsService {
	private Logger log = Log.getLogger("logs");
	private final SettleTrnsDao dao;
	
	public SettleTrnsService() {
		dao = new SettleTrnsDao();
	}
	
	/**
	 * 정산관리 > 거래내역조회 select
	 */
	public List<SettleTrns> selectSettleTrnsList(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;

		result = dao.selectSettleTrnsList(params);
		
		return result;
	}
	
	/**
	 * 정산관리 > 거래내역조회 select > 카운트
	 */
	public int selectSettleTrnsListCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectSettleTrnsListCnt(params).toString());
	}
	
	/**
	 * 정산관리 > 거래내역조회 > 쿠폰상세조회 select
	 */
	public SettleTrns  selectSettleCouponPop(HashMap<String, Object> params) {
		
		SettleTrns result= null;
		
		result = dao.selectSettleCouponPop(params);
		
		return result;
	}
	
	/**
	 * 정산관리 > 거래내역조회 > 멤버십상세조회 select
	 */
	public SettleTrns  selectSettleMemberPop(HashMap<String, Object> params) {
		
		SettleTrns result = null;
		
		result = dao.selectSettleMemberPop(params);
		
		return result;
	}
	
	/**
	 * 정산관리 > 일별현황관리 select
	 */
	public List<SettleTrns> selectSettleTrnsDaily(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		
		result = dao.selectSettleTrnsDaily(params);
		
		return result;
	}
	
	/**
	 * 정산관리 > 일별현황관리 select  카운트
	 */
	public int selectSettleTrnsDailyCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectSettleTrnsDailyCnt(params).toString());
	}
	
	/**
	 * 정산관리 > 정산DATA조회 select
	 */
	public List<SettleTrns> selectSettleDataList(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		result = dao.selectSettleDataList(params);
		return result;
	}
	
	/**
	 * 정산관리 > 정산DATA조회 select
	 */
	public int selectSettleDataListCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectSettleDataListCnt(params).toString());
	}
	
	/**
	 * 정산관리 > 정산DATA조회 > 쿠폰명 조회
	 */
	public String selectCouponName(String param) {
		
		String result = null;
		
		result = dao.selectCouponName(param);
		
		return result;
	}
	
	/**
	 * 정산관리 > 정산DATA조회  > 쿠폰상세정보 select
	 */
	public List<SettleTrns> selectSettleDataCpnInfoPop(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		
		result = dao.selectSettleDataCpnInfoPop(params);
		
		return result;
	}
	
	/**
	 * 정산관리 > 정산DATA조회  > 쿠폰결제내역 select
	 */
	public List<SettleTrns> selectSettleDataCpnTotPop(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		
		result = dao.selectSettleDataCpnTotPop(params);
		
		return result;
	}
}
