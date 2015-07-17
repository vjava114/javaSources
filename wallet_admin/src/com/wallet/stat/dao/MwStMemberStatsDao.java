package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.stat.mapper.MwStMemberStatsMapper;
import com.wallet.stat.model.MwStMemberStatsMocaPay;
import com.wallet.stat.model.MwStMemberStatsOs;
import com.wallet.stat.model.MwStMemberStatsAge;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdMemberStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdMemberStatsDao
 * History	: 2012/08/23, psj, 작업구분 : 가입자 통계
 * Comment	:
 */
public class MwStMemberStatsDao extends MybatisCilent implements MwStMemberStatsMapper {

	
	/**
	 * 누적가입자현황 조회 : OS/이통사별 통계
	 * @return	
	 */
		public MwStMemberStatsOs selectMemberStatsAcmOs(HashMap<String, Object> params) {
		return (MwStMemberStatsOs) sqlMapper.selectOne("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsAcmOs", params);
	}
	
	/**
	 * 누적가입자현황 조회 : 연령/성별 통계
	 * @return	
	 */
		public MwStMemberStatsAge selectMemberStatsAcmAge(HashMap<String, Object> params) {
		return (MwStMemberStatsAge) sqlMapper.selectOne("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsAcmAge", params);
	}
		
	/**
	 * 누적가입자현황 조회 : 모카페이 통계
	 * @return	
	 */
		public MwStMemberStatsMocaPay selectMemberStatsAcmMocaPay(HashMap<String, Object> params) {
		return (MwStMemberStatsMocaPay) sqlMapper.selectOne("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsAcmMocaPay", params);
	}

	
	/**
	 * 신규가입자현황 조회 : 단말기준 / 일별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsOs> selectMemberStatsNewOsDayList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewOsDayList", params);
	}
	
	/**
	 * 신규가입자현황 조회 : 단말기준 / 월별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsOs> selectMemberStatsNewOsMonthList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewOsMonthList", params);
	}
	
	/**
	 * 신규가입자현황 조회 : 단말기준 / 년도별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsOs> selectMemberStatsNewOsYearList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewOsYearList", params);
	}
	
	/**
	 * 신규가입자현황 조회 : 이용자기준(성별, 연령) 년도별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsAge> selectMemberStatsNewAgeList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewAgeList", params);
	}
	
	/**
	 * 신규가입자현황 조회 : 모카페이 유입 /  일별/월별/년도별
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMemberStatsMocaPay> selectMemberStatsNewMocaPayList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMemberStatsMapper.selectMemberStatsNewMocaPayList", params);
	}
	
}
