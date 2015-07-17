package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStMarketStatsDao;
import com.wallet.stat.model.MwStMarketStats;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdMarketStatsService.java
 * Class	: com.wallet.stat.service.MwAdMarketStatsService
 * History	: 2012/08/23, psj, 작업구분 : 마켓이동 수치
 * Comment	:
 */
public class MwStMarketStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStMarketStatsDao dao;
	
	public MwStMarketStatsService() {
		dao = new MwStMarketStatsDao();
	}

	/**
	 * 마켓이동 수치 - title
	 */
	public List<MwStMarketStats> selectMarketStatsTitle() {

		List<MwStMarketStats> result = null;
		
		result = dao.selectMarketStatsTitle();

		return result;
	}
	
	/**
	 * 마켓이동 수치
	 */
	public List<Map<String,String>> selectMarketStatsList(HashMap<String,Object> params) {

		List<Map<String,String>> result = null;
		
		result = dao.selectMarketStatsList(params);

		return result;
	}

}
