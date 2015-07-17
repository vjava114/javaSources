package com.wallet.stat.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.stat.mapper.MwStMarketStatsMapper;
import com.wallet.stat.model.MwStMarketStats;
import com.wallet.common.util.MybatisCilent;

/*
 * Filename	: MwAdMarketStatsDao.java
 * Class	: com.wallet.stat.dao.MwAdMarketStatsDao
 * History	: 2012/08/23, psj, �۾����� : �����̵� ��ġ
 * Comment	:
 */
public class MwStMarketStatsDao extends MybatisCilent implements MwStMarketStatsMapper {


	/**
	 * �����̵� ��ġ - title
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<MwStMarketStats> selectMarketStatsTitle() {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMarketStatsMapper.selectMarketStatsTitle");
	}

	/**
	 * �����̵� ��ġ
	 * @return	
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> selectMarketStatsList(HashMap<String, Object> params) {
		return sqlMapper.selectList("com.wallet.stat.mapper.MwStMarketStatsMapper.selectMarketStatsList", params);
	}
	
	
}
