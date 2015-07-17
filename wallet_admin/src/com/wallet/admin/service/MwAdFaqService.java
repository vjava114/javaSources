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
 * History	: 2012/08/23, psj, �۾����� : ��Ÿ���� > FAQ ����
 * Comment	:
 */
public class MwAdFaqService {
	private Logger log = Log.getLogger("logs");
	private final MwAdFaqDao dao;
	
	public MwAdFaqService() {
		dao = new MwAdFaqDao();
	}
	
	/**
	 * FAQ ��ȸ select
	 */
	public int selectFaqListTotalCnt(HashMap<String,Object> params) {
		
		int totalCount = dao.selectFaqListTotalCnt(params);

		return totalCount;
	}
	
	/**
	 * FAQ ��ȸ select
	 */
	public List<MwAdFaq> selectFaqList(HashMap<String,Object> params) {
	
		List<MwAdFaq> result = null;
		
		result = dao.selectFaqList(params);
		
		return result;

	}
	
	/**
	 * FAQ �� ��ȸ select
	 */
	public MwAdFaq selectFaqListDtl(HashMap<String,Object> params) {

		MwAdFaq result = null;
		
		result = dao.selectFaqListDtl(params);

		return result;
	}
	
	/**
	 * FAQ ��� insert
	 */
	public void insertFaqReg(HashMap<String,Object> params) {

		dao.insertFaqReg(params);			//isnert

	}
	
	/**
	 * FAQ �� ����
	 */
	public void updateFaqDtl(HashMap<String,Object> params) {

		dao.updateFaqDtl(params);

	}
	
	/**
	 * FAQ ���� ����
	 */
	public void deleteFaqDtl(HashMap<String,Object> params) {

		dao.deleteFaqDtl(params);

	}
	
}
