/*
 * 
 */
package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwMsStampMapper;
import com.wallet.membership.model.MwMsStamp;
import com.wallet.membership.model.MwMsStampExample;
import com.wallet.membership.model.MwMsStampKey;

/**
 * 
 *
 */
public class MwMsStampDao extends MybatisCilent implements MwMsStampMapper {

	
	public void rollback(){
		sqlMapper.rollback();
	}

	
	public void commit() {
		sqlMapper.commit();
	}


	public int countByExample(MwMsStampExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwMsStampMapper.countByExample", example);
	}


	public int deleteByExample(MwMsStampExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.delete("com.wallet.membership.mapper.MwMsStampMapper.deleteByExample", example);
	}


	public int deleteByPrimaryKey(MwMsStampKey key) {
		// TODO Auto-generated method stub
		return sqlMapper.delete("com.wallet.membership.mapper.MwMsStampMapper.deleteByPrimaryKey", key);
	}


	public int insert(MwMsStamp record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMsStampMapper.insert", record);
	}


	public int insertSelective(MwMsStamp record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwMsStampMapper.insertSelective", record);
	}


	public List<MwMsStamp> selectByExampleWithBLOBs(MwMsStampExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsStampMapper.selectByExampleWithBLOBs", example);
	}


	public List<MwMsStamp> selectByExample(MwMsStampExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsStampMapper.selectByExample", example);
	}


	public MwMsStamp selectByPrimaryKey(MwMsStampKey key) {
		// TODO Auto-generated method stub
		return (MwMsStamp) sqlMapper.selectOne("com.wallet.membership.mapper.MwMsStampMapper.selectByPrimaryKey", key);
	}


	
	public int updateByExampleSelective(MwMsStamp record, MwMsStampExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStampMapper.updateByExampleSelective", param);
	}


	public int updateByExampleWithBLOBs(MwMsStamp record, MwMsStampExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStampMapper.updateByExampleWithBLOBs", param);
	}


	public int updateByExample(MwMsStamp record, MwMsStampExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwMsStampMapper.updateByExample", param);
	}


	public int updateByPrimaryKeySelective(MwMsStamp record) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int updateByPrimaryKeyWithBLOBs(MwMsStamp record) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int updateByPrimaryKey(MwMsStamp record) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<MwMsStamp> StampSelectByExample(MwMsStampExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsStampMapper.StampSelectByExample", example);
	}


	public List<MwMsStamp> StampGetBySeq(MwMsStampExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwMsStampMapper.StampGetBySeq", example);
	}


	
	public int StampSelectByCount(MwMsStampExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwMsStampMapper.StampSelectByCount", example);
	}	
}
