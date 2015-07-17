package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdUserMapper;
import com.wallet.admin.model.MwAdUser;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdUserDao.java
 * Class	: com.wallet.admin.dao.MwAdUserDao
 * History	: 2012/08/23, psj, �۾����� : �����ڸ޴� > ������ ��������
 * Comment	:
 */
public class MwAdUserDao extends MybatisCilent implements MwAdUserMapper {
    
	public void commit() {
		sqlMapper.commit();
	}

	public void rollback() {
		sqlMapper.rollback();
	}
	
	/**
	 * ������ �������� ���� ��ȸ select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdUser> selectUserList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdUserMapper.selectUserList", params);
	}
	
	/**
	 * ������ �������� ���� �� ��ȸ select
	 * @return	
	 */
	public MwAdUser selectUserListDtl(HashMap<String, Object> params) {
		return (MwAdUser) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdUserMapper.selectUserList", params);
	}
	
	/**
	 * ������ �������� ���� ��� insert
	 * @return	
	 */
	public int insertUserReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdUserMapper.insertUserReg", params);
	}
	
	/**
	 * ������ �������� �� ���� ���� update
	 * @return	
	 */
	public int updateUserDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdUserMapper.updateUserDtl", params);
	}

	/**
	 * ������ �������� ��й�ȣ ���� ���� update
	 * @return	
	 */
	public int updateUserPasswd(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdUserMapper.updateUserPasswd", params);
	}
	
	/**
	 * ������ �������� ���� delete
	 * @return	
	 */
	public int deleteUserDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdUserMapper.deleteUserDtl", params);
	}
	
}
