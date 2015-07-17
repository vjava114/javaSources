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
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > 앱 버전정보 관리
 * Comment	:
 */
public class MwAdAppVersionService {
	private Logger log = Log.getLogger("logs");
	private final MwAdAppVersionDao dao;
	
	public MwAdAppVersionService() {
		dao = new MwAdAppVersionDao();
	}
	
	/**
	 * 앱 버전 관리 조회 select
	 */
	public List<MwAdAppVersion> selectAppVersionList(HashMap<String,Object> params) {

		List<MwAdAppVersion> result = null;
		
		result = dao.selectAppVersionList(params);

		return result;
	}
	
	/**
	 * 앱 버전 관리 상세 조회 select
	 */
	public MwAdAppVersion selectAppVersionListDtl(HashMap<String,Object> params) {

		MwAdAppVersion result = null;
		
		result = dao.selectAppVersionListDtl(params);

		return result;
	}
	
	/**
	 * 배너 등록 insert
	 */
	public void insertAppVersionReg(HashMap<String,Object> params) {

		dao.insertAppVersionReg(params);			//isnert

	}
	
	/**
	 * 앱 버전 상세 변경
	 */
	public void updateAppVersionDtl(HashMap<String,Object> params) {

		dao.updateAppVersionDtl(params);

	}
	
	/**
	 * 앱 버전 정보 삭제
	 */
	public void deleteAppVersionDtl(HashMap<String,Object> params) {

		dao.deleteAppVersionDtl(params);

	}
	
}
