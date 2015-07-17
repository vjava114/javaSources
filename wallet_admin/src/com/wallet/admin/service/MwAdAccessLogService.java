package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdAccessLogDao;
import com.wallet.admin.model.MwAdAccessLog;

import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;


/*
 * Filename	: MwAdAccessLogService.java
 * Class	: com.wallet.admin.dao.MwAdAccessLogService
 * History	: 2012/10/11, psj, 작업구분 : AccessLog 관리
 * Comment	:
 */
public class MwAdAccessLogService {
	private Logger log = Log.getLogger("logs");
	private final MwAdAccessLogDao dao;
	
	public MwAdAccessLogService() {
		dao = new MwAdAccessLogDao();
	}
	
	public void commit(){
		dao.commit();
	}

	public void rollback() {
		dao.rollback();
	}
	
	/**
	 * AccessLog select
	 */
	public List<MwAdAccessLog> selectAccessLogList(HashMap<String,Object> params) {

		List<MwAdAccessLog> result = null;
		result = dao.selectAccessLogList(params);
		return result;
	}
	
	
	/**
	 * AccessLog insert
	 */
	public int insertAccessLogReg(HashMap<String,Object> params) {
		
		int result = 0;
		result = dao.insertAccessLogReg(params);			//isnert
		return result;
		

	}
	
	
	
}
