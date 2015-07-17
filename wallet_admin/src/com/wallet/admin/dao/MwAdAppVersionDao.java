package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdAppVersionMapper;
import com.wallet.admin.model.MwAdAppVersion;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdAppVersionDao.java
 * Class	: com.wallet.admin.dao.MwAdAppVersionDao
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > �� �������� ����
 * Comment	:
 */
public class MwAdAppVersionDao extends MybatisCilent implements MwAdAppVersionMapper {
    
	/**
	 * �� ���� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdAppVersion> selectAppVersionList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdAppVersionMapper.selectAppVersionList", params);
	}
	
	/**
	 * �� ���� ���� �� ��ȸ select
	 * @return	
	 */
	public MwAdAppVersion selectAppVersionListDtl(HashMap<String, Object> params) {
		return (MwAdAppVersion) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdAppVersionMapper.selectAppVersionList", params);
	}
	
	/**
	 * �� ���� ���� ��� insert
	 * @return	
	 */
	public int insertAppVersionReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdAppVersionMapper.insertAppVersionReg", params);
	}
	
	/**
	 * �� ���� �� ���� ���� update
	 * @return	
	 */
	public int updateAppVersionDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdAppVersionMapper.updateAppVersionDtl", params);
	}
	
	/**
	 * �� ���� ���� delete
	 * @return	
	 */
	public int deleteAppVersionDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdAppVersionMapper.deleteAppVersionDtl", params);
	}
	
}
