package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.dao.ComplexDao;
import com.wallet.harex.model.ComplexInfo;
import com.wallet.harex.model.ComplexList;
import com.wallet.harex.model.ComplexShopHid;
import com.wallet.harex.model.ComplexUserList;

public class ComplexService {
	
	private final ComplexDao dao;
	
	public ComplexService() {
		dao = new ComplexDao();
	}
	
	public void commit(){
		dao.commit();
	}

	public void rollback() {
		dao.rollback();
	}
	
	/**
	 * ���հ��� �̿� ������ ��ȸ
	 */
	public List<ComplexList> selectComplexList(HashMap<String,Object> params) {
		
		List<ComplexList> result = null;
		result = dao.selectComplexList(params);
		return result;
	}
	
	/**
	 * ���հ��� �̿� ������ ��ȸ ī��Ʈ
	 */
	public int selectComplexListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectComplexListCnt(params).toString());
	}
	
	/**
	 * ���հ��� �̿� ������ - ���޻� ������
	 */
	public ComplexInfo selectComplexInfo(HashMap<String,Object> params) {
		
		ComplexInfo result = null;
		result = dao.selectComplexInfo(params);
		return result;
	}

	/**
	 * �����纰 ���հ��� ������ SHOP_ID ���ΰ���
	 */
	public List<ComplexShopHid> selectComplexShopHidList(HashMap<String,Object> params) {
		
		List<ComplexShopHid> result = null;
		result = dao.selectComplexShopHidList(params);
		return result;
	}
	
	/**
	 * �����纰 ���հ��� ������ SHOP_ID ���ΰ��� Count
	 */
	public int selectComplexShopHidListCnt(HashMap<String,Object> params) {
		return Integer.parseInt(dao.selectComplexShopHidListCnt(params).toString());
	}
	
	/**
	 * ���հ��� ȸ������ ����
	 */
	public List<ComplexUserList> selectComplexUserList(HashMap<String,Object> params) {
		
		List<ComplexUserList> result = null;
		result = dao.selectComplexUserList(params);
		return result;
	}
	
	/**
	 * ���հ��� ȸ������ ���� > ī��Ʈ
	 */
	public int selectComplexUserListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectComplexUserListCnt(params).toString());
	}
	
	/**
	 * �����纰 ���հ��� ������ SHOP_ID : H_ID ���ΰ���
	 */
	public int updateComplexShopHid(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.updateComplexShopHid(params);
		return result;
	}
	
	/**
	 *  �����纰 ���հ��� ������ SHOP_ID : H_ID �ߺ�Ȯ��
	 */
	public String selectComplexShopHidRegisterCheck(String h_shop_id){		
		return dao.selectComplexShopHidRegisterCheck(h_shop_id);
		
	}
	
}
