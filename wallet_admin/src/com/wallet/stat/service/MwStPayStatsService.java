package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStPayStatsDao;
import com.wallet.stat.model.MwStPayStats;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdPayStatsService.java
 * Class	: com.wallet.stat.service.MwAdPayStatsService
 * History	: 2012/08/23, psj, 작업구분 : 결제 통계
 * Comment	:
 */
public class MwStPayStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStPayStatsDao dao;
	
	public MwStPayStatsService() {
		dao = new MwStPayStatsDao();
	}

	/**
	 * 결제 통계 / 일별
	 */
	public List<MwStPayStats> selectPayStatsDayList(HashMap<String,Object> params) {

		List<MwStPayStats> result = null;
		
		result = dao.selectPayStatsDayList(params);

		return result;
	}
	
	
	/**
	 * 결제 통계 / 월별/년도별
	 */
	public List<MwStPayStats> selectPayStatsMonthYearList(HashMap<String,Object> params) {

		List<MwStPayStats> result = null;
		
		result = dao.selectPayStatsMonthYearList(params);

		return result;
	}
	
	/**
	 * 결제 통계 / 누적
	 */
	public MwStPayStats selectPayStatsAcc(HashMap<String,Object> params) {

		MwStPayStats result = null;
		
		result = dao.selectPayStatsAcc(params);

		return result;
	}
	
}
