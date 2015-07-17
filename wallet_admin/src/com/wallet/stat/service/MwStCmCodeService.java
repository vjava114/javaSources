package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStCmCodeDao;
import com.wallet.stat.model.MwStCmCode;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdCmCodeService.java
 * Class	: com.wallet.stat.service.MwAdCmCodeService
 * History	: 2012/08/23, psj, �۾����� : �ڵ尪 ����
 * Comment	:
 */
public class MwStCmCodeService {
	private Logger log = Log.getLogger("logs");
	private final MwStCmCodeDao dao;
	
	public MwStCmCodeService() {
		dao = new MwStCmCodeDao();
	}

	/**
	 * �ڵ尪 ���� ����Ʈ ��ȸ
	 */
	public List<MwStCmCode> selectCmCodeList(HashMap<String,Object> params) {

		List<MwStCmCode> result = null;
		
		result = dao.selectCmCodeList(params);

		return result;
	}
	/**
	 * �ڵ尪 ���� seq_no ��ȸ
	 */
	public int selectCmCodeSeqNo(HashMap<String,Object> params) {
		
		int seq_no = dao.selectCmCodeSeqNo(params);

		return seq_no;
	}
	/**
	 * �ڵ尪 ���� ����Ʈ ���
	 */
	public void insertCmCodeReg(HashMap<String,Object> params) {

			dao.insertCmCodeReg(params);
	}
	
	/**
	 * �ڵ尪 ����
	 */
	public void deleteCmCode(HashMap<String,Object> params) {

			dao.deleteCmCode(params);
	}
	

}
