package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.StatsMapper;
import com.wallet.harex.model.StatsListComp;
import com.wallet.harex.model.StatsReport;

public class StatsDao extends MybatisCilent implements StatsMapper {
	/**
	 * 통계 관리 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<StatsListComp> selectStatsListComp(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.harex.mapper.StatsMapper.selectStatsListComp", params);
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectStatsListCompCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.StatsMapper.selectStatsListCompCnt", params);
		
	}
	
	/**
	 * 리포팅 제휴사 select
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public StatsReport selectStatsReportCmon(HashMap<String, Object> params) {
		
		return (StatsReport) sqlMapper.selectOne("com.wallet.harex.mapper.StatsMapper.selectStatsReportCmon", params);
		
	}
}