package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.stat.model.MwStHubStats;


public interface MwStHubStatsMapper {
	
	List<Map<String,String>> selectHubStatsList(HashMap<String, Object> params);
	
	

}