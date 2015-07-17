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
 * History	: 2012/08/23, psj, �۾����� : �����ڸ޴� > ������ ��������
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
	 * ������ �������� ���� ��ȸ select
	 */
	public List<MwAdUser> selectUserList(HashMap<String,Object> params) {

		List<MwAdUser> result = null;
		
		result = dao.selectUserList(params);

		return result;
	}
	
	/**
	 * ������ �������� ���� �� ��ȸ select
	 */
	public MwAdUser selectUserListDtl(HashMap<String,Object> params) {

		MwAdUser result = null;
		
		result = dao.selectUserListDtl(params);

		return result;
	}
	
	/**
	 * ������ �������� ���� ��� insert
	 */
	public void insertUserReg(HashMap<String,Object> params) {

		dao.insertUserReg(params);			//isnert

	}
	
	/**
	 * ������ �������� �� ���� ���� update
	 */
	public void updateUserDtl(HashMap<String,Object> params) {

		dao.updateUserDtl(params);

	}
	
	/**
	 * ������ �������� ��й�ȣ ���� ���� update
	 */
	public void updateUserPasswd(HashMap<String,Object> params) {

		dao.updateUserPasswd(params);

	}
	
	/**
	 * ������ �������� ���� delete
	 */
	public void deleteUserDtl(HashMap<String,Object> params) {

		dao.deleteUserDtl(params);

	}
	
}
