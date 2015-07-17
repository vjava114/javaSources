package com.wallet.admin.dao;

import java.util.HashMap;
import com.wallet.admin.mapper.MwAdLoginMapper;
import com.wallet.admin.model.MwAdLogin;
import com.wallet.common.util.Log;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdAppVersionDao.java
 * Class	: com.wallet.admin.dao.MwAdAppVersionDao
 * History	: 2012/08/23, psj, 작업구분 : 관리자메뉴 > 앱 버전정보 관리
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
	 * 로그인 id 조회 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public MwAdLogin selectLoginIdCheck(HashMap<String, Object> params) {
		return (MwAdLogin) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdLoginMapper.selectLoginIdCheck", params);
	}
	
	/**
	 * 비빌번호 오류 update
	 * @return	
	 */
	public int updateRetryCnt(HashMap<String, Object> params) {
		return sqlMapper.update("com.wallet.admin.mapper.MwAdLoginMapper.updateRetryCnt", params);
	}
	
	/**
	 * 인증번호 체크
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public MwAdLogin selectSmsCheck(HashMap<String, Object> params) {
		return (MwAdLogin) sqlMapper.selectOne("com.wallet.admin.mapper.MwAdLoginMapper.selectSmsCheck", params);
	}
	
	/**
	 * 인증번호 delete
	 * @return	
	 */
	public int deleteSms(HashMap<String, Object> params) {
		return sqlMapper.delete("com.wallet.admin.mapper.MwAdLoginMapper.deleteSms", params);
	}
}
