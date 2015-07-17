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
	 * �ŷ�������ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMst(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMst", params);
	}
	
	/**
	 * �ŷ�������ȸ CNT
	 * @return	
	 */
	public Integer selectTransMstCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstCnt", params);
		
	}
	
	/**
	 * �ŷ�������ȸ - ���� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstCpn(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstCpn", params);
	}
	
	/**
	 * �ŷ�������ȸ - ���� CNT
	 * @return	
	 */
	public Integer selectTransMstCpnCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstCpnCnt", params);
		
	}
	
	/**
	 * �ŷ�������ȸ_��������� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstMsDc(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsDc", params);
	}
	
	/**
	 * �ŷ�������ȸ_��������� CNT
	 * @return	
	 */
	public Integer selectTransMstMsDcCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsDcCnt", params);
		
	}
	
	/**
	 * �ŷ�������ȸ_���������Ʈ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstMsUse(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsUse", params);
	}
	
	/**
	 * �ŷ�������ȸ_���������Ʈ CNT
	 * @return	
	 */
	public Integer selectTransMstMsUseCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsUseCnt", params);
		
	}
	
	/**
	 * �ŷ�������ȸ_��������� select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstMsSave(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsSave", params);
	}
	
	/**
	 * �ŷ�������ȸ_��������� CNT
	 * @return	
	 */
	public Integer selectTransMstMsSaveCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsSaveCnt", params);
		
	}
	
	/**
	 * �ŷ�������ȸ_������ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<TransMst> selectTransMstStamp(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.TransMstMapper.selectTransMstStamp", params);
	}
	
	/**
	 * �ŷ�������ȸ_������ CNT
	 * @return	
	 */
	public Integer selectTransMstStampCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstStampCnt", params);
		
	}
	
	/**
	 * �ŷ�������ȸ popup_����
	 * @return	
	 */
	public TransMstCpn selectTransMstCpnPop(HashMap<String, Object> params) {
		
		return (TransMstCpn) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstCpn", params);
	}
	
	/**
	 * �ŷ�������ȸ popup_���������
	 * @return	
	 */
	public TransMstMsDc selectTransMstMsDcPop(HashMap<String, Object> params) {
		
		return (TransMstMsDc) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsDc", params);
	}
	
	/**
	 * �ŷ�������ȸ popup_���������Ʈ
	 * @return	
	 */
	public TransMstMsUse selectTransMstMsUsePop(HashMap<String, Object> params) {
		
		return (TransMstMsUse) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsUse", params);
	}
	
	/**
	 * �ŷ�������ȸ popup_���������
	 * @return	
	 */
	public TransMstSave selectTransMstMsSavePop(HashMap<String, Object> params) {
		
		return (TransMstSave) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstMsSave", params);
	}
	
	/**
	 * �ŷ�������ȸ popup_������
	 * @return	
	 */
	public TransMstStamp selectTransMstStampPop(HashMap<String, Object> params) {
		
		return (TransMstStamp) sqlMapper.selectOne("com.wallet.harex.mapper.TransMstMapper.selectTransMstStamp", params);
	}
	
}