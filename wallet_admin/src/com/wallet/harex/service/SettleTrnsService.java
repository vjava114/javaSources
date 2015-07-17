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
 * History	: 2012/09/10, kma, �۾����� : �������
 * Comment	:
 */
public class SettleTrnsService {
	private Logger log = Log.getLogger("logs");
	private final SettleTrnsDao dao;
	
	public SettleTrnsService() {
		dao = new SettleTrnsDao();
	}
	
	/**
	 * ������� > �ŷ�������ȸ select
	 */
	public List<SettleTrns> selectSettleTrnsList(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;

		result = dao.selectSettleTrnsList(params);
		
		return result;
	}
	
	/**
	 * ������� > �ŷ�������ȸ select > ī��Ʈ
	 */
	public int selectSettleTrnsListCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectSettleTrnsListCnt(params).toString());
	}
	
	/**
	 * ������� > �ŷ�������ȸ > ��������ȸ select
	 */
	public SettleTrns  selectSettleCouponPop(HashMap<String, Object> params) {
		
		SettleTrns result= null;
		
		result = dao.selectSettleCouponPop(params);
		
		return result;
	}
	
	/**
	 * ������� > �ŷ�������ȸ > ����ʻ���ȸ select
	 */
	public SettleTrns  selectSettleMemberPop(HashMap<String, Object> params) {
		
		SettleTrns result = null;
		
		result = dao.selectSettleMemberPop(params);
		
		return result;
	}
	
	/**
	 * ������� > �Ϻ���Ȳ���� select
	 */
	public List<SettleTrns> selectSettleTrnsDaily(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		
		result = dao.selectSettleTrnsDaily(params);
		
		return result;
	}
	
	/**
	 * ������� > �Ϻ���Ȳ���� select  ī��Ʈ
	 */
	public int selectSettleTrnsDailyCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectSettleTrnsDailyCnt(params).toString());
	}
	
	/**
	 * ������� > ����DATA��ȸ select
	 */
	public List<SettleTrns> selectSettleDataList(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		result = dao.selectSettleDataList(params);
		return result;
	}
	
	/**
	 * ������� > ����DATA��ȸ select
	 */
	public int selectSettleDataListCnt(HashMap<String, Object> params) {
		
		return Integer.parseInt(dao.selectSettleDataListCnt(params).toString());
	}
	
	/**
	 * ������� > ����DATA��ȸ > ������ ��ȸ
	 */
	public String selectCouponName(String param) {
		
		String result = null;
		
		result = dao.selectCouponName(param);
		
		return result;
	}
	
	/**
	 * ������� > ����DATA��ȸ  > ���������� select
	 */
	public List<SettleTrns> selectSettleDataCpnInfoPop(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		
		result = dao.selectSettleDataCpnInfoPop(params);
		
		return result;
	}
	
	/**
	 * ������� > ����DATA��ȸ  > ������������ select
	 */
	public List<SettleTrns> selectSettleDataCpnTotPop(HashMap<String, Object> params) {
		
		List<SettleTrns> result = null;
		
		result = dao.selectSettleDataCpnTotPop(params);
		
		return result;
	}
}
