package com.wallet.harex.mapper;

import com.wallet.harex.model.SettleTrns;

import java.util.HashMap;
import java.util.List;

public interface SettleTrnsMapper {
	
	
	/**
	 * 정산관리 > 일별현황관리
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleTrnsDaily(HashMap<String, Object> params);
	
	/**
	 * 정산관리 > 일별현황관리 카운트
	 * @param params
	 * @return
	 */
	Integer selectSettleTrnsDailyCnt(HashMap<String, Object> params);  
	
	/**
	 * 정산관리 > 거래내역조회
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleTrnsList(HashMap<String, Object> params);
	
	/**
	 * 정산관리 > 거래내역조회 > 카운트
	 * @param params
	 * @return
	 */
	Integer selectSettleTrnsListCnt(HashMap<String, Object> params);
	
	/**
	 * 정산관리 > 거래내역조회 > 쿠폰할인상세조회 팝업
	 * @param params
	 * @return
	 */
	
	SettleTrns selectSettleCouponPop(HashMap<String, Object> params);  
	
	/**
	 * 정산관리 > 거래내역조회 > 멤버십상세조회 팝업
	 * @param params
	 * @return
	 */
	SettleTrns selectSettleMemberPop(HashMap<String, Object> params);  
	
	/**
	 * 정산관리 > 정산DATA조회
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleDataList(HashMap<String, Object> params);  
	
	/**
	 * 정산관리 > 정산DATA조회 > 쿠폰상세정보
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleDataCpnInfoPop(HashMap<String, Object> params);  
	
	/**
	 * 정산관리 > 정산DATA조회 > 쿠폰결제금액내역
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleDataCpnTotPop(HashMap<String, Object> params); 
	
	/**
	 * 정산관리 > 정산DATA조회 > 쿠폰명조회
	 * @param params
	 * @return
	 */
	String selectCouponName(String param);

	/**
	 * 정산관리 > 정산DATA조회 > 목록 Count
	 * @param params
	 * @return
	 */
	Integer selectSettleDataListCnt(HashMap<String, Object> params);  
}