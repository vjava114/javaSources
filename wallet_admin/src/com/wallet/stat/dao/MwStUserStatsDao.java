package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.stat.mapper.MwStUserStatsMapper;
import com.wallet.stat.model.MwStUserStats;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdUserStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdUserStatsDao
 * History	: 2012/08/23, psj, 작업구분 : 이용자 통계
 * Comment	:
 */
public class MwStUserStatsDao extends MybatisCilent implements MwStUserStatsMapper {

	
	/**
	 * 이용자 통계
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStUserStats> selectUserStatsList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStUserStatsMapper.selectUserStatsList", params);
	}
	
	/**
	 * 가입자 통계
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStUserStats> selectUserStatsListAll(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStUserStatsMapper.selectUserStatsListAll", params);
	}
	
	
}
