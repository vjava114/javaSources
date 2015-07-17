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
 * History	: 2012/09/13, mhjang, 작업구분 : 시스템모니터링
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
	 * 시스템모니터링현황 조회 select
	 */
	public List<SystemStatusList> selectSystemStatusList(HashMap<String,Object> params) {
		
		List<SystemStatusList> result = null;
		result = dao.selectSystemStatusList(params);
		return result;
	}
	
	/**
	 * 시스템모니터링 환경설정 조회 select
	 */
	public List<SystemM> selectSystemSettingList(HashMap<String,Object> params) {
		
		List<SystemM> result = null;
		result = dao.selectSystemSettingList(params);
		return result;
	}
	
	/**
	 * 시스템모니터링 환경설정 조회 select
	 */
	public SystemM selectSystemSettingView(HashMap<String,Object> params) {
		
		SystemM result = null;
		result = dao.selectSystemSettingView(params);
		return result;
	}
	
	/**
	 * 시스템모니터링 환경설정 조회 (수정화면) select
	 */
	public SystemM selectSystemSettingModifyView(HashMap<String,Object> params) {
		
		SystemM result = null;
		result = dao.selectSystemSettingModifyView(params);
		return result;
	}
	
	/**
	 * 시스템모니터링 환경설정 조회 row count
	 */
	public int selectSystemSettingListCnt() {
		
		return Integer.parseInt(dao.selectSystemSettingListCnt().toString());
	}
	
	/**
	 *  시스템모니터링 환경설정 수정 처리 MW_PS_MON_SVR
	 */
	public int updateSystemSettingSvr(HashMap<String, Object> params){
		
		return dao.updateSystemSettingSvr(params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 수정 처리 MW_PS_MON_OPER
	 */
	public int updateSystemSettingOper(HashMap<String, Object> params){
		
		return dao.updateSystemSettingOper(params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 수정 처리 MW_PS_MON_IF
	 */
	public int updateSystemSettingIf(HashMap<String, Object> params){
		
		return dao.updateSystemSettingIf(params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 등록 처리 MW_PS_MON_SVR
	 */
	public int insertSystemSettingSvr(HashMap<String, Object> params){
		
		return dao.insertSystemSettingSvr(params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 등록처리 MW_PS_MON_OPER
	 */
	public int insertSystemSettingOper(HashMap<String, Object> params){
		
		return dao.insertSystemSettingOper(params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 등록 처리 MW_PS_MON_IF
	 */
	public int insertSystemSettingIf(HashMap<String, Object> params){
		
		return dao.insertSystemSettingIf(params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 프로세서ID 중복확인
	 */
	public String selectCheckProcessIdYn(String id){		
		return dao.selectCheckProcessIdYn(id);
		
	}
	
}