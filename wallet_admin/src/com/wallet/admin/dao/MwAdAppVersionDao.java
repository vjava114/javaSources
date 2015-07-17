package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdAppVersionMapper;
import com.wallet.admin.model.MwAdAppVersion;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdAppVersionDao.java
 * Class	: com.wallet.admin.dao.MwAdAppVersionDao
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > 앱 버전정보 관리
 * Comment	:
 */
public class MwAdAppVersionDao extends MybatisCilent implements MwAdAppVersionMapper {
    
	/**
	 * 앱 버전 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdAppVersion> selectAppVersionList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdAppVersionMapper.selectAppVersionList", params);
	}
	
	/**
	 * 앱 버전 정보 상세 조회 select
	 * @return	
	 */
	public MwAdAppVersion selectAppVersionListDtl(HashMap<String, Object> params) {
		return (MwAdAppVersion) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdAppVersionMapper.selectAppVersionList", params);
	}
	
	/**
	 * 앱 버전 정보 등록 insert
	 * @return	
	 */
	public int insertAppVersionReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdAppVersionMapper.insertAppVersionReg", params);
	}
	
	/**
	 * 앱 버전 상세 정보 변경 update
	 * @return	
	 */
	public int updateAppVersionDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdAppVersionMapper.updateAppVersionDtl", params);
	}
	
	/**
	 * 앱 버전 정보 delete
	 * @return	
	 */
	public int deleteAppVersionDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdAppVersionMapper.deleteAppVersionDtl", params);
	}
	
}
