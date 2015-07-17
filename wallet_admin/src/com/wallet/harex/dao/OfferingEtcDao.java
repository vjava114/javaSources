package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.OfferingEtcMapper;
import com.wallet.harex.mapper.TransMstMapper;
import com.wallet.harex.model.OfferingCpn;
import com.wallet.harex.model.OfferingDtl;
import com.wallet.harex.model.OfferingMsDc;
import com.wallet.harex.model.OfferingMsSave;
import com.wallet.harex.model.OfferingMsStamp;
import com.wallet.harex.model.OfferingMsUse;
import com.wallet.harex.model.OfferingOrder;
import com.wallet.harex.model.OfferingPromo;
import com.wallet.harex.model.OfferingS;
import com.wallet.harex.model.TransMst;
import com.wallet.harex.model.TransMstCpn;
import com.wallet.harex.model.TransMstMsDc;
import com.wallet.harex.model.TransMstMsUse;
import com.wallet.harex.model.TransMstSave;
import com.wallet.harex.model.TransMstStamp;

public class OfferingEtcDao extends MybatisCilent implements OfferingEtcMapper {
	/**
	 * 복합결제 오퍼링 정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingS> selectOfferingS(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingS", params);
	}
	
	/**
	 * 복합결제 오퍼링 정보 CNT
	 * @return	
	 */
	public Integer selectOfferingSCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingSCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 쿠폰 상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingCpn> selectOfferingCpn(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingCpn", params);
	}
	
	/**
	 * 복합결제 오퍼링 쿠폰 상세정보 CNT
	 * @return	
	 */
	public Integer selectOfferingCpnCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingCpnCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingDtl> selectOfferingDtl(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingDtl", params);
	}
	
	/**
	 * 복합결제 오퍼링 상세정보 CNT
	 * @return	
	 */
	public Integer selectOfferingDtlCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingDtlCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽할인 상세정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsDc> selectOfferingMsDc(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsDc", params);
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽할인 상세정보 CNT
	 * @return	
	 */
	public Integer selectOfferingMsDcCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsDcCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 적립정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsSave> selectOfferingMsSave(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsSave", params);
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 적립정보 CNT
	 * @return	
	 */
	public Integer selectOfferingMsSaveCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsSaveCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 	멤버쉽 스탬프정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsStamp> selectOfferingMsStamp(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsStamp", params);
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 스탬프정보 CNT
	 * @return	
	 */
	public Integer selectOfferingMsStampCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsStampCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 	멤버쉽 사용정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsUse> selectOfferingMsUse(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsUse", params);
	}
	
	/**
	 * 복합결제 오퍼링 멤버쉽 사용정보 CNT
	 * @return	
	 */
	public Integer selectOfferingMsUseCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsUseCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 	순위 정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingOrder> selectOfferingOrder(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingOrder", params);
	}
	
	/**
	 * 복합결제 오퍼링 순위 정보 CNT
	 * @return	
	 */
	public Integer selectOfferingOrderCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingOrderCnt", params);
	}
	
	/**
	 * 복합결제 오퍼링 	프로모션정보 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingPromo> selectOfferingPromo(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingPromo", params);
	}
	
	/**
	 * 복합결제 오퍼링 프로모션정보 CNT
	 * @return	
	 */
	public Integer selectOfferingPromoCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingPromoCnt", params);
	}
	
	
}