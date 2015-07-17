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
	 * ���հ��� ���۸� ���� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingS> selectOfferingS(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingS", params);
	}
	
	/**
	 * ���հ��� ���۸� ���� CNT
	 * @return	
	 */
	public Integer selectOfferingSCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingSCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� ���� ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingCpn> selectOfferingCpn(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingCpn", params);
	}
	
	/**
	 * ���հ��� ���۸� ���� ������ CNT
	 * @return	
	 */
	public Integer selectOfferingCpnCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingCpnCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingDtl> selectOfferingDtl(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingDtl", params);
	}
	
	/**
	 * ���հ��� ���۸� ������ CNT
	 * @return	
	 */
	public Integer selectOfferingDtlCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingDtlCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� ��������� ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsDc> selectOfferingMsDc(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsDc", params);
	}
	
	/**
	 * ���հ��� ���۸� ��������� ������ CNT
	 * @return	
	 */
	public Integer selectOfferingMsDcCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsDcCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� ����� �������� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsSave> selectOfferingMsSave(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsSave", params);
	}
	
	/**
	 * ���հ��� ���۸� ����� �������� CNT
	 * @return	
	 */
	public Integer selectOfferingMsSaveCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsSaveCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� 	����� ���������� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsStamp> selectOfferingMsStamp(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsStamp", params);
	}
	
	/**
	 * ���հ��� ���۸� ����� ���������� CNT
	 * @return	
	 */
	public Integer selectOfferingMsStampCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsStampCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� 	����� ������� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingMsUse> selectOfferingMsUse(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsUse", params);
	}
	
	/**
	 * ���հ��� ���۸� ����� ������� CNT
	 * @return	
	 */
	public Integer selectOfferingMsUseCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingMsUseCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� 	���� ���� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingOrder> selectOfferingOrder(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingOrder", params);
	}
	
	/**
	 * ���հ��� ���۸� ���� ���� CNT
	 * @return	
	 */
	public Integer selectOfferingOrderCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingOrderCnt", params);
	}
	
	/**
	 * ���հ��� ���۸� 	���θ������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<OfferingPromo> selectOfferingPromo(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingPromo", params);
	}
	
	/**
	 * ���հ��� ���۸� ���θ������ CNT
	 * @return	
	 */
	public Integer selectOfferingPromoCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.OfferingEtcMapper.selectOfferingPromoCnt", params);
	}
	
	
}