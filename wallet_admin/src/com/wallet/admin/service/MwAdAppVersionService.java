package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdAppVersionDao;
import com.wallet.admin.model.MwAdAppVersion;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdAppVersionService.java
 * Class	: com.wallet.admin.service.MwAdAppVersionService
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > �� �������� ����
 * Comment	:
 */
public class MwAdAppVersionService {
	private Logger log = Log.getLogger("logs");
	private final MwAdAppVersionDao dao;
	
	public MwAdAppVersionService() {
		dao = new MwAdAppVersionDao();
	}
	
	/**
	 * �� ���� ���� ��ȸ select
	 */
	public List<MwAdAppVersion> selectAppVersionList(HashMap<String,Object> params) {

		List<MwAdAppVersion> result = null;
		
		result = dao.selectAppVersionList(params);

		return result;
	}
	
	/**
	 * �� ���� ���� �� ��ȸ select
	 */
	public MwAdAppVersion selectAppVersionListDtl(HashMap<String,Object> params) {

		MwAdAppVersion result = null;
		
		result = dao.selectAppVersionListDtl(params);

		return result;
	}
	
	/**
	 * ��� ��� insert
	 */
	public void insertAppVersionReg(HashMap<String,Object> params) {

		dao.insertAppVersionReg(params);			//isnert

	}
	
	/**
	 * �� ���� �� ����
	 */
	public void updateAppVersionDtl(HashMap<String,Object> params) {

		dao.updateAppVersionDtl(params);

	}
	
	/**
	 * �� ���� ���� ����
	 */
	public void deleteAppVersionDtl(HashMap<String,Object> params) {

		dao.deleteAppVersionDtl(params);

	}
	
}
