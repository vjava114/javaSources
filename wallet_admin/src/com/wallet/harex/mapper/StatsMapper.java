package com.wallet.harex.mapper;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.model.StatsReport;
import com.wallet.harex.model.StatsListComp;

public interface StatsMapper {
	
	List<StatsListComp> selectStatsListComp(HashMap<String, Object> params);
	Integer selectStatsListCompCnt(HashMap<String, Object> params);

	StatsReport selectStatsReportCmon(HashMap<String, Object> params);
	
}
