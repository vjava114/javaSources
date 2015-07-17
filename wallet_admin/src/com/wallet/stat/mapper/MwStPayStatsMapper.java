package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.stat.model.MwStPayStats;


public interface MwStPayStatsMapper {
	
	List<MwStPayStats> selectPayStatsDayList(HashMap<String, Object> params);
	
	List<MwStPayStats> selectPayStatsMonthYearList(HashMap<String, Object> params);
	
	MwStPayStats selectPayStatsAcc(HashMap<String, Object> params);
	
	

}