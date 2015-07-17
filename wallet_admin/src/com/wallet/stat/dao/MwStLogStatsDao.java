package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.stat.mapper.MwStLogStatsMapper;
import com.wallet.stat.model.MwStLogStats;

/*
 * Filename	: MwAdPayStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdPayStatsDao
 * History	: 2012/08/23, psj, 작업구분 : 결제 통계
 * Comment	:
 */
public class MwStLogStatsDao extends MybatisCilent implements MwStLogStatsMapper {
	
	/**
	 * 결제 통계 :  일별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStLogStats> selectAppLogStatsList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStLogStatsMapper.selectAppLogStatsList", params);
	}
	
}
