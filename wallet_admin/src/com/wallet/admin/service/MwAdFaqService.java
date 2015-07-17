package com.wallet.admin.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.admin.dao.MwAdFaqDao;
import com.wallet.admin.model.MwAdFaq;

import com.wallet.common.util.Log;


/*
 * Filename	: MwAdFaqService.java
 * Class	: com.wallet.admin.service.MwAdFaqService
 * History	: 2012/08/23, psj, 작업구분 : 기타관리 > FAQ 관리
 * Comment	:
 */
public class MwAdFaqService {
	private Logger log = Log.getLogger("logs");
	private final MwAdFaqDao dao;
	
	public MwAdFaqService() {
		dao = new MwAdFaqDao();
	}
	
	/**
	 * FAQ 조회 select
	 */
	public int selectFaqListTotalCnt(HashMap<String,Object> params) {
		
		int totalCount = dao.selectFaqListTotalCnt(params);

		return totalCount;
	}
	
	/**
	 * FAQ 조회 select
	 */
	public List<MwAdFaq> selectFaqList(HashMap<String,Object> params) {
	
		List<MwAdFaq> result = null;
		
		result = dao.selectFaqList(params);
		
		return result;

	}
	
	/**
	 * FAQ 상세 조회 select
	 */
	public MwAdFaq selectFaqListDtl(HashMap<String,Object> params) {

		MwAdFaq result = null;
		
		result = dao.selectFaqListDtl(params);

		return result;
	}
	
	/**
	 * FAQ 등록 insert
	 */
	public void insertFaqReg(HashMap<String,Object> params) {

		dao.insertFaqReg(params);			//isnert

	}
	
	/**
	 * FAQ 상세 변경
	 */
	public void updateFaqDtl(HashMap<String,Object> params) {

		dao.updateFaqDtl(params);

	}
	
	/**
	 * FAQ 정보 삭제
	 */
	public void deleteFaqDtl(HashMap<String,Object> params) {

		dao.deleteFaqDtl(params);

	}
	
}
