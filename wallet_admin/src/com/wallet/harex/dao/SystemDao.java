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
 * History	: 2012/09/13, kma, 작업구분 : 시스템모니터링
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
	 * 시스템모니터링현황 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<SystemStatusList> selectSystemStatusList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.SystemMapper.selectSystemStatusList", params);
	}
	
	/**
	 * 시스템모니터링 환경설정 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<SystemM> selectSystemSettingList(HashMap<String,Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.SystemMapper.selectSystemSettingList", params);
	}
	
	/**
	 * 시스템모니터링 환경설정 상세보기 조회
	 * @return	
	 */
	public SystemM selectSystemSettingView(HashMap<String,Object> params) {
		
		return (SystemM)sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectSystemSettingView", params);
	}
	
	/**
	 * 시스템모니터링 환경설정 상세보기 조회(수정화면)
	 * @return	
	 */
	public SystemM selectSystemSettingModifyView(HashMap<String,Object> params) {
		
		return (SystemM)sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectSystemSettingModifyView", params);
	}
	
	/**
	 * 시스템모니터링 환경설정 조회 row count
	 * @return	
	 */
	public Integer selectSystemSettingListCnt() {
		
		return (Integer)sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectSystemSettingListCnt");
	}
	
	/**
	 *  시스템모니터링 환경설정 수정 처리 MW_PS_MON_SVR
	 */
	public int updateSystemSettingSvr(HashMap<String, Object> params){
		
		return sqlMapper.update("com.wallet.harex.mapper.SystemMapper.updateSystemSettingSvr", params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 수정 처리 MW_PS_MON_OPER
	 */
	public int updateSystemSettingOper(HashMap<String, Object> params){
		
		return sqlMapper.update("com.wallet.harex.mapper.SystemMapper.updateSystemSettingOper", params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 수정 처리 MW_PS_MON_IF
	 */
	public int updateSystemSettingIf(HashMap<String, Object> params){
		
		return sqlMapper.update("com.wallet.harex.mapper.SystemMapper.updateSystemSettingIf", params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 등록 처리 MW_PS_MON_SVR
	 */
	public int insertSystemSettingSvr(HashMap<String, Object> params){
		
		return sqlMapper.insert("com.wallet.harex.mapper.SystemMapper.insertSystemSettingSvr", params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 등록 처리 MW_PS_MON_OPER
	 */
	public int insertSystemSettingOper(HashMap<String, Object> params){
		
		return sqlMapper.insert("com.wallet.harex.mapper.SystemMapper.insertSystemSettingOper", params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 등록 처리 MW_PS_MON_IF
	 */
	public int insertSystemSettingIf(HashMap<String, Object> params){
		
		return sqlMapper.insert("com.wallet.harex.mapper.SystemMapper.insertSystemSettingIf", params);
		
	}
	
	/**
	 *  시스템모니터링 환경설정 프로세스ID 중복확인
	 */
	public String selectCheckProcessIdYn(String id){
		
		return (String) sqlMapper.selectOne("com.wallet.harex.mapper.SystemMapper.selectCheckProcessIdYn", id);
		
	}
}
