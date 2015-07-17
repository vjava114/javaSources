package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.stat.model.MwStMarketStats;


public interface MwStMarketStatsMapper {
	
	List<MwStMarketStats> selectMarketStatsTitle();

	List<Map<String,String>> selectMarketStatsList(HashMap<String, Object> params);
	
	
	

}