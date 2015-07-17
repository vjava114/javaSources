package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdPaymentMapper;
import com.wallet.admin.model.MwAdPayment;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdPaymentDao.java
 * Class	: com.wallet.admin.dao.MwAdPaymentDao
 * History	: 2012/08/23, psj, 작업구분 : 결제서비스 관리 > 결재
 * Comment	:
 */
public class MwAdPaymentDao extends MybatisCilent implements MwAdPaymentMapper {


	/**
	 * 결제관리 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdPayment> selectPaymentList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdPaymentMapper.selectPaymentList", params);
	}
	
	/**
	 * 이용약관 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdPayment> selectUseClauseList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdPaymentMapper.selectUseClauseList", params);
	}
	
	/**
	 * 이용약관 등록 insert
	 * @return	
	 */
	public int insertUseClause(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdPaymentMapper.insertUseClause", params);
	}
	
	/**
	 * 결제관리 등록 insert
	 * @return	
	 */
	public int insertPaymentReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdPaymentMapper.insertPaymentReg", params);
	}

	/**
	 * 결제관리 정보 상세 조회 select
	 * @return	
	 */
	public MwAdPayment selectPaymentListDtl(HashMap<String, Object> params) {
		return (MwAdPayment) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdPaymentMapper.selectPaymentList", params);
	}
	
	/**
	 * 결제관리 정보 : max+1 main_idx 조회
	 * @return	
	 */
	public MwAdPayment selectPaymentMainIdx() {
		return (MwAdPayment) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdPaymentMapper.selectPaymentMainIdx");
	}
	
	/**
	 * 결제관리 정보 상세 정보 delete
	 * @return	
	 */
	public int deletePaymentDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdPaymentMapper.deletePaymentDtl", params);
	}
	
	/**
	 * 이용약관 정보 update
	 * @return	
	 */
	public int updateUseClause(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdPaymentMapper.updateUseClause", params);
	}
	/**
	 * 이용약관 정보 delete
	 * @return	
	 */
	public int deleteUseClause(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdPaymentMapper.deleteUseClause", params);
	}
	
	/**
	 * 결제관리 상세 정보 변경 update
	 * @return	
	 */
	public int updatePaymentDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdPaymentMapper.updatePaymentDtl", params);
	}
	
	/**
	 * 결제관리 순서 조정  update
	 * @return	
	 */
	public int updatePaymentIdx(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdPaymentMapper.updatePaymentIdx", params);
	}

}
