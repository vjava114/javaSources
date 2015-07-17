package com.wallet.admin.dao;

import java.util.HashMap;
import com.wallet.admin.mapper.MwAdLoginMapper;
import com.wallet.admin.model.MwAdLogin;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdAppVersionDao.java
 * Class	: com.wallet.admin.dao.MwAdAppVersionDao
 * History	: 2012/08/23, psj, �۾����� : �����ڸ޴� > �� �������� ����
 * Comment	:
 */
public class MwAdLoginDao extends MybatisCilent implements MwAdLoginMapper {

	public void commit() {
		sqlMapper.commit();
	}

	public void rollback() {
		sqlMapper.rollback();
	}
	
	/**
	 * �α��� id ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public MwAdLogin selectLoginIdCheck(HashMap<String, Object> params) {
		return (MwAdLogin) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdLoginMapper.selectLoginIdCheck", params);
	}
	
	/**
	 * �����ȣ ���� update
	 * @return	
	 */
	public int updateRetryCnt(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdLoginMapper.updateRetryCnt", params);
	}
	
	/**
	 * ������ȣ üũ
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public MwAdLogin selectSmsCheck(HashMap<String, Object> params) {
		return (MwAdLogin) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdLoginMapper.selectSmsCheck", params);
	}
	
	/**
	 * ������ȣ delete
	 * @return	
	 */
	public int deleteSms(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdLoginMapper.deleteSms", params);
	}
}
