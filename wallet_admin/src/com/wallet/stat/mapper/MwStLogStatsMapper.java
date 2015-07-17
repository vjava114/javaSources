package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.stat.model.MwStLogStats;


public interface MwStLogStatsMapper {

	List<MwStLogStats> selectAppLogStatsList(HashMap<String, Object> params);
	
}