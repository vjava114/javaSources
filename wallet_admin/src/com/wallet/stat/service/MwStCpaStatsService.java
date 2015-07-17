package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStCpaStatsDao;
import com.wallet.stat.model.MwStCpaStats;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdCpaStatsService.java
 * Class	: com.wallet.stat.service.MwAdCpaStatsService
 * History	: 2012/08/23, psj, 작업구분 : CPA 가입통계
 * Comment	:
 */
public class MwStCpaStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStCpaStatsDao dao;
	
	public MwStCpaStatsService() {
		dao = new MwStCpaStatsDao();
	}

	/**
	 * CPA 가입통계 - title
	 */
	public List<MwStCpaStats> selectCpaStatsTitle() {

		List<MwStCpaStats> result = null;
		
		result = dao.selectCpaStatsTitle();

		return result;
	}
	
	

	/**
	 * CPA 가입통계
	 */
	public List<Map<String,String>> selectCpaStatsList(HashMap<String,Object> params) {

		List<Map<String,String>> result = null;
		
		result = dao.selectCpaStatsList(params);

		return result;
	}
}
