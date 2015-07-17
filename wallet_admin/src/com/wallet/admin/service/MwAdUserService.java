package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdUserDao;
import com.wallet.admin.model.MwAdUser;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdUserService.java
 * Class	: com.wallet.admin.service.MwAdUserService
 * History	: 2012/08/23, psj, 작업구분 : 관리자메뉴 > 관리자 계정관리
 * Comment	:
 */
public class MwAdUserService {
	private Logger log = Log.getLogger("logs");
	private final MwAdUserDao dao;
	
	public MwAdUserService() {
		dao = new MwAdUserDao();
	}
	public void commit(){
		dao.commit();
	}

	public void rollback() {
		dao.rollback();
	}
	/**
	 * 관리자 계정관리 정보 조회 select
	 */
	public List<MwAdUser> selectUserList(HashMap<String,Object> params) {

		List<MwAdUser> result = null;
		
		result = dao.selectUserList(params);

		return result;
	}
	
	/**
	 * 관리자 계정관리 정보 상세 조회 select
	 */
	public MwAdUser selectUserListDtl(HashMap<String,Object> params) {

		MwAdUser result = null;
		
		result = dao.selectUserListDtl(params);

		return result;
	}
	
	/**
	 * 관리자 계정관리 정보 등록 insert
	 */
	public void insertUserReg(HashMap<String,Object> params) {

		dao.insertUserReg(params);			//isnert

	}
	
	/**
	 * 관리자 계정관리 상세 정보 변경 update
	 */
	public void updateUserDtl(HashMap<String,Object> params) {

		dao.updateUserDtl(params);

	}
	
	/**
	 * 관리자 계정관리 비밀번호 정보 변경 update
	 */
	public void updateUserPasswd(HashMap<String,Object> params) {

		dao.updateUserPasswd(params);

	}
	
	/**
	 * 관리자 계정관리 정보 delete
	 */
	public void deleteUserDtl(HashMap<String,Object> params) {

		dao.deleteUserDtl(params);

	}
	
}
