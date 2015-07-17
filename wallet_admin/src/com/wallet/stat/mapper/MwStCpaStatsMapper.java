package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.stat.model.MwStCpaStats;


public interface MwStCpaStatsMapper {
	
	List<MwStCpaStats> selectCpaStatsTitle();
	
	List<Map<String,String>> selectCpaStatsList(HashMap<String, Object> params);
	
	

}