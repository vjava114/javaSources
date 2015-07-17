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
	 * 복합결제 이용 가맹점 조회
	 */
	public List<ComplexList> selectComplexList(HashMap<String,Object> params) {
		
		List<ComplexList> result = null;
		result = dao.selectComplexList(params);
		return result;
	}
	
	/**
	 * 복합결제 이용 가맹점 조회 카운트
	 */
	public int selectComplexListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectComplexListCnt(params).toString());
	}
	
	/**
	 * 복합결제 이용 가맹점 - 제휴사 상세정보
	 */
	public ComplexInfo selectComplexInfo(HashMap<String,Object> params) {
		
		ComplexInfo result = null;
		result = dao.selectComplexInfo(params);
		return result;
	}

	/**
	 * 결제사별 복합결제 가맹점 SHOP_ID 매핑관리
	 */
	public List<ComplexShopHid> selectComplexShopHidList(HashMap<String,Object> params) {
		
		List<ComplexShopHid> result = null;
		result = dao.selectComplexShopHidList(params);
		return result;
	}
	
	/**
	 * 결제사별 복합결제 가맹점 SHOP_ID 매핑관리 Count
	 */
	public int selectComplexShopHidListCnt(HashMap<String,Object> params) {
		return Integer.parseInt(dao.selectComplexShopHidListCnt(params).toString());
	}
	
	/**
	 * 복합결제 회원연동 관리
	 */
	public List<ComplexUserList> selectComplexUserList(HashMap<String,Object> params) {
		
		List<ComplexUserList> result = null;
		result = dao.selectComplexUserList(params);
		return result;
	}
	
	/**
	 * 복합결제 회원연동 관리 > 카운트
	 */
	public int selectComplexUserListCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectComplexUserListCnt(params).toString());
	}
	
	/**
	 * 결제사별 복합결제 가맹점 SHOP_ID : H_ID 매핑관리
	 */
	public int updateComplexShopHid(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.updateComplexShopHid(params);
		return result;
	}
	
	/**
	 *  결제사별 복합결제 가맹점 SHOP_ID : H_ID 중복확인
	 */
	public String selectComplexShopHidRegisterCheck(String h_shop_id){		
		return dao.selectComplexShopHidRegisterCheck(h_shop_id);
		
	}
	
}
