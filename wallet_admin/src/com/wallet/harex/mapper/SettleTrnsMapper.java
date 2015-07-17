package com.wallet.harex.mapper;

import com.wallet.harex.model.SettleTrns;

import java.util.HashMap;
import java.util.List;

public interface SettleTrnsMapper {
	
	
	/**
	 * ������� > �Ϻ���Ȳ����
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleTrnsDaily(HashMap<String, Object> params);
	
	/**
	 * ������� > �Ϻ���Ȳ���� ī��Ʈ
	 * @param params
	 * @return
	 */
	Integer selectSettleTrnsDailyCnt(HashMap<String, Object> params);  
	
	/**
	 * ������� > �ŷ�������ȸ
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleTrnsList(HashMap<String, Object> params);
	
	/**
	 * ������� > �ŷ�������ȸ > ī��Ʈ
	 * @param params
	 * @return
	 */
	Integer selectSettleTrnsListCnt(HashMap<String, Object> params);
	
	/**
	 * ������� > �ŷ�������ȸ > �������λ���ȸ �˾�
	 * @param params
	 * @return
	 */
	
	SettleTrns selectSettleCouponPop(HashMap<String, Object> params);  
	
	/**
	 * ������� > �ŷ�������ȸ > ����ʻ���ȸ �˾�
	 * @param params
	 * @return
	 */
	SettleTrns selectSettleMemberPop(HashMap<String, Object> params);  
	
	/**
	 * ������� > ����DATA��ȸ
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleDataList(HashMap<String, Object> params);  
	
	/**
	 * ������� > ����DATA��ȸ > ����������
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleDataCpnInfoPop(HashMap<String, Object> params);  
	
	/**
	 * ������� > ����DATA��ȸ > ���������ݾ׳���
	 * @param params
	 * @return
	 */
	List<SettleTrns> selectSettleDataCpnTotPop(HashMap<String, Object> params); 
	
	/**
	 * ������� > ����DATA��ȸ > ��������ȸ
	 * @param params
	 * @return
	 */
	String selectCouponName(String param);

	/**
	 * ������� > ����DATA��ȸ > ��� Count
	 * @param params
	 * @return
	 */
	Integer selectSettleDataListCnt(HashMap<String, Object> params);  
}