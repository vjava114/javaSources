package com.wallet.admin.dao;

import java.util.HashMap;

import java.util.List;

import com.wallet.admin.mapper.MwAdUserMapper;
import com.wallet.admin.model.MwAdUser;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdUserDao.java
 * Class	: com.wallet.admin.dao.MwAdUserDao
 * History	: 2012/08/23, psj, 작업구분 : 관리자메뉴 > 관리자 계정관리
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
	 * 관리자 계정관리 정보 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwAdUser> selectUserList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.admin.mapper.MwAdUserMapper.selectUserList", params);
	}
	
	/**
	 * 관리자 계정관리 정보 상세 조회 select
	 * @return	
	 */
	public MwAdUser selectUserListDtl(HashMap<String, Object> params) {
		return (MwAdUser) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdUserMapper.selectUserList", params);
	}
	
	/**
	 * 관리자 계정관리 정보 등록 insert
	 * @return	
	 */
	public int insertUserReg(HashMap<String, Object> params) {
		return sqlMapper.insert("com.wallet.admin.mapper.MwAdUserMapper.insertUserReg", params);
	}
	
	/**
	 * 관리자 계정관리 상세 정보 변경 update
	 * @return	
	 */
	public int updateUserDtl(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdUserMapper.updateUserDtl", params);
	}

	/**
	 * 관리자 계정관리 비밀번호 정보 변경 update
	 * @return	
	 */
	public int updateUserPasswd(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdUserMapper.updateUserPasswd", params);
	}
	
	/**
	 * 관리자 계정관리 정보 delete
	 * @return	
	 */
	public int deleteUserDtl(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdUserMapper.deleteUserDtl", params);
	}
	
}
