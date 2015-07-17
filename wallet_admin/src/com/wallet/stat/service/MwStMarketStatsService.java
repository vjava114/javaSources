package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStMarketStatsDao;
import com.wallet.stat.model.MwStMarketStats;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdMarketStatsService.java
 * Class	: com.wallet.stat.service.MwAdMarketStatsService
 * History	: 2012/08/23, psj, �۾����� : �����̵� ��ġ
 * Comment	:
 */
public class MwStMarketStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStMarketStatsDao dao;
	
	public MwStMarketStatsService() {
		dao = new MwStMarketStatsDao();
	}

	/**
	 * �����̵� ��ġ - title
	 */
	public List<MwStMarketStats> selectMarketStatsTitle() {

		List<MwStMarketStats> result = null;
		
		result = dao.selectMarketStatsTitle();

		return result;
	}
	
	/**
	 * �����̵� ��ġ
	 */
	public List<Map<String,String>> selectMarketStatsList(HashMap<String,Object> params) {

		List<Map<String,String>> result = null;
		
		result = dao.selectMarketStatsList(params);

		return result;
	}

}
