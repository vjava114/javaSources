package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.CompareMapper;
import com.wallet.harex.model.Compare;

/*
 * Filename	: CompareDao.java
 * Class	: com.wallet.harex.dao.CompareDao
 * History	: 2012/09/13, kma, 작업구분 : 대사관리
 * Comment	:
 */
public class CompareDao extends MybatisCilent implements CompareMapper {

	/**
	 * 대사관리 select
	 * @return	
	 */

	@SuppressWarnings("unchecked")
	public List<Compare> selectCompareList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.CompareMapper.selectCompareList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Compare> selectCompareListExcel(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.CompareMapper.selectCompareListExcel", params);
		
	}
	
	public Integer selectCompareListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.CompareMapper.selectCompareListCnt", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Compare> selectCompareMemList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.CompareMapper.selectCompareMemList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Compare> selectCompareMemListExcel(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.CompareMapper.selectCompareMemListExcel", params);
		
	}
	
	public Integer selectCompareMemListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.CompareMapper.selectCompareMemListCnt", params);
		
	}

}
