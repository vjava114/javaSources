package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwMsCompPayMentMapper;
import com.wallet.membership.model.MwMsCompPayMent;
import com.wallet.membership.model.MwMsCompPayMentExample;

public class MwMsCompPayMentDao extends MybatisCilent implements MwMsCompPayMentMapper{

	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	public int countByExample(MwMsCompPayMentExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwMsCompPayMentMapper.countByExample", example);
	}

	
	public int deleteByExample(MwMsCompPayMentExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.delete("com.wallet.membership.mapper.MwMsCompPayMentMapper.deleteByExample", example);
	}

	
	public int deleteByPrimaryKey(String compId) {
		// TODO Auto-generated method stub
		return sqlMapper.delete("com.wallet.membership.mapper.MwMsCompPayMentMapper.deleteByPrimaryKey", compId);
	}

	
	public int insert(MwMsCompPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMsCompPayMentMapper.insert", record);
	}

	
	public int insertSelective(MwMsCompPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMsCompPayMentMapper.insertSelective", record);
	}

	
	public List<MwMsCompPayMent> selectByExampleWithBLOBs(
			MwMsCompPayMentExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsCompPayMentMapper.selectByExampleWithBLOBs", example);
	}

	
	public List<MwMsCompPayMent> selectByExample(MwMsCompPayMentExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsCompPayMentMapper.selectByExample", example);
	}

	
	public MwMsCompPayMent selectByPrimaryKey(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByExampleSelective(MwMsCompPayMent record,
			MwMsCompPayMentExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsCompPayMentMapper.updateByExampleSelective", param);
	}

	
	public int updateByExampleWithBLOBs(MwMsCompPayMent record,
			MwMsCompPayMentExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsCompPayMentMapper.updateByExampleWithBLOBs", param);
	}

	
	public int updateByExample(MwMsCompPayMent record,
			MwMsCompPayMentExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsCompPayMentMapper.updateByExample", param);
	}

	
	public int updateByPrimaryKeySelective(MwMsCompPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.MwMsCompPayMentMapper.updateByPrimaryKeySelective", record);
	}

	
	public int updateByPrimaryKeyWithBLOBs(MwMsCompPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.MwMsCompPayMentMapper.updateByPrimaryKeyWithBLOBs", record);
	}

	
	public int updateByPrimaryKey(MwMsCompPayMent record) {
		// TODO Auto-generated method stub
		return sqlMapper.update("com.wallet.membership.mapper.MwMsCompPayMentMapper.updateByPrimaryKey", record);
	}
	public void commit() {
		sqlMapper.commit();
	}

}
