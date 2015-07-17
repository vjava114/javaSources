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
 * History	: 2012/08/23, psj, 작업구분 : 코드값 정의
 * Comment	:
 */
public class MwStCmCodeService {
	private Logger log = Log.getLogger("logs");
	private final MwStCmCodeDao dao;
	
	public MwStCmCodeService() {
		dao = new MwStCmCodeDao();
	}

	/**
	 * 코드값 정의 리스트 조회
	 */
	public List<MwStCmCode> selectCmCodeList(HashMap<String,Object> params) {

		List<MwStCmCode> result = null;
		
		result = dao.selectCmCodeList(params);

		return result;
	}
	/**
	 * 코드값 정의 seq_no 조회
	 */
	public int selectCmCodeSeqNo(HashMap<String,Object> params) {
		
		int seq_no = dao.selectCmCodeSeqNo(params);

		return seq_no;
	}
	/**
	 * 코드값 정의 리스트 등록
	 */
	public void insertCmCodeReg(HashMap<String,Object> params) {

			dao.insertCmCodeReg(params);
	}
	
	/**
	 * 코드값 삭제
	 */
	public void deleteCmCode(HashMap<String,Object> params) {

			dao.deleteCmCode(params);
	}
	

}
