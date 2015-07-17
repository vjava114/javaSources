package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdPaymentMapper;
import com.wallet.admin.model.MwAdPayment;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdPaymentDao.java
 * Class	: com.wallet.admin.dao.MwAdPaymentDao
 * History	: 2012/08/23, psj, �۾����� : �������� ���� > ����
 * Comment	:
 */
public class MwAdPaymentDao extends MybatisCilent implements MwAdPaymentMapper {


	/**
	 * �������� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdPayment> selectPaymentList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdPaymentMapper.selectPaymentList", params);
	}
	
	/**
	 * �̿��� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdPayment> selectUseClauseList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdPaymentMapper.selectUseClauseList", params);
	}
	
	/**
	 * �̿��� ��� insert
	 * @return	
	 */
	public int insertUseClause(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdPaymentMapper.insertUseClause", params);
	}
	
	/**
	 * �������� ��� insert
	 * @return	
	 */
	public int insertPaymentReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdPaymentMapper.insertPaymentReg", params);
	}

	/**
	 * �������� ���� �� ��ȸ select
	 * @return	
	 */
	public MwAdPayment selectPaymentListDtl(HashMap<String, Object> params) {
		return (MwAdPayment) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdPaymentMapper.selectPaymentList", params);
	}
	
	/**
	 * �������� ���� : max+1 main_idx ��ȸ
	 * @return	
	 */
	public MwAdPayment selectPaymentMainIdx() {
		return (MwAdPayment) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdPaymentMapper.selectPaymentMainIdx");
	}
	
	/**
	 * �������� ���� �� ���� delete
	 * @return	
	 */
	public int deletePaymentDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdPaymentMapper.deletePaymentDtl", params);
	}
	
	/**
	 * �̿��� ���� update
	 * @return	
	 */
	public int updateUseClause(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdPaymentMapper.updateUseClause", params);
	}
	/**
	 * �̿��� ���� delete
	 * @return	
	 */
	public int deleteUseClause(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdPaymentMapper.deleteUseClause", params);
	}
	
	/**
	 * �������� �� ���� ���� update
	 * @return	
	 */
	public int updatePaymentDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdPaymentMapper.updatePaymentDtl", params);
	}
	
	/**
	 * �������� ���� ����  update
	 * @return	
	 */
	public int updatePaymentIdx(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdPaymentMapper.updatePaymentIdx", params);
	}

}
