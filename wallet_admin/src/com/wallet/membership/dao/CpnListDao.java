/*
 * 
 */
package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.CpnListMapper;
import com.wallet.membership.model.CpnList;
import com.wallet.membership.model.CpnListExample;

/**
 * 
 *
 */
public class CpnListDao extends MybatisCilent implements CpnListMapper {


	public void commit() {
		sqlMapper.commit();
	}
	
	public void rollback(){
		sqlMapper.rollback();
	}

	
	public int countByExample(CpnListExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.CpnListMapper.countByExample", example);
	}

	
	public int deleteByExample(CpnListExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int deleteByPrimaryKey(String cpnId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insert(CpnList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(CpnList record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.CpnListMapper.insertSelective", record);
	}

	
	public List<CpnList> selectByExample(CpnListExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.CpnListMapper.selectByExample", example);
	}

	
	public CpnList selectByPrimaryKey(String cpnId) {
		// TODO Auto-generated method stub
		return (CpnList) sqlMapper.selectOne("com.wallet.membership.mapper.CpnListMapper.selectByPrimaryKey", cpnId);
	}

	
	public int updateByExampleSelective(CpnList record, CpnListExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.CpnListMapper.updateByExampleSelective", param);
	}

	
	public int updateByExample(CpnList record, CpnListExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKeySelective(CpnList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKey(CpnList record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<CpnList> CouponOrderByExample(CpnListExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.CpnListMapper.CouponOrderByExample", example);
	}

	
	public int CouponOrderCount(CpnListExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.CpnListMapper.CouponOrderCount", example);
	}

	
	
	
	
	
	
}
