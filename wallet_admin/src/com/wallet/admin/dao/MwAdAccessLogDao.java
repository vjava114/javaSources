package com.wallet.admin.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.admin.mapper.MwAdAccessLogMapper;
import com.wallet.admin.model.MwAdAccessLog;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdAppVersionDao.java
 * Class	: com.wallet.admin.dao.MwAdAppVersionDao
 * History	: 2012/10/11, psj, 작업구분 : AccessLog 관리
 * Comment	:
 */
public class MwAdAccessLogDao extends MybatisCilent implements MwAdAccessLogMapper {
    
	public void commit() {
		sqlMapper.commit();
	}


	public void rollback() {
		sqlMapper.rollback();
	}
	
	/**
	 * AccessLog select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdAccessLog> selectAccessLogList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdAccessLogMapper.selectAccessLogList", params);
	}
	
	/**
	 * AccessLog insert
	 * @return	
	 */
	public int insertAccessLogReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdAccessLogMapper.insertAccessLogReg", params);
	}
}
