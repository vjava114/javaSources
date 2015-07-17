package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.stat.dao.MwStLogStatsDao;
import com.wallet.stat.model.MwStLogStats;


/*
 * Filename	: MwAdPayStatsService.java
 * Class	: com.wallet.stat.service.MwAdPayStatsService
 * History	: 2012/08/23, psj, 작업구분 : 결제 통계
 * Comment	:
 */
public class MwStLogStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStLogStatsDao dao;
	
	public MwStLogStatsService() {
		dao = new MwStLogStatsDao();
	}

	/**
	 * 결제 통계 / 일별
	 */
	public List<MwStLogStats> selectAppLogStatsList(HashMap<String,Object> params) {

		List<MwStLogStats> result = null;
		
		result = dao.selectAppLogStatsList(params);

		return result;
	}
	
}
