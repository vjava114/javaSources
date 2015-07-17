package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.stat.mapper.MwStCpaStatsMapper;
import com.wallet.stat.model.MwStCpaStats;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdCpaStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdCpaStatsDao
 * History	: 2012/08/23, psj, �۾����� : CPA �������
 * Comment	:
 */
public class MwStCpaStatsDao extends MybatisCilent implements MwStCpaStatsMapper {

	
	/**
	 * CPA �������- title
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStCpaStats> selectCpaStatsTitle() {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStCpaStatsMapper.selectCpaStatsTitle");
	}

	/**
	 * CPA �������
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> selectCpaStatsList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStCpaStatsMapper.selectCpaStatsList", params);
	}
	
}
