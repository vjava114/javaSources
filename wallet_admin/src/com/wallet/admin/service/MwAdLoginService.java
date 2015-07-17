package com.wallet.admin.service;

import java.util.HashMap;
import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdLoginDao;
import com.wallet.admin.model.MwAdLogin;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdLoginService.java
 * Class	: com.wallet.admin.service.MwAdLoginService
 * History	: 2012/08/23, psj, 작업구분 : 관리자메뉴 > 로그인 관리
 * Comment	:
 */
public class MwAdLoginService {
	private Logger log = Log.getLogger("logs");
	private final MwAdLoginDao dao;
	
	public MwAdLoginService() {
		dao = new MwAdLoginDao();
	}

	public void commit(){
		dao.commit();
	}

	public void rollback() {
		dao.rollback();
	}
	
	/**
	 * 로그인 id 조회 select
	 */
	public MwAdLogin selectLoginIdCheck(HashMap<String,Object> params) {

		MwAdLogin result = null;
		
		result = dao.selectLoginIdCheck(params);

		return result;
	}
	
	/**
	 * 비빌번호 오류 update
	 */
	public void updateRetryCnt(HashMap<String,Object> params)  {
			dao.updateRetryCnt(params);
	}
	
	/**
	 * 인증번호 체크
	 */
	public MwAdLogin selectSmsCheck(HashMap<String,Object> params) {

		MwAdLogin result = null;
		
		result = dao.selectSmsCheck(params);

		return result;
	}
	
	/**
	 * 인증번호 delete
	 */
	public void deleteSms(HashMap<String,Object> params)  {
			dao.deleteSms(params);
	}
	
}
