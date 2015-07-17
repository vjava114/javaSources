package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.stat.mapper.MwStPayStatsMapper;
import com.wallet.stat.model.MwStPayStats;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdPayStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdPayStatsDao
 * History	: 2012/08/23, psj, 작업구분 : 결제 통계
 * Comment	:
 */
public class MwStPayStatsDao extends MybatisCilent implements MwStPayStatsMapper {
	
	/**
	 * 결제 통계 :  일별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStPayStats> selectPayStatsDayList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStPayStatsMapper.selectPayStatsDayList", params);
	}
	
	/**
	 * 결제 통계 : 년도별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStPayStats> selectPayStatsMonthYearList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStPayStatsMapper.selectPayStatsMonthYearList", params);
	}
	
	/**
	 * 결제 통계 : 누적
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public MwStPayStats selectPayStatsAcc(HashMap<String, Object> params) {
		return (MwStPayStats) sqlMapper.selectOne("com.wallet.stat.mapper.MwStPayStatsMapper.selectPayStatsAccList", params);
	}
	
	
}
