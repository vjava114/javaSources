/*
 * 
 */
package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwMembAgreeMapper;
import com.wallet.membership.model.MwMembAgree;
import com.wallet.membership.model.MwMembAgreeExample;

/**
 * 
 *
 */
public class MwMembAgreeDao extends MybatisCilent implements MwMembAgreeMapper {

	
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	public int countByExample(MwMembAgreeExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwMembAgreeMapper.countByExample", example);
	}

	
	public int deleteByExample(MwMembAgreeExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.delete("com.wallet.membership.mapper.MwMembAgreeMapper.deleteByExample", example);
	}

	
	public int insert(MwMembAgree record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMembAgreeMapper.insertSelective", record);
	}

	
	public int insertSelective(MwMembAgree record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMembAgreeMapper.insertSelective", record);
	}

	
	public List<MwMembAgree> selectByExample(MwMembAgreeExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMembAgreeMapper.selectByExample", example);
	}

	
	public int updateByExampleSelective(MwMembAgree record,	MwMembAgreeExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMembAgreeMapper.updateByExampleSelective", param);
	}

	
	public int updateByExample(MwMembAgree record, MwMembAgreeExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMembAgreeMapper.updateByExample", param);
	}
	
	public void commit() {
		sqlMapper.commit();
	}

}
