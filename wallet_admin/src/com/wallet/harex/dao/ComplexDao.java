package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.ComplexMapper;
import com.wallet.harex.model.ComplexInfo;
import com.wallet.harex.model.ComplexList;
import com.wallet.harex.model.ComplexShopHid;
import com.wallet.harex.model.ComplexUserList;

public class ComplexDao extends MybatisCilent implements ComplexMapper {
	
	public void commit() {
		sqlMapper.commit();
	}

	public void rollback() {
		sqlMapper.rollback();
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplexList> selectComplexList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.ComplexMapper.selectComplexList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectComplexListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.ComplexMapper.selectComplexListCnt", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public ComplexInfo selectComplexInfo(HashMap<String, Object> params) {
		
		return (ComplexInfo) sqlMapper.selectOne("com.wallet.harex.mapper.ComplexMapper.selectComplexInfo", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplexShopHid> selectComplexShopHidList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.ComplexMapper.selectComplexShopHidList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectComplexShopHidListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.ComplexMapper.selectComplexShopHidListCnt", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplexUserList> selectComplexUserList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.ComplexMapper.selectComplexUserList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectComplexUserListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.ComplexMapper.selectComplexUserListCnt", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer updateComplexShopHid(HashMap<String, Object> params) {
		Integer result = 0;
		result = new Integer(sqlMapper.update("com.wallet.harex.mapper.ComplexMapper.updateComplexShopHid", params));
		return result;
	}

	public String selectComplexShopHidRegisterCheck(String h_shop_id){
		
		return (String) sqlMapper.selectOne("com.wallet.harex.mapper.ComplexMapper.selectComplexShopHidRegisterCheck", h_shop_id);
		
	}
}
