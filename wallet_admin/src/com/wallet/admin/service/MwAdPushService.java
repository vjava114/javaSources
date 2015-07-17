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
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > Push 발송
 * Comment	:
 */
public class MwAdPushService {
	private Logger log = Log.getLogger("logs");
	private final MwAdPushDao dao;
	
	public MwAdPushService() {
		dao = new MwAdPushDao();
	}
	
	/**
	 * Push 정보 조회 select
	 */
	public List<MwAdPush> selectPushList(HashMap<String,Object> params) {

		List<MwAdPush> result = null;
		
		result = dao.selectPushList(params);

		return result;
	}
	
	/**
	 * Push 정보 상세 조회 select
	 */
	public MwAdPush selectPushListDtl(HashMap<String,Object> params) {

		MwAdPush result = null;
		
		result = dao.selectPushListDtl(params);

		return result;
	}
	
	/**
	 * Push 정보 등록 insert
	 */
	public void insertPushReg(HashMap<String,Object> params) {

		dao.insertPushReg(params);			//isnert

	}
	
	/**
	 * Push 상세 정보 변경 update
	 */
	public void updatePushDtl(HashMap<String,Object> params) {

		dao.updatePushDtl(params);

	}
	
	/**
	 * Push 정보 delete
	 */
	public void deletePushDtl(HashMap<String,Object> params) {

		dao.deletePushDtl(params);

	}
	
}
