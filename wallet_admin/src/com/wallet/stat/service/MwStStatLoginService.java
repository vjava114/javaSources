package com.wallet.stat.service;

import java.util.HashMap;
import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStStatLoginDao;
import com.wallet.stat.model.MwStStatLogin;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdLoginService.java
 * Class	: com.wallet.stat.service.MwAdLoginService
 * History	: 2012/08/23, psj, �۾����� : ��� �α���
 * Comment	:
 */
public class MwStStatLoginService {
	private Logger log = Log.getLogger("logs");
	private final MwStStatLoginDao dao;
	
	public MwStStatLoginService() {
		dao = new MwStStatLoginDao();
	}
	
	public void commit(){
		dao.commit();
	}

	public void rollback() {
		dao.rollback();
	}
	
	/**
	 * �α��� id ��ȸ select
	 */
	public MwStStatLogin selectLoginIdCheck(HashMap<String,Object> params) {

		MwStStatLogin result = null;
		
		result = dao.selectLoginIdCheck(params);

		return result;
	}
	
	/**
	 * �����ȣ ���� update
	 */
	public void updateRetryCnt(HashMap<String,Object> params)  {
			dao.updateRetryCnt(params);
	}
	
}
