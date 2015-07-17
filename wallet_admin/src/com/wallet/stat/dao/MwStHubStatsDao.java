package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.stat.mapper.MwStHubStatsMapper;
import com.wallet.stat.model.MwStHubStats;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdHubStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdHubStatsDao
 * History	: 2012/08/23, psj, 작업구분 : 허브페이지 유입 통계
 * Comment	:
 */
public class MwStHubStatsDao extends MybatisCilent implements MwStHubStatsMapper {

	
	/**
	 * 허브페이지 유입 통계
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> selectHubStatsList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStHubStatsMapper.selectHubStatsList", params);
	}
	
	
}
