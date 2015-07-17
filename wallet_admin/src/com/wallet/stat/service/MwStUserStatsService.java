package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStUserStatsDao;
import com.wallet.stat.model.MwStUserStats;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdUserStatsService.java
 * Class	: com.wallet.stat.service.MwAdUserStatsService
 * History	: 2012/08/23, psj, �۾����� : �̿��� ���
 * Comment	:
 */
public class MwStUserStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStUserStatsDao dao;
	
	public MwStUserStatsService() {
		dao = new MwStUserStatsDao();
	}

	/**
	 * �̿��� ���
	 */
	public List<MwStUserStats> selectUserStatsList(HashMap<String,Object> params) {

		List<MwStUserStats> result = null;
		
		result = dao.selectUserStatsList(params);

		return result;
	}
	
	/**
	 * ������ ���
	 */
	public List<MwStUserStats> selectUserStatsListAll(HashMap<String,Object> params) {

		List<MwStUserStats> result = null;
		
		result = dao.selectUserStatsListAll(params);

		return result;
	}

}
