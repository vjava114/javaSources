package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.TransMstMapper;
import com.wallet.harex.model.TransMst;
import com.wallet.harex.model.TransMstCpn;
import com.wallet.harex.model.TransMstMsDc;
import com.wallet.harex.model.TransMstMsUse;
import com.wallet.harex.model.TransMstSave;
import com.wallet.harex.model.TransMstStamp;

public class TransMstDao extends MybatisCilent implements TransMstMapper {
	/**
	 * 거래내역조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMst(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMst", params);
	}
	
	/**
	 * 거래내역조회 CNT
	 * @return	
	 */
	public Integer selectTransMstCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstCnt", params);
		
	}
	
	/**
	 * 거래내역조회 - 쿠폰 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstCpn(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstCpn", params);
	}
	
	/**
	 * 거래내역조회 - 쿠폰 CNT
	 * @return	
	 */
	public Integer selectTransMstCpnCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstCpnCnt", params);
		
	}
	
	/**
	 * 거래내역조회_멤버십할인 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstMsDc(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsDc", params);
	}
	
	/**
	 * 거래내역조회_멤버십할인 CNT
	 * @return	
	 */
	public Integer selectTransMstMsDcCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsDcCnt", params);
		
	}
	
	/**
	 * 거래내역조회_멤버십포인트 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstMsUse(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsUse", params);
	}
	
	/**
	 * 거래내역조회_멤버십포인트 CNT
	 * @return	
	 */
	public Integer selectTransMstMsUseCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsUseCnt", params);
		
	}
	
	/**
	 * 거래내역조회_멤버십적립 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstMsSave(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsSave", params);
	}
	
	/**
	 * 거래내역조회_멤버십적립 CNT
	 * @return	
	 */
	public Integer selectTransMstMsSaveCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsSaveCnt", params);
		
	}
	
	/**
	 * 거래내역조회_스탬프 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstStamp(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstStamp", params);
	}
	
	/**
	 * 거래내역조회_스탬프 CNT
	 * @return	
	 */
	public Integer selectTransMstStampCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstStampCnt", params);
		
	}
	
	/**
	 * 거래내역조회 popup_쿠폰
	 * @return	
	 */
	public TransMstCpn selectTransMstCpnPop(HashMap<String, Object> params) {
		
		return (TransMstCpn) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstCpn", params);
	}
	
	/**
	 * 거래내역조회 popup_멤버십할인
	 * @return	
	 */
	public TransMstMsDc selectTransMstMsDcPop(HashMap<String, Object> params) {
		
		return (TransMstMsDc) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsDc", params);
	}
	
	/**
	 * 거래내역조회 popup_멤버십포인트
	 * @return	
	 */
	public TransMstMsUse selectTransMstMsUsePop(HashMap<String, Object> params) {
		
		return (TransMstMsUse) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsUse", params);
	}
	
	/**
	 * 거래내역조회 popup_멤버십적립
	 * @return	
	 */
	public TransMstSave selectTransMstMsSavePop(HashMap<String, Object> params) {
		
		return (TransMstSave) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsSave", params);
	}
	
	/**
	 * 거래내역조회 popup_스탬프
	 * @return	
	 */
	public TransMstStamp selectTransMstStampPop(HashMap<String, Object> params) {
		
		return (TransMstStamp) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstStamp", params);
	}
	
}