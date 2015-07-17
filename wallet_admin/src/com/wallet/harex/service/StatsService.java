package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.OfferingDao;
import com.wallet.harex.dao.StatsDao;
import com.wallet.harex.model.OfferingList;
import com.wallet.harex.model.OfferingDetail;
import com.wallet.harex.model.StatsReport;
import com.wallet.harex.model.StatsListComp;

public class StatsService {
	private Logger log = Log.getLogger("logs");
	private final StatsDao dao;
	
	public StatsService() {
		dao = new StatsDao();
	}
	
	/**
	 * 통계 관리 select
	 */
	public List<StatsListComp> selectStatsListComp(HashMap<String,Object> params) {
		
		List<StatsListComp> result = null;

		result = dao.selectStatsListComp(params);
		
		return result;
	}
	
	/**
	 * 통계 관리 select
	 */
	public int selectStatsListCompCnt(HashMap<String,Object> params) {
		
		return Integer.parseInt(dao.selectStatsListCompCnt(params).toString());
	}
	
	/**
	 * 리포팅-제휴사 select
	 */
	public StatsReport selectStatsReportCmon(HashMap<String, Object> params) {
		StatsReport result = null;
		
		result = dao.selectStatsReportCmon(params);
		
		return result;
	}
}
