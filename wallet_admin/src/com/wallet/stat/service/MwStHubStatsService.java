package com.wallet.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wallet.stat.dao.MwStHubStatsDao;
import com.wallet.stat.model.MwStHubStats;
import com.wallet.common.util.Log;


/*
 * Filename	: MwAdHubStatsService.java
 * Class	: com.wallet.stat.service.MwAdHubStatsService
 * History	: 2012/08/23, psj, �۾����� : ��������� ���� ���
 * Comment	:
 */
public class MwStHubStatsService {
	private Logger log = Log.getLogger("logs");
	private final MwStHubStatsDao dao;
	
	public MwStHubStatsService() {
		dao = new MwStHubStatsDao();
	}

	/**
	 * ��������� ���� ���
	 */
	public List<Map<String,String>> selectHubStatsList(HashMap<String,Object> params) {

		List<Map<String,String>> result = null;
		
		result = dao.selectHubStatsList(params);

		return result;
	}

}
