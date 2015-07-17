package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdPushDao;
import com.wallet.admin.model.MwAdPush;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdPushService.java
 * Class	: com.wallet.admin.service.MwAdPushService
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > Push �߼�
 * Comment	:
 */
public class MwAdPushService {
	private Logger log = Log.getLogger("logs");
	private final MwAdPushDao dao;
	
	public MwAdPushService() {
		dao = new MwAdPushDao();
	}
	
	/**
	 * Push ���� ��ȸ select
	 */
	public List<MwAdPush> selectPushList(HashMap<String,Object> params) {

		List<MwAdPush> result = null;
		
		result = dao.selectPushList(params);

		return result;
	}
	
	/**
	 * Push ���� �� ��ȸ select
	 */
	public MwAdPush selectPushListDtl(HashMap<String,Object> params) {

		MwAdPush result = null;
		
		result = dao.selectPushListDtl(params);

		return result;
	}
	
	/**
	 * Push ���� ��� insert
	 */
	public void insertPushReg(HashMap<String,Object> params) {

		dao.insertPushReg(params);			//isnert

	}
	
	/**
	 * Push �� ���� ���� update
	 */
	public void updatePushDtl(HashMap<String,Object> params) {

		dao.updatePushDtl(params);

	}
	
	/**
	 * Push ���� delete
	 */
	public void deletePushDtl(HashMap<String,Object> params) {

		dao.deletePushDtl(params);

	}
	
}
