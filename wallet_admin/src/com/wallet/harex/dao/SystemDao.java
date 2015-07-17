package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.SystemMapper;
import com.wallet.harex.model.SystemM;
import com.wallet.harex.model.SystemStatusList;

/*
 * Filename	: SystemDao.java
 * Class	: com.wallet.harex.dao.SystemDao
 * History	: 2012/09/13, kma, �۾����� : �ý��۸���͸�
 * Comment	:
 */
public class SystemDao extends MybatisCilent implements SystemMapper {
	
	public void commit() {
		sqlMapper.commit();
	}


	public void rollback() {
		sqlMapper.rollback();
	}

	/**
	 * �ý��۸���͸���Ȳ ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<SystemStatusList> selectSystemStatusList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.SystemMapper.selectSystemStatusList", params);
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<SystemM> selectSystemSettingList(HashMap<String,Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.SystemMapper.selectSystemSettingList", params);
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� �󼼺��� ��ȸ
	 * @return	
	 */
	public SystemM selectSystemSettingView(HashMap<String,Object> params) {
		
		return (SystemM)sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectSystemSettingView", params);
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� �󼼺��� ��ȸ(����ȭ��)
	 * @return	
	 */
	public SystemM selectSystemSettingModifyView(HashMap<String,Object> params) {
		
		return (SystemM)sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectSystemSettingModifyView", params);
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��ȸ row count
	 * @return	
	 */
	public Integer selectSystemSettingListCnt() {
		
		return (Integer)sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectSystemSettingListCnt");
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���� ó�� MW_PS_MON_SVR
	 */
	public int updateSystemSettingSvr(HashMap<String, Object> params){
		
		return sqlMapper.update("com.wallet.harex.mapper.SystemMapper.updateSystemSettingSvr", params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���� ó�� MW_PS_MON_OPER
	 */
	public int updateSystemSettingOper(HashMap<String, Object> params){
		
		return sqlMapper.update("com.wallet.harex.mapper.SystemMapper.updateSystemSettingOper", params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���� ó�� MW_PS_MON_IF
	 */
	public int updateSystemSettingIf(HashMap<String, Object> params){
		
		return sqlMapper.update("com.wallet.harex.mapper.SystemMapper.updateSystemSettingIf", params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ��� ó�� MW_PS_MON_SVR
	 */
	public int insertSystemSettingSvr(HashMap<String, Object> params){
		
		return sqlMapper.insert("com.wallet.harex.mapper.SystemMapper.insertSystemSettingSvr", params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ��� ó�� MW_PS_MON_OPER
	 */
	public int insertSystemSettingOper(HashMap<String, Object> params){
		
		return sqlMapper.insert("com.wallet.harex.mapper.SystemMapper.insertSystemSettingOper", params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ��� ó�� MW_PS_MON_IF
	 */
	public int insertSystemSettingIf(HashMap<String, Object> params){
		
		return sqlMapper.insert("com.wallet.harex.mapper.SystemMapper.insertSystemSettingIf", params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���μ���ID �ߺ�Ȯ��
	 */
	public String selectCheckProcessIdYn(String id){
		
		return (String) sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectCheckProcessIdYn", id);
		
	}
}
