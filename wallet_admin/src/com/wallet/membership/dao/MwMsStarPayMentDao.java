package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwMsStarPayMentMapper;
import com.wallet.membership.model.MwMsStarPayMent;
import com.wallet.membership.model.MwMsStarPayMentExample;

public class MwMsStarPayMentDao extends MybatisCilent implements MwMsStarPayMentMapper{
	
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	public int countByExample(MwMsStarPayMentExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwMsStarPayMentMapper.countByExample", example);
	}

	
	public int deleteByExample(MwMsStarPayMentExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.delete("com.wallet.membership.mapper.MwMsStarPayMentMapper.deleteByExample", example);
	}

	
	public int deleteByPrimaryKey(String compId) {
		// TODO Auto-generated method stub
		return sqlMapper.delete("com.wallet.membership.mapper.MwMsStarPayMentMapper.deleteByPrimaryKey", compId);
	}

	
	public int insert(MwMsStarPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMsStarPayMentMapper.insert", record);
	}

	
	public int insertSelective(MwMsStarPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMsStarPayMentMapper.insertSelective", record);
	}

	
	public List<MwMsStarPayMent> selectByExampleWithBLOBs(
			MwMsStarPayMentExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsStarPayMentMapper.selectByExampleWithBLOBs", example);
	}

	
	public List<MwMsStarPayMent> selectByExample(MwMsStarPayMentExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsStarPayMentMapper.selectByExample", example);
	}

	
	public MwMsStarPayMent selectByPrimaryKey(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByExampleSelective(MwMsStarPayMent record,
			MwMsStarPayMentExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStarPayMentMapper.updateByExampleSelective", param);
	}

	
	public int updateByExampleWithBLOBs(MwMsStarPayMent record,
			MwMsStarPayMentExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStarPayMentMapper.updateByExampleWithBLOBs", param);
	}

	
	public int updateByExample(MwMsStarPayMent record,
			MwMsStarPayMentExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStarPayMentMapper.updateByExample", param);
	}

	
	public int updateByPrimaryKeySelective(MwMsStarPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStarPayMentMapper.updateByPrimaryKeySelective", record);
	}

	
	public int updateByPrimaryKeyWithBLOBs(MwMsStarPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStarPayMentMapper.updateByPrimaryKeyWithBLOBs", record);
	}

	
	public int updateByPrimaryKey(MwMsStarPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStarPayMentMapper.updateByPrimaryKey", record);
	}
	public void commit() {
		sqlMapper.commit();
	}
}
