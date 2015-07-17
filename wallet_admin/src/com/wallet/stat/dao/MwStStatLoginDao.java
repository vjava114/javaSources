package com.wallet.stat.dao;

import java.util.HashMap;
import com.wallet.stat.mapper.MwStStatLoginMapper;
import com.wallet.stat.model.MwStStatLogin;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdLoginDao.java
 * Class	: com.wallet.stat.dao.MwAdLoginDao
 * History	: 2012/08/23, psj, �۾����� : �α���
 * Comment	:
 */
public class MwStStatLoginDao extends MybatisCilent implements MwStStatLoginMapper {

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
	public MwStStatLogin selectLoginIdCheck(HashMap<String, Object> params) {
		return (MwStStatLogin) sqlMapper.selectOne("com.wallet.stat.mapper.MwStStatLoginMapper.selectLoginIdCheck", params);
	}
	
	/**
	 * �����ȣ ���� update
	 * @return	
	 */
	public int updateRetryCnt(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.stat.mapper.MwStStatLoginMapper.updateRetryCnt", params);
	}
}
