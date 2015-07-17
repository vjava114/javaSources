package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.SystemDao;
import com.wallet.harex.model.SystemM;
import com.wallet.harex.model.SystemStatusList;


/*
 * Filename	: SystemService.java
 * Class	:  com.wallet.harex.service.SystemService
 * History	: 2012/09/13, mhjang, �۾����� : �ý��۸���͸�
 * Comment	:
 */
public class SystemService {
	
	private Logger log = Log.getLogger("logs");
	private final SystemDao dao;
	
	public SystemService() {
		dao = new SystemDao();
	}
	
	public void commit(){
		dao.commit();
	}


	public void rollback() {
		dao.rollback();
	}
	
	/**
	 * �ý��۸���͸���Ȳ ��ȸ select
	 */
	public List<SystemStatusList> selectSystemStatusList(HashMap<String,Object> params) {
		
		List<SystemStatusList> result = null;
		result = dao.selectSystemStatusList(params);
		return result;
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��ȸ select
	 */
	public List<SystemM> selectSystemSettingList(HashMap<String,Object> params) {
		
		List<SystemM> result = null;
		result = dao.selectSystemSettingList(params);
		return result;
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��ȸ select
	 */
	public SystemM selectSystemSettingView(HashMap<String,Object> params) {
		
		SystemM result = null;
		result = dao.selectSystemSettingView(params);
		return result;
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��ȸ (����ȭ��) select
	 */
	public SystemM selectSystemSettingModifyView(HashMap<String,Object> params) {
		
		SystemM result = null;
		result = dao.selectSystemSettingModifyView(params);
		return result;
	}
	
	/**
	 * �ý��۸���͸� ȯ�漳�� ��ȸ row count
	 */
	public int selectSystemSettingListCnt() {
		
		return Integer.parseInt(dao.selectSystemSettingListCnt().toString());
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���� ó�� MW_PS_MON_SVR
	 */
	public int updateSystemSettingSvr(HashMap<String, Object> params){
		
		return dao.updateSystemSettingSvr(params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���� ó�� MW_PS_MON_OPER
	 */
	public int updateSystemSettingOper(HashMap<String, Object> params){
		
		return dao.updateSystemSettingOper(params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���� ó�� MW_PS_MON_IF
	 */
	public int updateSystemSettingIf(HashMap<String, Object> params){
		
		return dao.updateSystemSettingIf(params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ��� ó�� MW_PS_MON_SVR
	 */
	public int insertSystemSettingSvr(HashMap<String, Object> params){
		
		return dao.insertSystemSettingSvr(params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���ó�� MW_PS_MON_OPER
	 */
	public int insertSystemSettingOper(HashMap<String, Object> params){
		
		return dao.insertSystemSettingOper(params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ��� ó�� MW_PS_MON_IF
	 */
	public int insertSystemSettingIf(HashMap<String, Object> params){
		
		return dao.insertSystemSettingIf(params);
		
	}
	
	/**
	 *  �ý��۸���͸� ȯ�漳�� ���μ���ID �ߺ�Ȯ��
	 */
	public String selectCheckProcessIdYn(String id){		
		return dao.selectCheckProcessIdYn(id);
		
	}
	
}