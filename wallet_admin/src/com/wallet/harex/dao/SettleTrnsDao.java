package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.SettleTrnsMapper;
import com.wallet.harex.model.SettleTrns;

/*
 * Filename	: SettleTrnsDao.java
 * Class	: com.wallet.harex.dao.SettleTrnsDao
 * History	: 2012/09/10, kma, 작업구분 : 거래내역조회
 * Comment	:
 */
public class SettleTrnsDao extends MybatisCilent implements SettleTrnsMapper {

	/**
	 * 거래내역조회 select
	 * @return	
	 */

	@SuppressWarnings("unchecked")
	public List<SettleTrns> selectSettleTrnsList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleTrnsList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectSettleTrnsListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.ComplexMapper.selectSettleTrnsListCnt", params);
		
	}

	@SuppressWarnings("unchecked")
	public List<SettleTrns> selectSettleTrnsDaily(HashMap<String, Object> params) {

		return sqlMapper.selectList("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleTrnsDaily", params);
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectSettleTrnsDailyCnt(HashMap<String, Object> params) {

		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleTrnsDailyCnt", params);
	}
	
	@SuppressWarnings("unchecked")
	public SettleTrns selectSettleCouponPop(HashMap<String, Object> params) {
		
		return (SettleTrns) sqlMapper.selectOne("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleCouponPop", params);
	}
	
	@SuppressWarnings("unchecked")
	public SettleTrns selectSettleMemberPop(HashMap<String, Object> params) {
		
		return (SettleTrns) sqlMapper.selectOne("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleMemberPop", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<SettleTrns> selectSettleDataList(HashMap<String, Object> params) {
		
		
		List<SettleTrns> result = null;

		result =  sqlMapper.selectList("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleDataFixList", params);
		
		return result;
	}
	
	public Integer selectSettleDataListCnt(HashMap<String, Object> params) {
		
		int result = 0;
		result =  (Integer)sqlMapper.selectOne("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleDataFixListCnt", params);
	
		return result;
	}
	
	public String selectCouponName(String param) {
		
		return  (String) sqlMapper.selectOne("com.wallet.harex.mapper.SettleTrnsMapper.selectCouponName", param);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<SettleTrns> selectSettleDataPerList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleDataPerList", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<SettleTrns> selectSettleDataCpnInfoPop(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleDataCpnInfoPop", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<SettleTrns> selectSettleDataCpnTotPop(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.SettleTrnsMapper.selectSettleDataCpnTotPop", params);
	}
}
