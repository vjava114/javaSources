package com.wallet.harex.mapper;

import com.wallet.harex.model.Compare;

import java.util.HashMap;
import java.util.List;

public interface CompareMapper {

	List<Compare> selectCompareList(HashMap<String, Object> params);
	
	Integer selectCompareListCnt(HashMap<String, Object> params);

	List<Compare> selectCompareMemList(HashMap<String, Object> params);
	
	Integer selectCompareMemListCnt(HashMap<String, Object> params);
	
	List<Compare> selectCompareListExcel(HashMap<String, Object> params);
	
	List<Compare> selectCompareMemListExcel(HashMap<String, Object> params);
}