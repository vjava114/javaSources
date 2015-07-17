package com.wallet.harex.service;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.CommonDao;

public class CommonService {
	private Logger log = Log.getLogger("logs");
	
	private final CommonDao dao;
	
	public CommonService() {
		dao = new CommonDao();
	}
	
	/**
	 * ���հ��� �̿� ������ ��ȸ
	 */
	public String getPromoId() {
		
		String result = null;
		result = dao.getPromoId();
		return result;
	}
	
	/**
	 * ���հ��� ���۸� ������ ��ȸ
	 */
	public String getRuleId() {
		
		String result = null;
		result = dao.getRuleId();
		return result;
	}
	
}
