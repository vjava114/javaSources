package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.stat.model.MwStUserStats;


public interface MwStUserStatsMapper {
	
	List<MwStUserStats> selectUserStatsList(HashMap<String, Object> params);
	
	List<MwStUserStats> selectUserStatsListAll(HashMap<String, Object> params);

}