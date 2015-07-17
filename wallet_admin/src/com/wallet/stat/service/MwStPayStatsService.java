package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStPayStatsDao;
import com.wallet.stat.model.MwStPayStats;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdPayStatsService.java
 * Class	: com.wallet.stat.service.MwAdPayStatsService
 * History	: 2012/08/23, psj, �۾����� : ���� ���
 * Comment	:
 */
public class MwStPayStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStPayStatsDao dao;
	
	public MwStPayStatsService() {
		dao = new MwStPayStatsDao();
	}

	/**
	 * ���� ��� / �Ϻ�
	 */
	public List<MwStPayStats> selectPayStatsDayList(HashMap<String,Object> params) {

		List<MwStPayStats> result = null;
		
		result = dao.selectPayStatsDayList(params);

		return result;
	}
	
	
	/**
	 * ���� ��� / ����/�⵵��
	 */
	public List<MwStPayStats> selectPayStatsMonthYearList(HashMap<String,Object> params) {

		List<MwStPayStats> result = null;
		
		result = dao.selectPayStatsMonthYearList(params);

		return result;
	}
	
	/**
	 * ���� ��� / ����
	 */
	public MwStPayStats selectPayStatsAcc(HashMap<String,Object> params) {

		MwStPayStats result = null;
		
		result = dao.selectPayStatsAcc(params);

		return result;
	}
	
}
