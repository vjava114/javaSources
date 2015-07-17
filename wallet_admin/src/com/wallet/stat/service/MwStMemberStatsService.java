package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStMemberStatsDao;
import com.wallet.stat.model.MwStMemberStatsMocaPay;
import com.wallet.stat.model.MwStMemberStatsOs;
import com.wallet.stat.model.MwStMemberStatsAge;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdMemberStatsService.java
 * Class	: com.wallet.stat.service.MwAdMemberStatsService
 * History	: 2012/08/23, psj, 작업구분 : 가입자 통계
 * Comment	:
 */
public class MwStMemberStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStMemberStatsDao dao;
	
	public MwStMemberStatsService() {
		dao = new MwStMemberStatsDao();
	}

	/**
	 * 누적가입자현황 : OS/이통사별 통계
	 */
	public MwStMemberStatsOs selectMemberStatsAcmOs(HashMap<String,Object> params) {

		MwStMemberStatsOs result = null;
		
		result = dao.selectMemberStatsAcmOs(params);

		return result;
	}

	/**
	 * 누적가입자현황 : 연령/성별 통계
	 */
	public MwStMemberStatsAge selectMemberStatsAcmAge(HashMap<String,Object> params) {

		MwStMemberStatsAge result = null;
		
		result = dao.selectMemberStatsAcmAge(params);

		return result;
	}
	
	/**
	 * 누적가입자현황 : 모카페이 통계
	 */
	public MwStMemberStatsMocaPay selectMemberStatsAcmMocaPay(HashMap<String,Object> params) {

		MwStMemberStatsMocaPay result = null;
		
		result = dao.selectMemberStatsAcmMocaPay(params);

		return result;
	}
	
	/**
	 * 신규가입자현황 조회 : 단말기준 / 일별
	 */
	public List<MwStMemberStatsOs> selectMemberStatsNewOsDayList(HashMap<String,Object> params) {

		List<MwStMemberStatsOs> result = null;
		
		result = dao.selectMemberStatsNewOsDayList(params);

		return result;
	}
	
	/**
	 * 신규가입자현황 조회 : 단말기준 / 월별
	 */
	public List<MwStMemberStatsOs> selectMemberStatsNewOsMonthList(HashMap<String,Object> params) {

		List<MwStMemberStatsOs> result = null;
		
		result = dao.selectMemberStatsNewOsMonthList(params);

		return result;
	}
	
	/**
	 * 신규가입자현황 조회 : 단말기준 / 년도별
	 */
	public List<MwStMemberStatsOs> selectMemberStatsNewOsYearList(HashMap<String,Object> params) {

		List<MwStMemberStatsOs> result = null;
		
		result = dao.selectMemberStatsNewOsYearList(params);

		return result;
	}
	
	/**
	 * 신규가입자현황 조회 : 이용자기준(성별, 연령) / 일별/월별/년도별
	 */
	public List<MwStMemberStatsAge> selectMemberStatsNewAgeList(HashMap<String,Object> params) {

		List<MwStMemberStatsAge> result = null;
		
		result = dao.selectMemberStatsNewAgeList(params);

		return result;
	}
	
	/**
	 * 신규가입자현황 조회 : 모카페이 유입 / 일별/월별/년도별
	 */
	public List<MwStMemberStatsMocaPay> selectMemberStatsNewMocaPayList(HashMap<String,Object> params) {

		List<MwStMemberStatsMocaPay> result = null;
		
		result = dao.selectMemberStatsNewMocaPayList(params);

		return result;
	}
	
}
